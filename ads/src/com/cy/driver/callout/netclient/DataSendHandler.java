package com.cy.driver.callout.netclient;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by haoy on 15/1/31.
 */
public class DataSendHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("向服务端发数据..");

        String content = initParameter();

        int length = content.getBytes().length;

        byte[] shOrgMachineID = new byte[2];//16			/* 发送端主机编码：一般采用IP地址最后一位 */
        byte[] ushOrgCommPort = new byte[2];//16 /* 发送端虚拟端口号 */
        byte[] shDestMachineID = new byte[2];//16			/* 接收端主机编码：一般采用IP地址最后一位 */
        byte[] ushDestCommPort = new byte[2];//16/* 接收端虚拟端口号 */
        byte[] ushPacketLength = new byte[2];//16/* 不含本包头的包体的长度 */
        byte[] uchPacketType = new byte[2];//16  /* 包类型标识：0x00-单独包，0x01-拆分包的中间包，0x02-拆分包的最后一个包 */
        byte[] uchSubPacketSNO = new byte[2];//16/* 拆分包的包序号，从1开始计数，最大只允许255个包 */
        byte[] ushPacketSNO = new byte[2];//16   /* 包序号，用于一个包拆分之前的计数 */
        byte[] uchPacketCodeType = new byte[2];//16/* 包编码类型：0x00-GB2312，0x01-UTF-8 */
        byte[] uchReserved = new byte[2];//16    /* 保留，保证包头大小为4的整数倍 */

        short mysno = (short) CommFunc.getRandom();
        shOrgMachineID=changeByte(18, 2);
        ushOrgCommPort=changeByte(9090, 2);
        shDestMachineID=changeByte(13, 2);
        ushDestCommPort=changeByte(3122, 2);
        ushPacketLength=changeByte(length, 2);
        uchPacketType=changeByte(0, 2);
        uchSubPacketSNO=changeByte(1, 2);
        ushPacketSNO=changeByte(0, 2);
        uchPacketCodeType=changeByte(0, 2);
        uchReserved=changeByte(0x80, 2);

        ctx.write(Unpooled.copiedBuffer(shOrgMachineID));
        ctx.write(Unpooled.copiedBuffer(ushOrgCommPort));
        ctx.write(Unpooled.copiedBuffer(shDestMachineID));
        ctx.write(Unpooled.copiedBuffer(ushDestCommPort));
        ctx.write(Unpooled.copiedBuffer(ushPacketLength));
        ctx.write(Unpooled.copiedBuffer(uchPacketType));
//        ctx.write(Unpooled.copiedBuffer(uchSubPacketSNO));
//        ctx.write(Unpooled.copiedBuffer(ushPacketSNO));
        ctx.write(Unpooled.copiedBuffer(uchPacketCodeType));
        ctx.write(Unpooled.copiedBuffer(uchReserved));

        ctx.write(Unpooled.copiedBuffer(content, CharsetUtil.UTF_8));
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }

    private String initParameter() {
        return "<message><user>" +
                "<business>CS</business>" +
                "<command>callextReq</command>" +
                "<isresult>false</isresult>" +
                "<level>0</level>" +
                "<mobilecode>015268160573</mobilecode>" +
                "<param2>0</param2>" +
                "<role>0</role>" +
                "<serialid>372515447</serialid>" +
                "<time>" + CommFunc.getTime() + "</time>" +
                "<type>acd</type>" +
                "<version>1.0</version>" +
                "</user>" +
                "<body>" +
                "<calltimes>3,3</calltimes>" +
                "<channo></channo>" +
                "<comMobile>4001001040</comMobile>" +
                "<employeeid>31109</employeeid>" +
                "<employeephone>15268160573</employeephone>" +
                "<ext>018757178302</ext>" +
                "<extNbr>4001001040</extNbr>" +
                "<meetingserial></meetingserial>" +
                "<no>cc-employee</no>" +
                "<orsn>95188</orsn>" +
                "<roomid>95188</roomid>" +
                "</body>" +
                "</message>";
    }

    /**
     * 将一个int数据转为按小端顺序排列的字节数组
     *
     * @param data
     *            int数据
     * @param len 返回所需要的长度  1<=len<=4
     * @return 按小端顺序排列的字节数组
     */
    private byte[] changeByte(int data,int len) {
        byte b4 = (byte) ((data) >> 24);
        byte b3 = (byte) (((data) << 8) >> 24);
        byte b2 = (byte) (((data) << 16) >> 24);
        byte b1 = (byte) (((data) << 24) >> 24);
        byte[] bytes = new byte[len];
        switch (len) {
            case 1:
                bytes[0] = b1;
                break;
            case 2:
                bytes = new byte[]{ b1, b2 };
                break;
            case 3:
                bytes = new byte[]{ b1, b2,b3 };
                break;
            case 4:
                bytes = new byte[]{ b1, b2, b3, b4 };
                break;
        }
        return bytes;
    }
}

package com.cy.dctms.common.bo;

import com.cy.dctms.common.util.DateUtils;

/**
 * Created by haoy on 2014/9/5.
 */
public class TransactionInfo {
    private String transactionDate; //订单日期
    private String orderNumber;     //订单号码
    private String cargoName;       //货物名称
    private String shipper;         //发货方
    private long orderStart;        //订单状态
    private String orderStartStr;
    private String driverName;      //司机姓名
    private String driverTelephone; //司机号码
    private String carNumber;       //车牌号
    private int invoice;
    private int receipt;
    private boolean isReceiving ;   //确认卸货
    private boolean isInvoiceUpload;//发货单上传
    private boolean isReceiptUpload;//回单上传
    private String deliveryTime;    //发货时间
    private String arrivalTime;     //到货时间
    private String receiveTime;     //收货时间

    public TransactionInfo() {
    }

    public String getTransactionDate() {
        return DateUtils.formatDateYyyyMmDd(transactionDate);
    }

    public void setTransactionDate(String transactionDate) {
        this.transactionDate = transactionDate;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getCargoName() {
        return cargoName;
    }

    public int getInvoice() {
        return invoice;
    }

    public void setInvoice(int invoice) {
        this.invoice = invoice;
    }

    public int getReceipt() {
        return receipt;
    }

    public void setReceipt(int receipt) {
        this.receipt = receipt;
    }

    public void setCargoName(String cargoName) {
        this.cargoName = cargoName;
    }

    public String getShipper() {
        return shipper;
    }

    public void setShipper(String shipper) {
        this.shipper = shipper;
    }

    public long getOrderStart() {
        return orderStart;
    }

    public void setOrderStart(long orderStart) {
        this.orderStart = orderStart;
    }

    public String getOrderStartStr() {
        String str = "";
        if (orderStart == 1) {
            str = "待司机确认";
        } else if (orderStart == 3) {
            str = "运输跟踪";
        } else if (orderStart == 5) {
            str = "订单完成";
        } else if (orderStart == 6) {
            str = "交易取消";
        } else if (orderStart == 7) {
            str = "司机已卸货";
        }
        return str;
    }

    public void setOrderStartStr(String orderStartStr) {
        this.orderStartStr = orderStartStr;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getDriverTelephone() {
        return driverTelephone;
    }

    public void setDriverTelephone(String driverTelephone) {
        this.driverTelephone = driverTelephone;
    }

    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }

    public boolean isReceiving() {
        if (orderStart == 7) {
            return true;
        }
        return false;
    }

    public void setReceiving(boolean isReceiving) {
        this.isReceiving = isReceiving;
    }

    public boolean isInvoiceUpload() {
        if (invoice > 0) {
            return true;
        }
        return false;
    }

    public void setInvoiceUpload(boolean isInvoiceUpload) {
        this.isInvoiceUpload = isInvoiceUpload;
    }

    public boolean isReceiptUpload() {
        if (receipt > 0) {
            return true;
        }
        return false;
    }

    public void setReceiptUpload(boolean isReceiptUpload) {
        this.isReceiptUpload = isReceiptUpload;
    }

    public String getDeliveryTime() {
        return DateUtils.formatDateYyyyMmDd(deliveryTime);
    }

    public void setDeliveryTime(String deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public String getArrivalTime() {
        return DateUtils.formatDateYyyyMmDd(arrivalTime);
    }

    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public String getReceiveTime() {
        return DateUtils.formatDateYyyyMmDd(receiveTime);
    }

    public void setReceiveTime(String receiveTime) {
        this.receiveTime = receiveTime;
    }
}

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/include-tag.jsp"%>
<%@ include file="/common/include-jqueryJs.jsp"%>
<head>
<title>快到网-用户-注册</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script type="text/javascript" src="<sys:context/>/resource/js/user/register.js"></script>
<script type="text/javascript" src="<sys:context/>/resource/js/checkoutData.js"></script>
<link href="<sys:context/>/resource/css/public.css" rel="stylesheet" type="text/css" />
<link href="<sys:context/>/resource/css/index.css" rel="stylesheet" type="text/css" />
<style type="text/css">
.div1{float:left;padding-left: 20px;}
.div2{padding-left: 40px;}
.div3{float:left;padding-left: 40px;}
.div4{padding-left: 60px;}
.div5{float:left;padding-left: 60px;}
.div6{padding-left: 80px;}
.div7{float:left;padding-left: 0px;}
.div8{padding-left: 20px;}
</style>
<script type="text/javascript">
$(function(){
	inputTipText();  //初始化Input的灰色提示信息  
    messageReturn();//保存返回错误信息提示
});
</script>
</head>
<body>
<!-- 头部开始 -->
<div class="header">
  <div class="hea_top">
  </div>
  <div class="hea_cent">
    <div class="fl hea_logo"><a href="<sys:context/>/"><img src="<sys:context/>/resource/image/index/logo.jpg" /></a>
          <div class="slet_dta">
            <a>新用户注册</a>
          </div>
    </div>
  </div>
</div>
<!-- 头部结束 -->
<s:form id="mainForm" action="/registerWebUserInfo" namespace="/" method="post">
<input type='hidden' id='errorMessage' name='errorMessage' value='${webUserInfoDamain.errorMessage}'/>
<div class="mian">
	<div class="fl flant w550">
    <div class="laing doest">
        <ul>
          <li>带<font color="red">*</font>号的不能为空</li>
          <li>
            <label></label>
            <input type='hidden' id='userType' name='webUserInfoDamain.userType' value='0'/>
			<label><input type="radio" id="userTypeId" name="userTypeId" value="0" onclick="getRadio()" checked="checked" />&nbsp;企业版</label>
			<label><input type="radio" id="userTypeId" name="userTypeId" value="1" onclick="getRadio()" />&nbsp;货主版</label>
          </li>
          <li>
            <label>用户名<font color="red">*</font></label>
            <input id="code" name="webUserInfoDamain.code" type="text" tipMsg="请输入用户名" class="int" onblur="on_blur('code');" onfocus="on_focus('code');"/>
            <div class="mpt" id="divcodezhu" style="display:none">
            	<div class="wn_a"></div>
                <div class="wn_s" id="divCodeNull" style="display:none">用户名不能为空！</div>
          		<div class="wn_s" id="divCodeCyi" style="display:none">用户名已存在！</div>
          		<div class="wn_s" id="divCodeLength" style="display:none">用户名长度为3到20位！</div>
          		<div class="wn_s" id="divCodeLetter" style="display:none">用户名只能有数字字母或下划线组成！</div>
                <div class="wn_c"></div>
            </div>
          </li>
          <li>
            <label>密码<font color="red">*</font></label>
            <input id="password" name="webUserInfoDamain.password" type="password" class="int" onblur="on_blur('password');" onfocus="on_focus('password');"/>
          	 <div class="mpt" id="divPasswordzhu" style="display:none">
            	<div class="wn_a"></div>
            	<div class="wn_s" id="divPasswordNull" style="display:none">密码不能为空！</div>
          		<div class="wn_s" id="divPasswordLength" style="display:none">密码长度为6到20位！</div>
                <div class="wn_c"></div>
            </div>
          </li>
          <li>
            <label>确认密码<font color="red">*</font></label>
            <input id="passwordes" name="passwordes" type="password" class="int" onblur="on_blur('passwordes');" onfocus="on_focus('passwordes');"/>
          	<div class="mpt" id="divPasswordeszhu" style="display:none">
            	<div class="wn_a"></div>
            	<div class="wn_s"  id="divPasswordes" style="display:none" >两次密码输入不一致！</div>
                <div class="wn_c"></div>
            </div>
          </li>
          <li>
            <label>企业全称<font color="red">*</font></label>
            <input id="companyName" name="webUserInfoDamain.companyName"  tipMsg="请输入企业名称" type="text" class="int" onblur="on_blur('companyName');" onfocus="on_focus('companyName');"/>
          	<div class="mpt" id="divCompanyNamezhu" style="display:none">
            	<div class="wn_a"></div>
            	<div class="wn_s" id="divCompanyName" style="display:none">企业名称不能为空！</div>
          		<div class="wn_s" id="divCompanyNameCyi" style="display:none">该企业名称已存在！</div>
                <div class="wn_c"></div>
            </div>
          </li>
          <li>
            <label>手机<font color="red">*</font></label>
            <input id="mobilephone" name="webUserInfoDamain.mobilephone" tipMsg="请输入手机号码" type="text" class="int" onblur="on_blur('mobilephone');" onfocus="on_focus('mobilephone');"/>
            <input id="btnSendCode" name="btnSendCode" type="button" value="获取验证码" class="unt" onclick="sendMessage()"/>
          	<div class="mpt" id="divMobilephonezhu" style="display:none">
            	<div class="wn_a"></div>
            	<div class="wn_s" id="divMobilephoneNull" style="display:none">手机号码不能为空！</div>
          		<div class="wn_s" id="divMobilephoneFormat" style="display:none">请输入正确格式的手机号码！</div>
          		<div class="wn_s" id="divMobilephoneCyi" style="display:none">手机号码已经被绑定！</div>
                <div class="wn_c"></div>
            </div>
          </li>
          <li>
            <label>验证码<font color="red">*</font></label>
            <input id="codeNumber" name="webUserInfoDamain.codeNumber" tipMsg="请输入验证码" type="text" class="int" onblur="on_blur('codeNumber');" onfocus="on_focus('codeNumber');"/>
            <div class="mpt" id="divCodeNumberzhu" style="display:none">
            	<div class="wn_a"></div>
            	<div class="wn_s" id="divCodeNumberLength" style="display:none">请输入6位数字验证码！</div>
            	<div class="wn_s" id="divCodeNumberFormat" style="display:none">验证码错误！</div>
            	<div class="wn_s" id="divContentFormat" style="display:none">请填写完整注册信息！</div>
                <div class="wn_c"></div>
            </div>
          </li>
          <li class="emt">
            <label></label>
            <input name="checkboxes" id="checkboxes" type="checkbox" checked="checked"/>
            &nbsp;&nbsp;&nbsp;我已经认证阅读并同意<a href="javascript:open();" id="btn">《快到网注册协议》</a></li>
          <li class="ternow">
            <label></label>
            <input name="" id="saveBtn" onclick="save();" type="button" value="立即注册" /></li>
        </ul>
        <div style="clear:both;"></div>
      </div>
      <!-- 注册协议开始 -->
      <div class="tndow" id="tndow" style="display:none;">
   			<h3 class="th3">《软件使用许可协议》</h3>
        	<div class="cextf">
        		<p style="text-indent: 2em">
        			杭州灿越网络科技有限公司（以下简称灿越科技）特别提示：《快到网软件许可及服务协议》 (以下简称《协议》及其条款，系确立您与灿越科技之间就您下载、安装及使用快到网软件（以下简称“软件”—该软件的国家版本局的著作权登记号：如有）所订立的，描述您与灿越科技之间权利义务的协议。
				</p>
				<p style="text-indent: 2em">	
					请您审慎阅读并选择接受或不接受本《协议》（未成年人应在法定监护人陪同下阅读）。除非您接受本《协议》所有条款，否则您无权下载、安装或使用本软件及其相关服务。您的下载、安装、使用、帐号获取和登录等行为将视为对本《协议》的接受，并同意接受本《协议》各项条款的约束。
				</p>
				<p style="text-indent: 2em">
					本《协议》可由灿越科技随时更新，更新后的协议条款一旦公布即代替原来的协议条款，恕不再另行通知。用户可重新下载安装本软件或网站查阅最新版协议条款。在灿越科技修改《协议》条款后，如果用户不接受修改后的条款，请立即停止使用灿越科技提供的软件和服务，用户继续使用灿越科技提供的软件和服务将被视为已接受了修改后的协议。
				</p>
				<p style="text-indent: 2em">
					灿越科技在本《协议》项下，不对包括但不限于快到网软件及依据本《协议》提供的服务方面做出任何担保或保证。本《协议》项下包含对您与灿越科技之间权利义务限制或者免责的条款，请认真阅读！除非您与灿越科技另有书面协议，否则您与灿越科技的协议将始终至少包括下述文件中陈述的条款和条件。
        		</p>
        		
        		<p>
        			1	知识产权声明
        		</p>
        		<div>
        			<div class="div1">1.1</div>
        			<div class="div2">
        				本“软件”是由灿越科技开发，针对货物或者物品（国家法律准许流通的及或非危险品）需要异地运输的货主及承运人提供信息发布服务。“软件”的一切版权、商标权、专利权、商业秘密等知识产权，以及与“软件”相关的所有信息内容，包括但不限于：文字表述及其组合、图标、图饰、图表、色彩、界面设计、版面框架、有关数据、印刷材料、或电子文档等均受中华人民共和国著作权法、商标法、专利法、反不正当竞争法和相应的国际条约以及其他知识产权法律法规的保护，除涉及第三方授权的软件或技术外，灿越科技享有上述知识产权。
        			</div>
        		</div>
        		<div>
        			<div class="div1">1.2</div>
        			<div class="div2">
						未经灿越科技书面同意，用户不得为任何营利性或非营利性的目的自行实施、利用、转让或许可任何三方实施、利用、转让上述知识产权，灿越科技保留追究上述未经许可行为的权利。
        			</div>
        		</div>  
        		      		
        		<p>
        			2	“软件”授权范围
        		</p>
        		<div>
        			<div class="div1">2.1</div>
        			<div class="div2">    
        				用户可以为非商业目的在单一台终端设备上安装、使用、显示、运行本“软件”。用户不得为商业运营目的安装、使用、运行本“软件”，不可以对该软件或者该软件运行过程中释放到任何计算机终端内存中的数据及该软件运行过程中客户端与服务器端的交互数据进行复制、更改、修改、挂接运行或创作任何衍生作品，形式包括但不限于使用插件、外挂或非经授权的第三方工具/服务接入本“软件”和相关系统。    				
        			</div>
        		</div>
        		<div>
        			<div class="div1">2.2</div>
        			<div class="div2">     
        				用户不得未经灿越科技许可，将本“软件”安装在未经灿越科技明示许可的其他终端设备上。包括但不限于机顶盒、手持设备、电话、无线上网机、游戏机、电视机、DVD机等。   				
        			</div>
        		</div>
        		<div>
        			<div class="div1">2.3</div>
        			<div class="div2">     
        				保留权利：未明示授权的其他一切权利仍归灿越科技所有，用户使用其他权利时须另外取得灿越科技的书面同意。   				
        			</div>
        		</div>
        		
        		<p>
        			3	用户使用须知
        		</p>
        		<p style="text-indent: 2em">
        			3.1	快到网软件用户为智能移动通讯用户、互联网用户。
        		</p>
        		<div>
        			<div class="div3">3.1.1</div>
        			<div class="div4">    
        				&nbsp;快到网软件业务针对智能移动通讯用户、互联网用户开放，但以上各群体可使用的功能不完全均等。    				
        			</div>
        		</div>
        		<div>
        			<div class="div3">3.1.2</div>
        			<div class="div4">      
        				&nbsp;使用手机号码注册快到网软件的用户（以下称“快到网软件智能移动通讯用户”）可以使用快到网软件业务所有功能。  				
        			</div>
        		</div>
        		<div>
        			<div class="div3">3.1.3</div>
        			<div class="div4">     
        				&nbsp;通过网站注册快到网软件帐号的互联网用户（以下称“快到网软件互联网用户”），可以使用快到网软件基于互联网的服务。   				
        			</div>
        		</div>
        		<div>
        			<div class="div3">3.1.4</div>
        			<div class="div4">  
        				&nbsp;灿越科技保留对未来快到网软件服务改变和说明的权利。      				
        			</div>
        		</div>
        		<p style="text-indent: 2em">
        			3.2	快到网软件帐号
        		</p>
        		<div>
        			<div class="div3">3.2.1</div>
        			<div class="div4">  
        				&nbsp;用户可以通过注册快到网软件帐号使用快到网软件业务，注册方式为手机号码注册或者网站注册，软件根据注册用户注册时间、地理位置等因素自动配置一个注册帐号供信息阅读和交流使用。			
        			</div>
        		</div>
        		<div>
        			<div class="div3">3.2.2</div>
        			<div class="div4">  
        				&nbsp;快到网软件帐号的所有权归灿越科技，用户完成申请注册手续后，获得快到网软件帐号的使用权。     				
        			</div>
        		</div>
        		<div>
        			<div class="div3">3.2.3</div>
        			<div class="div4">  
        				&nbsp;快到网软件帐号使用权仅属于初始申请注册人，禁止赠与、借用、租用、转让或售卖。如果灿越科技发现使用者并非帐号初始注册人，灿越科技有权在未经通知的情况下回收该帐号而无需向该帐号使用人承担法律责任，由此带来的包括并不限于用户通讯中断、用户资料和其他相关信息等清空等损失由用户自行承担。灿越科技禁止用户私下有偿或无偿转让帐号，以免因帐号问题产生纠纷，用户应当自行承担因违反此要求而遭致的任何损失，同时灿越科技保留追究上述行为人法律责任的权利。		
        			</div>
        		</div>
        		<div>
        			<div class="div3">3.2.4</div>
        			<div class="div4">  
        				&nbsp;用户承担快到网软件帐号与密码的保管责任，并就其帐号及密码项下之一切活动负全部责任。用户须重视快到网软件帐号密码和公开邮箱的密码保护。用户同意如发现他人未经许可使用其帐号时立即通知灿越科技。      				
        			</div>
        		</div>
        		<div>
        			<div class="div3">3.2.5</div>
        			<div class="div4">  
        				&nbsp;用户快到网软件帐号在丢失或遗忘密码后，须遵照灿越科技的申诉途径及时申诉请求找回帐号。用户应不断提供能增加帐号安全性的个人密码保护资料。用户可以凭初始注册资料及个人密码保护资料填写申诉单向灿越科技申请找回帐号，灿越科技的密码找回机制仅负责识别申诉单上所填资料与系统记录资料的正确性，而无法识别申诉人是否系真正帐号权使用人。对用户因被他人冒名申诉而致的任何损失，灿越科技不承担任何责任，用户知晓快到网软件帐号及密码保管责任在于用户。      				
        			</div>
        		</div>
        		<div>
        			<div class="div3">3.2.6</div>
        			<div class="div4">  
        				&nbsp;用户注册快到网软件帐号后如果长期不使用，灿越科技有权回收帐号，以免造成资源浪费，此期限由灿越科技根据注册用户资源情况决定，但注册用户为货主的不少于一年，注册用户为承运人的不少于半年。      				
        			</div>
        		</div>
        		<p style="text-indent: 2em">
        			3.3	快到网软件资费与增值业务
        		</p>
        		<div>
        			<div class="div3">3.3.1</div>
        			<div class="div4">  
        				&nbsp;快到网软件部分功能将产生资费。例如，用智能手机下载、注册、使用快到网软件等产生的移动设备用户流量费属于移动运营商收取。			
        			</div>
        		</div>
        		<p style="text-indent: 2em">
        			3.4	用户在遵守法律及本《协议》的前提下可依本《协议》使用本“软件”。用户无权实施包括但不限于下列行为：
        		</p>
        		<div>
        			<div class="div3">3.4.1</div>
        			<div class="div4">  
        				&nbsp;用户通过非灿越科技开发、授权或认可的第三方兼容软件、系统登录或使用灿越科技软件及服务，用户针对灿越科技的软件和服务使用非灿越科技开发、授权或认证的插件和外挂；			
        			</div>
        		</div>
        		<div>
        			<div class="div3">3.4.2</div>
        			<div class="div4">  
        				&nbsp;删除本“软件”及其他副本上所有关于版权的信息、内容；		
        			</div>
        		</div>
        		<div>
        			<div class="div3">3.4.3</div>
        			<div class="div4">  
        				&nbsp;对本“软件”进行反向工程、反向汇编、反向编译等；			
        			</div>
        		</div>
        		<div>
        			<div class="div3">3.4.4</div>
        			<div class="div4">  
        				&nbsp;对于本“软件”相关信息等，未经灿越科技书面同意，用户擅自实施包括但不限于下列行为：使用、出租、出借、复制、修改、链接、转载、汇编、发表、出版，建立镜像站点、擅自借助本“软件”发展与之有关的衍生产品、作品、服务、插件、外挂、兼容、互联等。			
        			</div>
        		</div>
        		<div>
        			<div class="div3">3.4.5</div>
        			<div class="div4">  
        				&nbsp;利用本“软件”发表、传送、传播、储存违反国家法律、危害国家安全、祖国统一、社会稳定、公序良俗的内容，或任何不当的、侮辱诽谤的、淫秽的、暴力的及任何违反国家法律法规政策的内容。			
        			</div>
        		</div>
        		<div>
        			<div class="div3">3.4.6</div>
        			<div class="div4">  
        				&nbsp;利用本“软件”发表、传送、传播、储存侵害他人知识产权、商业秘密权等合法权利的内容；		
        			</div>
        		</div>
        		<div>
        			<div class="div3">3.4.7</div>
        			<div class="div4">  
        				&nbsp;制造虚假身份或传播虚假信息以误导、欺骗他人；			
        			</div>
        		</div>
        		<div>
        			<div class="div3">3.4.8</div>
        			<div class="div4">  
        				&nbsp;利用本“软件”批量发表、传送、传播广告信息及垃圾信息；			
        			</div>
        		</div>
        		<div>
        			<div class="div3">3.4.9</div>
        			<div class="div4">  
        				&nbsp;传送、散布或以其他方式实现传送含有受到知识产权法律保护的图像、相片、软件或其他资料的文件，作为举例（但不限于此）：包括版权或商标法（或隐私权或公开权），除非用户拥有或控制着相应的权利或已得到所有必要的认可；		
        			</div>
        		</div>
        		<div>
        			<div class="div3">3.4.10</div>
        			<div class="div4">  
        				&nbsp;使用任何包含有通过侵犯商标、版权、专利、商业机密或任何一方的其他专有权利的方式利用本“软件”获得的图像或相片的资料或信息；			
        			</div>
        		</div>
        		<div>
        			<div class="div3">3.4.11</div>
        			<div class="div4">  
        				&nbsp;任何人进行任何危害计算机网络安全的行为，包括但不限于：使用未经许可的数据或进入未经许可的服务器/帐号；未经允许进入公众计算机网络或者他人计算机系统并删除、修改、增加存储信息；未经许可，企图探查、扫描、测试本“软件”系统或网络的弱点或其它实施破坏网络安全的行为；企图干涉、破坏本“软件”系统或网站的正常运行，故意传播恶意程序或病毒以及其他破坏干扰正常网络信息服务的行为；伪造TCP/IP数据包名称或部分名称；			
        			</div>
        		</div>
        		<div>
        			<div class="div3">3.4.12</div>
        			<div class="div4">  
        				&nbsp;通过修改或伪造软件作品运行中的指令、数据、数据包，增加、删减、变动软件的功能或运行效果，或将用于上述用途的软件通过信息网络向公众传播或者运营；			
        			</div>
        		</div>
        		<div>
        			<div class="div3">3.4.13</div>
        			<div class="div4">  
        				&nbsp;制作、发布、传播用于窃取快到网软件帐号及他人专属信息、财产、保密信息的软件；			
        			</div>
        		</div>
        		<div>
        			<div class="div3">3.4.14</div>
        			<div class="div4">  
        				&nbsp;在未经灿越科技书面明确授权前提下，出售、出租、出借、散布、转移或转授权软件和服务或相关的链接或从使用软件和服务或软件和服务的条款中获利，无论以上使用是否为直接经济或金钱收益；
        			</div>
        		</div>
        		<div>
        			<div class="div3">3.4.15</div>
        			<div class="div4">  
        				&nbsp;其他以任何不合法的方式、为任何不合法的目的、或以任何与本协议不一致的方式使用本软件和灿越科技提供的其他服务；			
        			</div>
        		</div>
        		<div>
        			<div class="div3">3.4.16</div>
        			<div class="div4">  
        				&nbsp;用户若违反上述规定，灿越科技有权单方采取终止、完全或部分中止、限制用户帐号的使用功能等保护措施。			
        			</div>
        		</div>
        		<div>
        			<div class="div1">3.5</div>
        			<div class="div2">  
        				使用本“软件”必须遵守国家有关法律和政策等，维护国家利益，保护国家安全，并遵守本《协议》。对于用户违法或违反本《协议》的使用而引起的一切责任，由用户负全部责任，一概与灿越科技及合作单位无关；导致灿越科技及合作单位损失的，灿越科技及合作单位有权要求用户赔偿，并有权保留相关记录。而且，对于用户违法或违反本《协议》以及违反了利用本软件和快到网软件帐号访问的灿越科技或合作单位的其他服务规定的相关服务条款，灿越科技有权视用户的行为性质，在不事先通知用户的情况下，采取包括但不限于中断使用许可、停止提供服务、限制使用、回收用户快到网软件帐号、法律追究等措施。对利用快到网软件帐号进行违法活动、骚扰、欺骗其他用户等行为，灿越科技有权回收其帐号。由此带来的包括并不限于用户通信中断、用户资料、邮件和其他相关信息丢失等损失由用户自行承担。			
        			</div>
        		</div>
        		<div>
        			<div class="div1">3.6</div>
        			<div class="div2">  
        				本“软件”同大多数互联网软件一样，受包括但不限于用户原因、网络服务质量、社会环境等因素的差异影响，可能受到各种安全问题的侵扰，如他人利用用户的资料，造成现实生活中的骚扰；用户下载安装的其它软件或访问的其他网站中含有“特洛伊木马”等病毒，威胁到用户的计算机信息和数据的安全，继而影响本“软件”的正常使用等等。用户应加强信息安全及使用者资料的保护意识，要注意加强密码保护，以免遭致损失和骚扰。			
        			</div>
        		</div>
        		<div>
        			<div class="div1">3.7</div>
        			<div class="div2">  
        				非经灿越科技或灿越科技授权开发并正式发布的其它任何由本软件衍生的软件均属非法，下载、安装、使用此类软件，将可能导致不可预知的风险，由此产生的一切法律责任与纠纷一概与灿越科技无关。用户不得轻易下载、安装、使用此类软件，否则，灿越科技有权在不事先通知用户的情况下单方面终止用户灿越科技快到网软件帐号的使用资格。 用户只能通过灿越科技提供的本“软件”及其他合法通道登录使用快到网软件。用户不得通过其他未经灿越科技授权开发的包括但不限于非法兼容型软件、程序或者其他非灿越科技明示许可的方式，登录使用快到网软件帐号，否则，灿越科技有权在不事先通知用户的情况下单方面终止用户的快到网软件帐号的使用资格。			
        			</div>
        		</div>
        		<div>
        			<div class="div1">3.8</div>
        			<div class="div2">  
        				用户同意个人隐私信息是指那些能够对用户进行个人辨识或涉及个人通信的信息，包括下列信息：用户的姓名，身份证号，手机号码，IP地址，电子邮件地址信息，以及用于注册必需的身份证、驾驶证、行车证、营运证等身份与资质信息。而非个人隐私信息是指用户对本软件的操作状态以及使用习惯等一些明确且客观反映在灿越科技服务器端的基本记录信息和其他一切个人隐私信息范围外的普通信息。			
        			</div>
        		</div>     
        		<div>
        			<div class="div7">4</div>
        			<div class="div8">  
        				尊重用户个人隐私信息的私有性是灿越科技的一贯制度，灿越科技将会采取合理的措施保护用户的个人隐私信息，除法律或有法律赋予权限的政府部门要求或用户同意等原因外，灿越科技未经用户同意不向除合作单位以外的第三方公开、 透露用户个人隐私信息。			
        			</div>
        		</div>
        		<div>
        			<div class="div7">5</div>
        			<div class="div8">  
        				但是用户在注册时选择或同意，或用户与灿越科技及合作单位之间就用户个人隐私信息公开或使用另有约定的除外，同时用户应自行承担因此可能产生的任何风险，灿越科技对此不予负责。同时为了运营和改善灿越科技的技术和服务，我们将可能会自己收集使用或向第三方提供用户的非个人隐私信息，这将有助于向用户提供更好的用户体验和提高我们的服务质量。			
        			</div>
        		</div>   
        		<div>
        			<div class="div1">5.1</div>
        			<div class="div2">  
        				一般而言，灿越科技基于下列原因需要使用到用户的信息资源：			
        			</div>
        		</div>	
        		<div>
        			<div class="div1">5.2</div>
        			<div class="div2">  
        				执行软件验证服务；		
        			</div>
        		</div>	
        		<div>
        			<div class="div1">5.3</div>
        			<div class="div2">  
        				执行软件升级服务；			
        			</div>
        		</div>	
        		<div>
        			<div class="div1">5.4</div>
        			<div class="div2">  
        				提高用户的使用安全性并提供客户支持；			
        			</div>
        		</div>		        		        		
        		<div>
        			<div class="div1">5.5</div>
        			<div class="div2">  
        				因用户使用灿越科技快到网软件特定功能或因用户要求灿越科技或合作单位提供特定服务时，灿越科技或合作单位则需要把用户的信息提供给与此相关联的第三方；			
        			</div>
        		</div>
        		<div>
        			<div class="div1">5.6</div>
        			<div class="div2">  
        				将各种非个人隐私数据用于商业目的，包括但不限于向第三方提供增值服务、广告、定位广告、营销、联合注册其它服务、促销或其它任何活动（统称为“商业活动”）；			
        			</div>
        		</div>        		
        		<p style="text-indent: 2em">
        			5.7	执行灿越科技的隐私保护声明，用户可访问灿越科技网站查阅该声明；
        		</p>
        		<p style="text-indent: 2em">
        			5.8	其他有利于用户和灿越科技利益的。
        		</p>
        		<div>
        			<div class="div3">5.8.1</div>
        			<div class="div4">  
        				&nbsp;计算机资源使用的同意及风险提示			
        			</div>
        		</div>
        		<div>
        			<div class="div5">5.8.1.1</div>
        			<div class="div6">  
        				&nbsp;本“软件”需要用户共同享用、维护其所提供的利益，用户在此确认同意本“软件”在必要时使用您计算机的处理器和带宽等资源，用作容许其它本“软件”使用者与您通讯联络、分享本“软件”及服务的有限目的。此项同意可能会影响用户的使用感受和带来不可预知的风险。您应认真考虑并做出选择，承担风险。		
        			</div>
        		</div>
        		<div>
        			<div class="div5">5.8.1.2</div>
        			<div class="div6">  
        				&nbsp;用户同意本“软件”将会尽其合理努力以保护您的计算机资源及计算机通讯的隐私性和完整性，但是，您承认和同意灿越科技不能就此事提供任何保证。		
        			</div>
        		</div>
        		<div>
        			<div class="div3">5.8.2</div>
        			<div class="div4">  
        				&nbsp;灿越科技保留在任何时候根据适用法律、法规、法律程序或政府要求的需要而披露任何信息，或由灿越科技自主决定全部或部分地编辑、拒绝张贴或删除任何信息或资料的权利。			
        			</div>
        		</div>
        		<div>
        			<div class="div1">5.9</div>
        			<div class="div2">  
        				&nbsp;本“软件”的替换、修改和升级：灿越科技保留在任何时候通过为您提供本“软件”替换、修改、升级版本的权利以及为替换、修改或升级收取费用的权利。本“软件”为用户默认开通“升级提示”功能，视用户使用的“软件”版本差异，灿越科技提供给用户自行选择是否需要开通此功能。软件新版本发布后，灿越科技不保证旧版本软件的继续可用。			
        			</div>
        		</div>	
        		<div>
        			<div class="div1">5.10</div>
        			<div class="div2">  
        				&nbsp;灿越科技和/或合作单位将根据市场与技术的发展，向用户提供与本“软件”相关的各种互联网以及移动通信设备增值服务，包括免费和收费的增值服务。灿越科技和/或合作单位保留对相关增值服务收取费用及改变收费标准、方式的权利；如相关服务由免费变更为收费服务，灿越科技和/或合作单位将以适当的形式通知，用户可自主选择接受或拒绝收费服务，并保证在使用收费服务时，将按照灿越科技和/或合作单位相关收费规定支付费用，如拒付或拖欠费用，灿越科技和/或合作单位有权停止服务，并依法追偿损失及赔偿。	
        			</div>
        		</div>	        		
        		<p style="text-indent: 2em">
        			5.11	灿越科技有权在服务中或经过服务投放各种广告和宣传信息，该广告可能以系统消息或弹出窗口等形式出现。
        		</p>
        		<p style="text-indent: 2em">
        			5.12	本“软件”使用的第三方的软件或技术
        		</p>
        		<div>
        			<div class="div3">5.12.1</div>
        			<div class="div4">  
        					&nbsp;本“软件”可能会使用第三方的软件或技术，该等使用均是获得了合法授权的。如因本“软件”使用的第三方软件或技术引发的任何纠纷，由该第三方负责解决，灿越科技不承担任何责任。 灿越科技不对该第三方软件或技术提供客服支持，若用户需要获取支持，请与该第三方联系。	
        			</div>
        		</div>
        		<div>
        			<div class="div3">5.12.2</div>
        			<div class="div4">  
        					&nbsp;本“软件”如果使用了第三方的软件或技术，该第三方对该等使用有要求且需要用户遵守的，则用户应当遵守该等要求。本《协议》的附件将向用户介绍本“软件”使用了的第三方的软件或技术，并将向用户提供该第三方网络链接地址，用户可以通过点击该网络链接地址浏览、阅读、了解该第三方提出的该等软件或技术的使用要求。	
        			</div>
        		</div>
        		<div>
        			<div class="div3">5.12.3</div>
        			<div class="div4">  
        					&nbsp;本《协议》上述第3.13.2条提到，本《协议》附件将对本“软件”所使用的第三方的软件或技术进行介绍。 用户充分理解到：该介绍仅仅是灿越科技依照该第三方提供的情况，并根据灿越科技的理解对该等软件或技术所作的一个大致的介绍。灿越科技并不对该等软件或技术的功能、性质及其他情况作任何明示的或者默示的担保，也不保证该介绍就是对该等软件或技术所有情况的全面的、细致的介绍。用户如需对该等软件或技术进行全面细致的了解，应当直接与该第三方联系。	
        			</div>
        		</div>
        		<div>
        			<div class="div3">5.12.4</div>
        			<div class="div4">  
        					&nbsp;本《协议》上述第3.13.2条提到，第三方可能对本“软件”使用其授权的软件或技术有相关要求，且需要用户遵守，本《协议》附件将向用户提供第三方的网络链接地址，供用户了解第三方的相关要求。	
        			</div>
        		</div>
        		<div>
        			<div class="div7">6</div>
        			<div class="div8">  
        					用户充分理解到：本《协议》附件所提供的第三方的网络链接地址，是灿越科技根据第三方提供的当前的网络链接地址而向用户提供的。该第三方有可能变更上述网络链接地址，也有可能对本“软件”使用其授权的软件或技术的相关要求进行变更，而灿越科技可能不能及时更新上述网络链接地址。因此，用户需要及时了解该第三方上述网络链接地址及相关要求的变更。	
        			</div>
        		</div>
        		<div>
        			<div class="div7">7</div>
        			<div class="div8">  
        					而且，用户还充分理解到：第三方的相关要求可能会以“软件使用许可协议”、“授权协议”或者其他的形式来表达，是本《协议》不可分割的组成部分，与本《协议》具有同等的法律效力。且如该等要求需要用户遵守而您没有遵守时，该第三方或者国家司法机关可能会对您提起诉讼、罚款或其他的制裁措施，且要求灿越科技给予协助。	
        			</div>
        		</div>
        		<div>
        			<div class="div7">8</div>
        			<div class="div8">  
        					您充分理解到：本《协议》附件所提到的本“软件”使用的第三方授权的软件或技术，仅仅是本“软件”当前“正在”使用的第三方授权的软件或技术，灿越科技并不保证本“软件”将会永久地使用该等软件或技术，也不保证将来不会使用该第三方的其他的软件或技术，还不保证将来不会使用非该第三方的同类型或不同类型的软件或技术。	
        			</div>
        		</div>        		
        		<p>
        			9	&nbsp;法律责任与免责
        		</p>
        		<p style="text-indent: 2em">
        			9.1	利用的许可
        		</p>
        		<div>
        			<div class="div3">9.1.1</div>
        			<div class="div4">  
        					&nbsp;许可利用您的计算机：为了得到本“软件”所提供的利益，用户在此许可灿越科技利用用户的移动通讯设备、计算机的处理器和宽带，用作容许其它灿越科技快到网软件使用者与用户通讯联络的有限目的。	
        			</div>
        		</div>
        		<div>
        			<div class="div3">9.1.2</div>
        			<div class="div4">  
        					&nbsp;保护用户的移动通讯设备、计算机（资源）：用户认可灿越科技快到网软件将会尽其商业上的合理努力以保护用户的移动通讯设备、计算机资源及计算机通讯的隐私性和完整性，但是，用户承认和同意灿越科技不能就此事提供任何保证。	
        			</div>
        		</div>
        		<div>
        			<div class="div3">9.1.3</div>
        			<div class="div4">  
        					&nbsp;本软件为网络交互型软件，灿越科技可以根据用户软件使用状态和行为，为了改善软件服务和体验、完善业务内容，自己或与合作方合作开发新的服务或者调整软件功能。	
        			</div>
        		</div>
        		<div>
        			<div class="div1">9.2</div>
        			<div class="div2">  
        					灿越科技特别提请用户注意：灿越科技为了保障公司业务发展和调整的自主权，灿越科技拥有随时自行修改或中断软件授权使用而不需逐一事先通知用户的权利，如有必要，修改或中断会以通告形式公布于本软件的主页面或者灿越科技网站重要页面上。	
        			</div>
        		</div>
        		<div>
        			<div class="div1">9.3</div>
        			<div class="div2">  
        					用户违反本《协议》或相关的服务条款的规定，导致或产生的任何第三方向灿越科技主张的任何索赔、要求或损失，包括合理的律师费，用户同意赔偿灿越科技与合作公司、关联公司，并使之免受损害。对此，灿越科技有权视用户的行为性质，在不事先通知用户的情况下，采取本《协议》第3.5条所述的措施及其他相应的措施。	
        			</div>
        		</div>
        		<div>
        			<div class="div1">9.4</div>
        			<div class="div2">  
        					本软件的宗旨是为物流供需双方提供信息发布平台，供需信息均由发布人自行发布，软件本身不具备识别信息真伪的功能，灿越科技亦无法对发布信息的真实性、准确性进行核实并负责，请使用相关信息的用户仔细辨别相关信息的真实性和准确性，并自行承担不利的后果，灿越科技不对使用本“软件”由用户承担任何不利的风险和损失。	
        			</div>
        		</div>
        		<div>
        			<div class="div7">10</div>
        			<div class="div8">  
        					灿越科技及合作单位对本“软件”不作任何类型的担保，不论是明示的、默示的或法令的保证和条件，包括但不限于本“软件”的适销性、适用性、无病毒、无疏忽或无技术瑕疵问题、所有权和无侵权的明示或默示担保和条件，对在任何情况下因使用或不能使用本“软件”所产生的直接、间接、偶然、特殊及后续的损害及风险，灿越科技及合作单位不承担任何责任。		
        			</div>
        		</div>        		
        		<div>
        			<div class="div1">10.1</div>
        			<div class="div2">  
        					&nbsp;使用本“软件”涉及到移动通讯运营商、互联网服务，可能会受到各个环节不稳定因素的影响，存在因不可抗力、计算机病毒、黑客攻击、系统不稳定、用户所在位置、用户关机，非法内容信息、骚扰信息屏蔽以及其他任何网络、技术、通信线路、信息安全管理措施等原因造成的服务中断、受阻等不能满足用户要求的风险，用户须明白并自行承担以上风险，用户因此不能发送或接收阅读消息，或接、发错消息，灿越科技及合作单位不承担任何责任。		
        			</div>
        		</div>   
        		<div>
        			<div class="div1">10.2</div>
        			<div class="div2">  
        					&nbsp;使用本“软件”可能存在来自他人匿名或冒名的含有威胁、诽谤、令人反感或非法内容的信息的风险，用户须明白并自行承担以上风险，灿越科技及合作单位不对任何有关信息真实性、适用性、合法性承担责任。		
        			</div>
        		</div>    
        		<div>
        			<div class="div1">10.3</div>
        			<div class="div2">  
        					&nbsp;用户因第三方如移动通讯运营商、网络运营商的通讯线路故障、技术问题、网络、电脑故障、系统不稳定性及其他各种不可抗力原因而遭受的一切损失，灿越科技及合作单位不承担责任。		
        			</div>
        		</div>    
        		<div>
        			<div class="div1">10.4</div>
        			<div class="div2">  
        					&nbsp;因技术故障等不可抗事件影响到服务的正常运行的，灿越科技及合作单位承诺在第一时间内与相关单位配合，及时处理进行修复，但用户因此而遭受的一切损失，灿越科技及合作单位不承担责任。		
        			</div>
        		</div>    
        		<div>
        			<div class="div1">10.5</div>
        			<div class="div2">  
        					&nbsp;用户之间通过本“软件”与其他用户交往，因受误导或欺骗而导致或可能导致的任何心理、生理上的伤害以及经济上的损失，由过错方依法承担所有责任，一概与灿越科技及合作单位无关。		
        			</div>
        		</div>             		
        		<p>
        			11	其他条款
        		</p>
        		<div>
        			<div class="div1">11.1</div>
        			<div class="div2">  
        					&nbsp;协议的完整性：本《协议》以及第三方授权软件或技术的使用协议和许可条款，共同构成了本“软件”及其支持服务的完整协议。		
        			</div>
        		</div>  
        		<div>
        			<div class="div1">11.2</div>
        			<div class="div2">  
        					&nbsp;本《协议》所定的任何条款的部分或全部无效者，不影响其它条款的效力。		
        			</div>
        		</div>  
        		<div>
        			<div class="div1">11.3</div>
        			<div class="div2">  
        					&nbsp;本《协议》的所有标题仅仅是为了醒目及阅读方便，本身并无实际涵义，不能作为本《协议》涵义解释之依据。		
        			</div>
        		</div>         		
        		<div>
        			<div class="div1">11.4</div>
        			<div class="div2">  
        					&nbsp;本《协议》签订地为杭州。本《协议》的解释、效力及纠纷的解决，适用于中华人民共和国法律。若用户和灿越科技之间发生任何纠纷或争议，首先应友好协商解决，协商不成的，用户在此完全同意将纠纷或争议提交灿越科技所在地有管辖权的人民法院管辖。		
        			</div>
        		</div>         		
        	</div>
        	<div class="tinue"><a href="javascript:close();" id="tinue">同意并继续</a></div>
    </div>
    <!-- 注册协议结束 -->  
    </div>
    <div class="fr frard">已经注册过？<a href="<sys:context/>/dcts/user/login.jsp">马上登录&nbsp;&gt;&gt;</a></div>
    <div class="both"></div>
<br />
</div>
</s:form>
<!-- 底部开始 -->
<jsp:include page="/bottomes.jsp" />
<!-- 底部结束 -->


</body>
</html>

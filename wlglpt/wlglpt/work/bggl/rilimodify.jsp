<%@ include file="/common/include.jsp"%>
<%@ include file="/common/meta.jsp"%>
<%@ page contentType="text/html;charset=GBK"%>
<html>
<HEAD>
<!-- <script language="javascript" src="include/service.js"></script> -->
<script language="javascript" src="<sys:context/>/resource/pageframe/js/common.js"></script>
<SCRIPT language=JavaScript>
<!--
//日期列表
var dateArray = new Array();
/*****************************************************************************
                                   日期资料
*****************************************************************************/

var lunarInfo=new Array(
0x04bd8,0x04ae0,0x0a570,0x054d5,0x0d260,0x0d950,0x16554,0x056a0,0x09ad0,0x055d2,
0x04ae0,0x0a5b6,0x0a4d0,0x0d250,0x1d255,0x0b540,0x0d6a0,0x0ada2,0x095b0,0x14977,
0x04970,0x0a4b0,0x0b4b5,0x06a50,0x06d40,0x1ab54,0x02b60,0x09570,0x052f2,0x04970,
0x06566,0x0d4a0,0x0ea50,0x06e95,0x05ad0,0x02b60,0x186e3,0x092e0,0x1c8d7,0x0c950,
0x0d4a0,0x1d8a6,0x0b550,0x056a0,0x1a5b4,0x025d0,0x092d0,0x0d2b2,0x0a950,0x0b557,
0x06ca0,0x0b550,0x15355,0x04da0,0x0a5d0,0x14573,0x052d0,0x0a9a8,0x0e950,0x06aa0,
0x0aea6,0x0ab50,0x04b60,0x0aae4,0x0a570,0x05260,0x0f263,0x0d950,0x05b57,0x056a0,
0x096d0,0x04dd5,0x04ad0,0x0a4d0,0x0d4d4,0x0d250,0x0d558,0x0b540,0x0b5a0,0x195a6,
0x095b0,0x049b0,0x0a974,0x0a4b0,0x0b27a,0x06a50,0x06d40,0x0af46,0x0ab60,0x09570,
0x04af5,0x04970,0x064b0,0x074a3,0x0ea50,0x06b58,0x055c0,0x0ab60,0x096d5,0x092e0,
0x0c960,0x0d954,0x0d4a0,0x0da50,0x07552,0x056a0,0x0abb7,0x025d0,0x092d0,0x0cab5,
0x0a950,0x0b4a0,0x0baa4,0x0ad50,0x055d9,0x04ba0,0x0a5b0,0x15176,0x052b0,0x0a930,
0x07954,0x06aa0,0x0ad50,0x05b52,0x04b60,0x0a6e6,0x0a4e0,0x0d260,0x0ea65,0x0d530,
0x05aa0,0x076a3,0x096d0,0x04bd7,0x04ad0,0x0a4d0,0x1d0b6,0x0d250,0x0d520,0x0dd45,
0x0b5a0,0x056d0,0x055b2,0x049b0,0x0a577,0x0a4b0,0x0aa50,0x1b255,0x06d20,0x0ada0)

var solarMonth=new Array(31,28,31,30,31,30,31,31,30,31,30,31);
var Gan=new Array("甲","乙","丙","丁","戊","己","庚","辛","壬","癸");
var Zhi=new Array("子","丑","寅","卯","辰","巳","午","未","申","酉","戌","亥");
var Animals=new Array("鼠","牛","虎","兔","龙","蛇","马","羊","猴","鸡","狗","猪");
var solarTerm = new Array("小寒","大寒","立春","雨水","惊蛰","春分","清明","谷雨","立夏","小满","芒种","夏至","小暑","大暑","立秋","处暑","白露","秋分","寒露","霜降","立冬","小雪","大雪","冬至")
var sTermInfo = new Array(0,21208,42467,63836,85337,107014,128867,150921,173149,195551,218072,240693,263343,285989,308563,331033,353350,375494,397447,419210,440795,462224,483532,504758)
var nStr1 = new Array('日','一','二','三','四','五','六','七','八','九','十')
var nStr2 = new Array('初','十','廿','卅','　')
var monthName = new Array("January","February","March","April","May","June","July","Auguest","September","October","November","December");
var monthChina = new Array("一月","二月","三月","四月","五月","六月","七月","八月","九月","十月","十一月","十二月");

//国历节日 *表示放假日
var sFtv = new Array(
"0101*元旦",
"0214 元旦",
"0303 元旦",
"0308 元旦",
"0312 元旦",
"0315 元旦",
"0317 元旦",
"0401 元旦",
"0501 元旦",
"0504 元旦",
"0512 元旦",
"0512 元旦",
"0601 元旦",
"0101 元旦",
"0214 元旦",
"0303 元旦",
"0308 元旦",
"0312 元旦",
"0315 元旦",
"0317 元旦",
"0401 元旦",
"0501 元旦",
"0504 元旦",
"0512 元旦",
"0512 元旦",
"0601 元旦",
"0614 元旦",
"1226 元旦")

//农历节日 *表示放假日
var lFtv = new Array(
"0101*春节",
"0115 元宵节",
"0505 端午节",
"0707 七夕情人节",
"0715 中元节",
"0815 中秋节",
"0909 重阳节",
"1208 腊八节",
"1224 小年",
"0100*除夕")

//某月的第几个星期几
var wFtv = new Array(
"0131 Martin Luther King Day",
"0231 President's Day",
"0520 母亲节",
"0530 Armed Forces Day",
"0531 Victoria Day",
"0716 合作节",
"0730 被奴役国家周",
"0811 Civic Holiday",
"0911 Labor Holiday",
"1021 Columbus Day",
"1144 Thanksgiving")


/*****************************************************************************
                                      日期计算
*****************************************************************************/

//====================================== 传回农历 y年的总天数
function lYearDays(y) {
   var i, sum = 348
   for(i=0x8000; i>0x8; i>>=1) sum += (lunarInfo[y-1900] & i)? 1: 0
   return(sum+leapDays(y))
}

//====================================== 传回农历 y年闰月的天数
function leapDays(y) {
   if(leapMonth(y))  return((lunarInfo[y-1900] & 0x10000)? 30: 29)
   else return(0)
}

//====================================== 传回农历 y年闰哪个月 1-12 , 没闰传回 0
function leapMonth(y) {
   return(lunarInfo[y-1900] & 0xf)
}

//====================================== 传回农历 y年m月的总天数
function monthDays(y,m) {
   return( (lunarInfo[y-1900] & (0x10000>>m))? 30: 29 )
}

//====================================== 算出农历, 传入日期物件, 传回农历日期物件
//                                       该物件属性有 .year .month .day .isLeap .yearCyl .dayCyl .monCyl
function Lunar(objDate) {

   var i, leap=0, temp=0
   var baseDate = new Date(1900,0,31)
   var offset   = (objDate - baseDate)/86400000

   this.dayCyl = offset + 40
   this.monCyl = 14

   for(i=1900; i<2050 && offset>0; i++) {
      temp = lYearDays(i)
      offset -= temp
      this.monCyl += 12
   }

   if(offset<0) {
      offset += temp;
      i--;
      this.monCyl -= 12
   }

   this.year = i
   this.yearCyl = i-1864

   leap = leapMonth(i) //闰哪个月
   this.isLeap = false

   for(i=1; i<13 && offset>0; i++) {
      //闰月
      if(leap>0 && i==(leap+1) && this.isLeap==false)
         { --i; this.isLeap = true; temp = leapDays(this.year); }
      else
         { temp = monthDays(this.year, i); }

      //解除闰月
      if(this.isLeap==true && i==(leap+1)) this.isLeap = false

      offset -= temp
      if(this.isLeap == false) this.monCyl ++
   }

   if(offset==0 && leap>0 && i==leap+1)
      if(this.isLeap)
         { this.isLeap = false; }
      else
         { this.isLeap = true; --i; --this.monCyl;}

   if(offset<0){ offset += temp; --i; --this.monCyl; }

   this.month = i
   this.day = offset + 1
}

//==============================传回国历 y年某m+1月的天数
function solarDays(y,m) {
   if(m==1)
      return(((y%4 == 0) && (y%100 != 0) || (y%400 == 0))? 29: 28)
   else
      return(solarMonth[m])
}
//============================== 传入 offset 传回干支, 0=甲子
function cyclical(num) {
   return(Gan[num%10]+Zhi[num%12])
}

//============================== 月历属性
function calElement(sYear,sMonth,sDay,week,lYear,lMonth,lDay,isLeap,cYear,cMonth,cDay) {

      this.isToday    = false;
      //国历
      this.sYear      = sYear;
      this.sMonth     = sMonth;
      this.sDay       = sDay;
      this.week       = week;
      //农历
      this.lYear      = lYear;
      this.lMonth     = lMonth;
      this.lDay       = lDay;
      this.isLeap     = isLeap;
      //干支
      this.cYear      = cYear;
      this.cMonth     = cMonth;
      this.cDay       = cDay;

      this.color      = '';

      this.lunarFestival = ''; //农历节日
      this.solarFestival = ''; //国历节日
      this.solarTerms    = ''; //节气

}

//===== 某年的第n个节气为几日(从0小寒起算)
function sTerm(y,n) {
   var offDate = new Date( ( 31556925974.7*(y-1900) + sTermInfo[n]*60000  ) + Date.UTC(1900,0,6,2,5) )
   return(offDate.getUTCDate())
}

//============================== 传回月历物件 (y年,m+1月)
function calendar(y,m) {

   var sDObj, lDObj, lY, lM, lD=1, lL, lX=0, tmp1, tmp2
   var lDPOS = new Array(3)
   var n = 0
   var firstLM = 0

   sDObj = new Date(y,m,1)            //当月一日日期

   this.length    = solarDays(y,m)    //国历当月天数
   this.firstWeek = sDObj.getDay()    //国历当月1日星期几


   for(var i=0;i<this.length;i++) {

      if(lD>lX) {
         sDObj = new Date(y,m,i+1)    //当月一日日期
         lDObj = new Lunar(sDObj)     //农历
         lY    = lDObj.year           //农历年
         lM    = lDObj.month          //农历月
         lD    = lDObj.day            //农历日
         lL    = lDObj.isLeap         //农历是否闰月
         lX    = lL? leapDays(lY): monthDays(lY,lM) //农历当月最後一天

         if(n==0) firstLM = lM
         lDPOS[n++] = i-lD+1
      }

      //sYear,sMonth,sDay,week,
      //lYear,lMonth,lDay,isLeap,
      //cYear,cMonth,cDay
      this[i] = new calElement(y, m+1, i+1, nStr1[(i+this.firstWeek)%7],
                               lY, lM, lD++, lL,
                               cyclical(lDObj.yearCyl) ,cyclical(lDObj.monCyl), cyclical(lDObj.dayCyl++) )


      if((i+this.firstWeek)%7==0)   this[i].color = 'black'  //周日颜色
      if((i+this.firstWeek)%14==13) this[i].color = 'black'  //周休二日颜色
   }

   //节气
   tmp1=sTerm(y,m*2  )-1
   tmp2=sTerm(y,m*2+1)-1
   this[tmp1].solarTerms = solarTerm[m*2]
   this[tmp2].solarTerms = solarTerm[m*2+1]
   if(m==3) this[tmp1].color = 'black' //清明颜色

   //国历节日
   for(i in sFtv)
      if(sFtv[i].match(/^(\d{2})(\d{2})([\s\*])(.+)$/))
         if(Number(RegExp.$1)==(m+1)) {
            this[Number(RegExp.$2)-1].solarFestival += RegExp.$4 + ' '
            if(RegExp.$3=='*') this[Number(RegExp.$2)-1].color = 'black'
         }

   //月周节日
   for(i in wFtv)
      if(wFtv[i].match(/^(\d{2})(\d)(\d)([\s\*])(.+)$/))
         if(Number(RegExp.$1)==(m+1)) {
            tmp1=Number(RegExp.$2)
            tmp2=Number(RegExp.$3)
            this[((this.firstWeek>tmp2)?7:0) + 7*(tmp1-1) + tmp2 - this.firstWeek].solarFestival += RegExp.$5 + ' '
         }

   //农历节日
   for(i in lFtv)
      if(lFtv[i].match(/^(\d{2})(.{2})([\s\*])(.+)$/)) {
         tmp1=Number(RegExp.$1)-firstLM
         if(tmp1==-11) tmp1=1
         if(tmp1 >=0 && tmp1<n) {
            tmp2 = lDPOS[tmp1] + Number(RegExp.$2) -1
            if( tmp2 >= 0 && tmp2<this.length) {
               this[tmp2].lunarFestival += RegExp.$4 + ' '
               if(RegExp.$3=='*') this[tmp2].color = 'black'
            }
         }
      }

   //黑色星期五
   if((this.firstWeek+12)%7==5)
      this[12].solarFestival += '黑色星期五 '

   //今日
   if(y==tY && m==tM) this[tD-1].isToday = true;

}

//====================== 中文日期
function cDay(d){
   var s;

   switch (d) {
      case 10:
         s = '初十'; break;
      case 20:
         s = '二十'; break;
         break;
      case 30:
         s = '三十'; break;
         break;
      default :
         s = nStr2[Math.floor(d/10)];
         s += nStr1[d%10];
   }
   return(s);
}
///////////////////////////////////////////////////////////////////////////////
var cld;
function drawCld(SY,SM) {
   var i,sD,s,size;
   cld = new calendar(SY,SM);
   if(SY>1874 && SY<1909) yDisplay = '光绪' + (((SY-1874)==1)?'元':SY-1874)
   if(SY>1908 && SY<1912) yDisplay = '宣统' + (((SY-1908)==1)?'元':SY-1908)
   if(SY>1911 && SY<1950) yDisplay = '民国' + (((SY-1911)==1)?'元':SY-1911)
   if(SY>1949) yDisplay = ''
   GZ.innerHTML = yDisplay +' 农历' + cyclical(SY-1900+36) + '年 &nbsp;&nbsp;【'+Animals[(SY-4)%12]+'】';
   YMBG.innerHTML = "&nbsp;" + SY + "&nbsp;" + monthName[SM];
   YMBG2.innerHTML = "&nbsp;" + SY + "&nbsp;" + monthChina[SM];
   for(i=0;i<42;i++) {
      sObj=eval('SD'+ i);	//id=SD
      lObj=eval('LD'+ i);	//id=LD
	    gdbj=eval('GD'+i);
      sObj.className = '';
      sD = i - cld.firstWeek;
      if(sD>-1 && sD<cld.length) { //日期内
		     sObj.innerHTML = (sD+1);//原来的情况
         if(cld[sD].isToday) sObj.className = 'todyaColor'; //今日颜色

         sObj.style.color = cld[sD].color; //国定假日颜色

         if(cld[sD].lDay==1) //显示农历月
            lObj.innerHTML = '<b>'+(cld[sD].isLeap?'闰':'') + cld[sD].lMonth + '月';
         else //显示农历日
            lObj.innerHTML = cDay(cld[sD].lDay);
         s=cld[sD].lunarFestival;
         if(s.length>0) { //农历节日
			lObj.innerHTML = cDay(cld[sD].lDay);
				        }
         else { //国历节日
            lObj.innerHTML = cDay(cld[sD].lDay);
         }
         if(s.length>10) lObj.innerHTML = s;
         //存在记录的日期
         flag = 0;
          if(dateArray.length != 0){
            for(var tempI = 0 ; tempI < dateArray.length; tempI++){
               if(sD == dateArray[tempI]){
                  flag = 1;
                  break;
               }
            }
          }
          if(flag==0)
            gdbj.className = 'noneRecordColor';
          else
            gdbj.className = 'recordColor';
      }
      else { //非日期
         sObj.innerHTML = '';
         lObj.innerHTML = '';
         gdbj.className = 'noneRecordColor';
      }
   }
}

function changeLong()
{
var y,m,ly,lm,id,im,iy,yangy,yangm,deltm,miny,tt;
CLD.SY.selectedIndex=CLD.D1.selectedIndex;
CLD.SM.selectedIndex=CLD.D2.selectedIndex;
yangm=0;yangy=0;
tt=true;
while (tt)
	{
	yangm=0;yangy=0;
			changeCld();
		   for(i=0;i<42;i++)
		   {
		      sD = i - cld.firstWeek;
		      if(sD>-1 && sD<cld.length)
		      { //日期内
			      if ((cld[sD].lMonth==CLD.D2.selectedIndex+1)&&(cld[sD].lYear==CLD.D1.selectedIndex+1900))
			      {
					yangy=CLD.SY.selectedIndex+1900; 	yangm=CLD.SM.selectedIndex ;
					tt=false;
					break;
					}
	      		}
		   }
		   if (!tt) break;

			pushBtm('MD');
			changeCld();

//			alert(CLD.SY.selectedIndex+" "+CLD.SM.selectedIndex);

		   for(i=0;i<42;i++)
		   {
		      sD = i - cld.firstWeek;
		      if(sD>-1 && sD<cld.length)
		      { //日期内
			      if ((cld[sD].lMonth==CLD.D2.selectedIndex+1)&&(cld[sD].lYear==CLD.D1.selectedIndex+1900))
			      {
					yangy=CLD.SY.selectedIndex+1900; 	yangm=CLD.SM.selectedIndex ;
					tt=false;
					break;
					}
	      		}
		   }
			break;
	}
//		alert(yangy+" "+yangm);


//CLD.SY.selectedIndex=yangy;//-1900;
//pushBtm('YU');
//pushBtm('YD');
CLD.SM.selectedIndex=yangm;
pushBtm('MD');
pushBtm('MU');

}
//changeLong end

function changeCld() {
   var y,m;
   y=CLD.SY.selectedIndex+1900;
   m=CLD.SM.selectedIndex;
   drawCld(y,m);
}

function pushBtm(K) {
	
   switch (K){
      case 'YU' :
         if(CLD.SY.selectedIndex>0) CLD.SY.selectedIndex--;
         break;
      case 'YD' :
         if(CLD.SY.selectedIndex<149) CLD.SY.selectedIndex++;
         break;
      case 'MU' :
         if(CLD.SM.selectedIndex>0) {
            CLD.SM.selectedIndex--;
         }
         else {
            CLD.SM.selectedIndex=11;
            if(CLD.SY.selectedIndex>0) CLD.SY.selectedIndex--;
         }
         break;
      case 'MD' :
         if(CLD.SM.selectedIndex<11) {
            CLD.SM.selectedIndex++;
         }
         else {
            CLD.SM.selectedIndex=0;
            if(CLD.SY.selectedIndex<149) CLD.SY.selectedIndex++;
         }
         break;
      default :
         CLD.SY.selectedIndex=tY-1900;
         CLD.SM.selectedIndex=tM;
   }
   datechange();
}

var Today = new Date();
//var strDate=window.document.CLD.curDate.value;
var strDate="2005-09-08";
//alert("date info:"+strDate);
var arrDate=strDate.split("-");


var tY = Today.getFullYear();
var tM = Today.getMonth();
var tD = Today.getDate();

//var tY = 2005;
//var tM = 8;
//var tD = 29;




//////////////////////////////////////////////////////////////////////////////

var width = "130";
var offsetx = 2;
var offsety = 16;

var x = 0;
var y = 0;
var snow = 0;
var sw = 0;
var cnt = 0;

var dStyle;
document.onmousemove = mEvn;

//显示详细日期资料
//清除详细日期资料
function mOut() {
	if ( cnt >= 1 ) { sw = 0 }
	if ( sw == 0 ) { snow = 0;	dStyle.visibility = "hidden";}
	else cnt++;
}

//取得位置
function mEvn() {
   x=event.x;
   y=event.y;
	if (document.body.scrollLeft)
	   {x=event.x+document.body.scrollLeft; y=event.y+document.body.scrollTop;}
	if (snow){
      dStyle.left = x+offsetx-(width/2)
      dStyle.top = y+offsety
	}
}

///////////////////////////////////////////////////////////////////////////

function tick() {
   var today
   today = new Date()
   Clock.innerHTML = today.toLocaleString().replace(/(年|月)/g, "/").replace(/日/, "");
//   Clock.innerHTML = TimeAdd(today.toGMTString(), CLD.TZ.value)
   window.setTimeout("tick()", 1000);
}

function setCookie(name, value) {
	var today = new Date()
	var expires = new Date()
	expires.setTime(today.getTime() + 1000*60*60*24*365)
	document.cookie = name + "=" + escape(value)	+ "; expires=" + expires.toGMTString()
}

function getCookie(Name) {
   var search = Name + "="
   if(document.cookie.length > 0) {
      offset = document.cookie.indexOf(search)
      if(offset != -1) {
         offset += search.length
         end = document.cookie.indexOf(";", offset)
         if(end == -1) end = document.cookie.length
         return unescape(document.cookie.substring(offset, end))
      }
      else return ""
   }
}

/////////////////////////////////////////////////////////

function initial() {
   dStyle = detail.style;
   CLD.SY.selectedIndex=tY-1900;
   CLD.SM.selectedIndex=tM;
   drawCld(tY,tM);
   tick();
}
//查询
function onQuery(gNum){
    //showMessage("query");
    var tempYear = CLD.SY.selectedIndex+1900;
    var tempMonth = CLD.SM.selectedIndex;
    var tempCalendar = new calendar(tempYear,tempMonth);
    var cxrq;
    tempMonth = tempMonth+1;
    if(tempMonth<10){
        tempMonth = "0"+tempMonth;
    }
    var tempDay = gNum-tempCalendar.firstWeek;
    if(tempDay<10){
      tempDay = "0"+tempDay;
    }
    
    cxrq = tempYear+"-"+tempMonth+"-"+tempDay;
    
    popwindow(jcontextPath+"/bggl/bgrcap!initMx?domain.cxrq="+cxrq);
}
//月份变动
function datechange(){
    var tempYear = CLD.SY.selectedIndex+1900;
    var tempMonth = CLD.SM.selectedIndex;
    var tempCalendar = new calendar(tempYear,tempMonth);
    tempMonth = tempMonth+1;
    if(tempMonth<10){
        tempMonth = "0"+tempMonth;
    }
    var date = tempYear+"-"+tempMonth;
    
	var url = jcontextPath+"/bgrcap!queryByDate";  
	var jsonObj = {"domain.changeDate":date};   			
	ajaxCommon(url,jsonObj,"oDownloadDone",false);
    
}
function oDownloadDone(data){

    var tempDate = data.domain.dataList;
    dateArray = new Array();
    if(tempDate != ""){
        //获取日期数组
        for(var i = 0; i < (tempDate.length); i++){
            dateArray[i] = tempDate[i].rq;
        }
        //获取年月
        tempDate = dateArray[0];
        tempYear =  tempDate.substring(0,4);
        tempMonth =  tempDate.substring(5,7) - 1;
        var tempCalendar = new calendar(tempYear, tempMonth);
        //计算与显示匹配的数字
        for(var i = 0; i < dateArray.length; i++){
            tempDay = dateArray[i].substring(8,10);
            dateArray[i] = tempDay*1 - 1 //+ tempCalendar.firstWeek*1;
        }
    }
    //重新画图
    changeCld();
}
//获取后台日期列表 - init
function getRecordDateList(){
    
    var tempYear = CLD.SY.selectedIndex+1900;
    var tempMonth = CLD.SM.selectedIndex;
    var tempCalendar = new calendar(tempYear,tempMonth);
    tempMonth = tempMonth+1;
    if(tempMonth<10){
        tempMonth = "0"+tempMonth;
    }
    
    var date = tempYear+"-"+tempMonth;
    
    //判断是否为初始化(CLD.SY.selectedIndex没有加载，获取不到值)
	if(tempYear == 1900){
		date = "";
	}
	
    var url = jcontextPath+"/bgrcap!queryByDate";  
	var jsonObj = {"domain.changeDate":date};   			
	ajaxCommon(url,jsonObj,"initDateList",false);
	
	/**
    theSelect = document.getElementById("tempRqId");
    if(theSelect.length > 0){
        //获取日期数组
        for(var i = 0; i < theSelect.length; i++){
            dateArray[i] = theSelect.options[i].value;
        }
        //获取年月
        tempDate = dateArray[0];
        tempYear =  tempDate.substring(0,4);
        tempMonth =  tempDate.substring(5,7) - 1;
        var tempCalendar = new calendar(tempYear, tempMonth);
        //计算与显示匹配的数字
        for(var i = 0; i < dateArray.length; i++){
            tempDay = dateArray[i].substring(8,10);
            dateArray[i] = tempDay*1 -1 //+ tempCalendar.firstWeek*1;
        }
    }**/
}

function initDateList(data){
	var tempDate = data.domain.dataList;
    if(tempDate !=""){
        //获取日期数组
        for(var i = 0; i < tempDate.length; i++){
            dateArray[i] = tempDate[i].rq;
        }
        //获取年月
        tempDate = dateArray[0];
        tempYear =  tempDate.substring(0,4);
        tempMonth =  tempDate.substring(5,7) - 1;
        var tempCalendar = new calendar(tempYear, tempMonth);
        //计算与显示匹配的数字
        for(var i = 0; i < dateArray.length; i++){
            tempDay = dateArray[i].substring(8,10);
            dateArray[i] = tempDay*1 -1 //+ tempCalendar.firstWeek*1;
        }
    }
    //重新画图
    changeCld();
}
//-->
</SCRIPT>

<STYLE>.todyaColor {
  BACKGROUND-COLOR: red
}
.recordColor {
  cursor:pointer;background-color:lightsteelblue;
}
.noneRecordColor {
}
</STYLE>

<s:form action="bggl/bgrcap!query" namespace="" method="post" id="mainForm" name="mainForm">
<s:hidden name="domain.cxrq" />
</s:form>
<META content="Microsoft FrontPage 6.0" name=GENERATOR>
</HEAD>
<body onload=initial()>
<a STYLE="behavior:url('#default#download')" ID='oDownload'>
<SCRIPT language=JavaScript>
  <!--
     if(navigator.appName == "Netscape" || parseInt(navigator.appVersion) < 4)
     document.write("<h1>你的浏览器无法执行此程序。</h1>此程序在 IE4 以后的版本才能执行!!")
  //-->
</SCRIPT>
<br>
<br>

<DIV id=detail style="POSITION: absolute"></DIV>

<FORM name="CLD">
<input type="hidden" name="curDate" value="2005-09-08"/>

<div align="center">
<TABLE width="656" align="center" height="315" bordercolordark="#CC9900" border="4" background="l11.gif">
<TBODY>
<TR>
<TD align=middle vAlign=top width="8" height="303" style="display:none">

<div align="center">
  <center>
    <TABLE BORDER=1 COLSPAN=2 width="100%" style="display:none">
      <td width="100%" height="48" valign="middle">

      <div align="center"><font style="font-size:10.8pt" color=red>本地计算机时间:</FONT><BR>
        <FONT color=#000080 face=ARIAL id=Clock size=3 align="center"></FONT>
        <!--时区 *表示自动调整为日光节约时间-->
      </div>
    </TABLE>
  </center>
</div>

<div align="center">
  <center>
    <table border=1 height="1" width="100%" style="display:none">
      <td height="1">
      <table width="100%" height="1" style="display:none">
        <center>
          <center>
            <font id=tSave
                  style="COLOR: black; FONT-FAMILY: Wingdings; FONT-SIZE: 18pt"></font>
            <td width="155" height="1" valign="top"><font size="2"><font style="FONT-SIZE: 9pt"><font color="#0000FF">公历</font>年</font><font color=#ffffff
                                                                                                                                             style="FONT-SIZE: 9pt">
              <select name=SY onChange=changeCld()
                      style="FONT-SIZE: 9pt">
                <script language=JavaScript><!--
                    for(i=1900;i<2050;i++) document.write('<option>'+i)
                //--></script>
              </select>
            </font>月<font color=#ffffff
                          style="FONT-SIZE: 9pt">
              <select name=SM onChange=changeCld()
                      style="FONT-SIZE: 9pt">
                <script language=JavaScript><!--
                    for(i=1;i<13;i++) document.write('<option>'+i)
                //--></script>
              </select>
            </font></font>
            <td width="75" height="1" align="center" valign="middle">
              <div align="right">
                <button onClick="pushBtm('MU')" style="FONT-SIZE: 9pt">月↑</button>
                <button onClick="pushBtm('MD')" style="FONT-SIZE: 9pt">月↓</button>
              </div></td>
          </center>
        </center>
        <tr>
        <center>
          <td align=center valign=top height="1" colspan="2">
            <button onClick="pushBtm('YU')" style="FONT-SIZE: 9pt">年↑</button>
            <button onClick="pushBtm('YD')" style="FONT-SIZE: 9pt">
              <div align="center">年↓</div>
            </button>
          </td>
        </center>
      </table>
      <center>
        <table border="1" width="100%" height="1" bordercolordark="#FFFFCC">
          <tr>
            <td width="100%" height="1"><font style="font-size:10.8pt" color=#0> 　&nbsp; 阴历存在跨月情况，如果查找到的阴历月跨两个公历月，用<font color=blue>[月↑][月↓]</font>翻动可以找到你想要的确切日期。</font></td>
          </tr>
        </table>
      </center>
    </table>
  </center>
</div>
<TD width="660" height="303" align=middle valign="top">
  <DIV style="position: absolute; top: 76px; z-index: 1; left: 447px; width: 347;height: 108;">
    <p><FONT id=YMBG style="COLOR: #D0D0FF; FONT-FAMILY: 'Arial Black'; FONT-SIZE: 20pt">&nbsp;</FONT>
  </DIV>
  <TABLE width="100%" height="27" border=0 align="center" cellspacing="1" bgcolor="#FFFFFF">
    <TBODY>
      <TR>
        <TD bgColor=#000080 colSpan=7 align="center" height="24">
          <table width="100%" border="0" cellspacing="0" cellpadding="0">
            <tr>
              <td width="6%"><button onClick="pushBtm('MU')" STYLE="height:20; font:9pt; BORDER-BOTTOM: #cccccc 1px groove; BORDER-RIGHT: #cccccc 1px groove; BACKGROUND-COLOR: #eeeeee">月↑</button></td>
              <td width="87%"><center><FONT id=YMBG2 color="#ffffff"></FONT>&nbsp;&nbsp;&nbsp;<FONT id=GZ color="#ffffff"></FONT></center></td>
              <td width="7%"><div align="right"><button onClick="pushBtm('MD')" STYLE="height:20; font:9pt; BORDER-BOTTOM: #cccccc 1px groove; BORDER-RIGHT: #cccccc 1px groove; BACKGROUND-COLOR: #eeeeee">月↓</button></div></td>
            </tr>
          </table>
        </TD></TR>
      <TR align=middle bgColor=#e0e0e0>
        <TD height="23" align="center" bgcolor="#99CCFF"><font size="2">日</font></TD>
        <TD height="23" align="center" bgcolor="#99CCFF"><font size="2">一</font></TD>
        <TD height="23" align="center" bgcolor="#99CCFF"><font size="2">二</font></TD>
        <TD height="23" align="center" bgcolor="#99CCFF"><font size="2">三</font></TD>
        <TD height="23" align="center" bgcolor="#99CCFF"><font size="2">四</font></TD>
        <TD height="23" align="center" bgcolor="#99CCFF"><font size="2">五</font></TD>
        <TD height="23" align="center" bgcolor="#99CCFF"><font size="2">六</font></TD>
      </TR>
      <SCRIPT language=JavaScript><!--
            getRecordDateList();
            var gNum;
            var jsNum;
            var flag ;
            for(i=0;i<6;i++) {
               document.write('<tr align=center>');
               for(j=0;j<7;j++) {
                  gNum = i*7+j;
                  document.write('<td id="GD' + gNum +'"  onMouseOver="" onMouseOut="mOut()">');
                  document.write('<a href="javascript:onQuery('+(i*7+j+1)+')">');
                  document.write('<font id="SD' + gNum +'" size=2 style="font-size:12pt" face="Arial Black" color="black"');
                  document.write(' TITLE=""> </font><br><font id="LD' + gNum + '" size=2 style="font-size:9pt" color="black"> </font>');
                  document.write("</a>");
                  document.write("</td>");
               }
               document.write('</tr>');
            }
            //--></SCRIPT>
    </TBODY></TABLE>
</TD>
</TR></TBODY></TABLE>
</div>
</FORM>
</body>
</html>
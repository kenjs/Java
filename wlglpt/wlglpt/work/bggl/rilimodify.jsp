<%@ include file="/common/include.jsp"%>
<%@ include file="/common/meta.jsp"%>
<%@ page contentType="text/html;charset=GBK"%>
<html>
<HEAD>
<!-- <script language="javascript" src="include/service.js"></script> -->
<script language="javascript" src="<sys:context/>/resource/pageframe/js/common.js"></script>
<SCRIPT language=JavaScript>
<!--
//�����б�
var dateArray = new Array();
/*****************************************************************************
                                   ��������
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
var Gan=new Array("��","��","��","��","��","��","��","��","��","��");
var Zhi=new Array("��","��","��","î","��","��","��","δ","��","��","��","��");
var Animals=new Array("��","ţ","��","��","��","��","��","��","��","��","��","��");
var solarTerm = new Array("С��","��","����","��ˮ","����","����","����","����","����","С��","â��","����","С��","����","����","����","��¶","���","��¶","˪��","����","Сѩ","��ѩ","����")
var sTermInfo = new Array(0,21208,42467,63836,85337,107014,128867,150921,173149,195551,218072,240693,263343,285989,308563,331033,353350,375494,397447,419210,440795,462224,483532,504758)
var nStr1 = new Array('��','һ','��','��','��','��','��','��','��','��','ʮ')
var nStr2 = new Array('��','ʮ','إ','ئ','��')
var monthName = new Array("January","February","March","April","May","June","July","Auguest","September","October","November","December");
var monthChina = new Array("һ��","����","����","����","����","����","����","����","����","ʮ��","ʮһ��","ʮ����");

//�������� *��ʾ�ż���
var sFtv = new Array(
"0101*Ԫ��",
"0214 Ԫ��",
"0303 Ԫ��",
"0308 Ԫ��",
"0312 Ԫ��",
"0315 Ԫ��",
"0317 Ԫ��",
"0401 Ԫ��",
"0501 Ԫ��",
"0504 Ԫ��",
"0512 Ԫ��",
"0512 Ԫ��",
"0601 Ԫ��",
"0101 Ԫ��",
"0214 Ԫ��",
"0303 Ԫ��",
"0308 Ԫ��",
"0312 Ԫ��",
"0315 Ԫ��",
"0317 Ԫ��",
"0401 Ԫ��",
"0501 Ԫ��",
"0504 Ԫ��",
"0512 Ԫ��",
"0512 Ԫ��",
"0601 Ԫ��",
"0614 Ԫ��",
"1226 Ԫ��")

//ũ������ *��ʾ�ż���
var lFtv = new Array(
"0101*����",
"0115 Ԫ����",
"0505 �����",
"0707 ��Ϧ���˽�",
"0715 ��Ԫ��",
"0815 �����",
"0909 ������",
"1208 ���˽�",
"1224 С��",
"0100*��Ϧ")

//ĳ�µĵڼ������ڼ�
var wFtv = new Array(
"0131 Martin Luther King Day",
"0231 President's Day",
"0520 ĸ�׽�",
"0530 Armed Forces Day",
"0531 Victoria Day",
"0716 ������",
"0730 ��ū�۹�����",
"0811 Civic Holiday",
"0911 Labor Holiday",
"1021 Columbus Day",
"1144 Thanksgiving")


/*****************************************************************************
                                      ���ڼ���
*****************************************************************************/

//====================================== ����ũ�� y���������
function lYearDays(y) {
   var i, sum = 348
   for(i=0x8000; i>0x8; i>>=1) sum += (lunarInfo[y-1900] & i)? 1: 0
   return(sum+leapDays(y))
}

//====================================== ����ũ�� y�����µ�����
function leapDays(y) {
   if(leapMonth(y))  return((lunarInfo[y-1900] & 0x10000)? 30: 29)
   else return(0)
}

//====================================== ����ũ�� y�����ĸ��� 1-12 , û�򴫻� 0
function leapMonth(y) {
   return(lunarInfo[y-1900] & 0xf)
}

//====================================== ����ũ�� y��m�µ�������
function monthDays(y,m) {
   return( (lunarInfo[y-1900] & (0x10000>>m))? 30: 29 )
}

//====================================== ���ũ��, �����������, ����ũ���������
//                                       ����������� .year .month .day .isLeap .yearCyl .dayCyl .monCyl
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

   leap = leapMonth(i) //���ĸ���
   this.isLeap = false

   for(i=1; i<13 && offset>0; i++) {
      //����
      if(leap>0 && i==(leap+1) && this.isLeap==false)
         { --i; this.isLeap = true; temp = leapDays(this.year); }
      else
         { temp = monthDays(this.year, i); }

      //�������
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

//==============================���ع��� y��ĳm+1�µ�����
function solarDays(y,m) {
   if(m==1)
      return(((y%4 == 0) && (y%100 != 0) || (y%400 == 0))? 29: 28)
   else
      return(solarMonth[m])
}
//============================== ���� offset ���ظ�֧, 0=����
function cyclical(num) {
   return(Gan[num%10]+Zhi[num%12])
}

//============================== ��������
function calElement(sYear,sMonth,sDay,week,lYear,lMonth,lDay,isLeap,cYear,cMonth,cDay) {

      this.isToday    = false;
      //����
      this.sYear      = sYear;
      this.sMonth     = sMonth;
      this.sDay       = sDay;
      this.week       = week;
      //ũ��
      this.lYear      = lYear;
      this.lMonth     = lMonth;
      this.lDay       = lDay;
      this.isLeap     = isLeap;
      //��֧
      this.cYear      = cYear;
      this.cMonth     = cMonth;
      this.cDay       = cDay;

      this.color      = '';

      this.lunarFestival = ''; //ũ������
      this.solarFestival = ''; //��������
      this.solarTerms    = ''; //����

}

//===== ĳ��ĵ�n������Ϊ����(��0С������)
function sTerm(y,n) {
   var offDate = new Date( ( 31556925974.7*(y-1900) + sTermInfo[n]*60000  ) + Date.UTC(1900,0,6,2,5) )
   return(offDate.getUTCDate())
}

//============================== ����������� (y��,m+1��)
function calendar(y,m) {

   var sDObj, lDObj, lY, lM, lD=1, lL, lX=0, tmp1, tmp2
   var lDPOS = new Array(3)
   var n = 0
   var firstLM = 0

   sDObj = new Date(y,m,1)            //����һ������

   this.length    = solarDays(y,m)    //������������
   this.firstWeek = sDObj.getDay()    //��������1�����ڼ�


   for(var i=0;i<this.length;i++) {

      if(lD>lX) {
         sDObj = new Date(y,m,i+1)    //����һ������
         lDObj = new Lunar(sDObj)     //ũ��
         lY    = lDObj.year           //ũ����
         lM    = lDObj.month          //ũ����
         lD    = lDObj.day            //ũ����
         lL    = lDObj.isLeap         //ũ���Ƿ�����
         lX    = lL? leapDays(lY): monthDays(lY,lM) //ũ����������һ��

         if(n==0) firstLM = lM
         lDPOS[n++] = i-lD+1
      }

      //sYear,sMonth,sDay,week,
      //lYear,lMonth,lDay,isLeap,
      //cYear,cMonth,cDay
      this[i] = new calElement(y, m+1, i+1, nStr1[(i+this.firstWeek)%7],
                               lY, lM, lD++, lL,
                               cyclical(lDObj.yearCyl) ,cyclical(lDObj.monCyl), cyclical(lDObj.dayCyl++) )


      if((i+this.firstWeek)%7==0)   this[i].color = 'black'  //������ɫ
      if((i+this.firstWeek)%14==13) this[i].color = 'black'  //���ݶ�����ɫ
   }

   //����
   tmp1=sTerm(y,m*2  )-1
   tmp2=sTerm(y,m*2+1)-1
   this[tmp1].solarTerms = solarTerm[m*2]
   this[tmp2].solarTerms = solarTerm[m*2+1]
   if(m==3) this[tmp1].color = 'black' //������ɫ

   //��������
   for(i in sFtv)
      if(sFtv[i].match(/^(\d{2})(\d{2})([\s\*])(.+)$/))
         if(Number(RegExp.$1)==(m+1)) {
            this[Number(RegExp.$2)-1].solarFestival += RegExp.$4 + ' '
            if(RegExp.$3=='*') this[Number(RegExp.$2)-1].color = 'black'
         }

   //���ܽ���
   for(i in wFtv)
      if(wFtv[i].match(/^(\d{2})(\d)(\d)([\s\*])(.+)$/))
         if(Number(RegExp.$1)==(m+1)) {
            tmp1=Number(RegExp.$2)
            tmp2=Number(RegExp.$3)
            this[((this.firstWeek>tmp2)?7:0) + 7*(tmp1-1) + tmp2 - this.firstWeek].solarFestival += RegExp.$5 + ' '
         }

   //ũ������
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

   //��ɫ������
   if((this.firstWeek+12)%7==5)
      this[12].solarFestival += '��ɫ������ '

   //����
   if(y==tY && m==tM) this[tD-1].isToday = true;

}

//====================== ��������
function cDay(d){
   var s;

   switch (d) {
      case 10:
         s = '��ʮ'; break;
      case 20:
         s = '��ʮ'; break;
         break;
      case 30:
         s = '��ʮ'; break;
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
   if(SY>1874 && SY<1909) yDisplay = '����' + (((SY-1874)==1)?'Ԫ':SY-1874)
   if(SY>1908 && SY<1912) yDisplay = '��ͳ' + (((SY-1908)==1)?'Ԫ':SY-1908)
   if(SY>1911 && SY<1950) yDisplay = '���' + (((SY-1911)==1)?'Ԫ':SY-1911)
   if(SY>1949) yDisplay = ''
   GZ.innerHTML = yDisplay +' ũ��' + cyclical(SY-1900+36) + '�� &nbsp;&nbsp;��'+Animals[(SY-4)%12]+'��';
   YMBG.innerHTML = "&nbsp;" + SY + "&nbsp;" + monthName[SM];
   YMBG2.innerHTML = "&nbsp;" + SY + "&nbsp;" + monthChina[SM];
   for(i=0;i<42;i++) {
      sObj=eval('SD'+ i);	//id=SD
      lObj=eval('LD'+ i);	//id=LD
	    gdbj=eval('GD'+i);
      sObj.className = '';
      sD = i - cld.firstWeek;
      if(sD>-1 && sD<cld.length) { //������
		     sObj.innerHTML = (sD+1);//ԭ�������
         if(cld[sD].isToday) sObj.className = 'todyaColor'; //������ɫ

         sObj.style.color = cld[sD].color; //����������ɫ

         if(cld[sD].lDay==1) //��ʾũ����
            lObj.innerHTML = '<b>'+(cld[sD].isLeap?'��':'') + cld[sD].lMonth + '��';
         else //��ʾũ����
            lObj.innerHTML = cDay(cld[sD].lDay);
         s=cld[sD].lunarFestival;
         if(s.length>0) { //ũ������
			lObj.innerHTML = cDay(cld[sD].lDay);
				        }
         else { //��������
            lObj.innerHTML = cDay(cld[sD].lDay);
         }
         if(s.length>10) lObj.innerHTML = s;
         //���ڼ�¼������
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
      else { //������
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
		      { //������
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
		      { //������
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

//��ʾ��ϸ��������
//�����ϸ��������
function mOut() {
	if ( cnt >= 1 ) { sw = 0 }
	if ( sw == 0 ) { snow = 0;	dStyle.visibility = "hidden";}
	else cnt++;
}

//ȡ��λ��
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
   Clock.innerHTML = today.toLocaleString().replace(/(��|��)/g, "/").replace(/��/, "");
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
//��ѯ
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
//�·ݱ䶯
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
        //��ȡ��������
        for(var i = 0; i < (tempDate.length); i++){
            dateArray[i] = tempDate[i].rq;
        }
        //��ȡ����
        tempDate = dateArray[0];
        tempYear =  tempDate.substring(0,4);
        tempMonth =  tempDate.substring(5,7) - 1;
        var tempCalendar = new calendar(tempYear, tempMonth);
        //��������ʾƥ�������
        for(var i = 0; i < dateArray.length; i++){
            tempDay = dateArray[i].substring(8,10);
            dateArray[i] = tempDay*1 - 1 //+ tempCalendar.firstWeek*1;
        }
    }
    //���»�ͼ
    changeCld();
}
//��ȡ��̨�����б� - init
function getRecordDateList(){
    
    var tempYear = CLD.SY.selectedIndex+1900;
    var tempMonth = CLD.SM.selectedIndex;
    var tempCalendar = new calendar(tempYear,tempMonth);
    tempMonth = tempMonth+1;
    if(tempMonth<10){
        tempMonth = "0"+tempMonth;
    }
    
    var date = tempYear+"-"+tempMonth;
    
    //�ж��Ƿ�Ϊ��ʼ��(CLD.SY.selectedIndexû�м��أ���ȡ����ֵ)
	if(tempYear == 1900){
		date = "";
	}
	
    var url = jcontextPath+"/bgrcap!queryByDate";  
	var jsonObj = {"domain.changeDate":date};   			
	ajaxCommon(url,jsonObj,"initDateList",false);
	
	/**
    theSelect = document.getElementById("tempRqId");
    if(theSelect.length > 0){
        //��ȡ��������
        for(var i = 0; i < theSelect.length; i++){
            dateArray[i] = theSelect.options[i].value;
        }
        //��ȡ����
        tempDate = dateArray[0];
        tempYear =  tempDate.substring(0,4);
        tempMonth =  tempDate.substring(5,7) - 1;
        var tempCalendar = new calendar(tempYear, tempMonth);
        //��������ʾƥ�������
        for(var i = 0; i < dateArray.length; i++){
            tempDay = dateArray[i].substring(8,10);
            dateArray[i] = tempDay*1 -1 //+ tempCalendar.firstWeek*1;
        }
    }**/
}

function initDateList(data){
	var tempDate = data.domain.dataList;
    if(tempDate !=""){
        //��ȡ��������
        for(var i = 0; i < tempDate.length; i++){
            dateArray[i] = tempDate[i].rq;
        }
        //��ȡ����
        tempDate = dateArray[0];
        tempYear =  tempDate.substring(0,4);
        tempMonth =  tempDate.substring(5,7) - 1;
        var tempCalendar = new calendar(tempYear, tempMonth);
        //��������ʾƥ�������
        for(var i = 0; i < dateArray.length; i++){
            tempDay = dateArray[i].substring(8,10);
            dateArray[i] = tempDay*1 -1 //+ tempCalendar.firstWeek*1;
        }
    }
    //���»�ͼ
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
     document.write("<h1>���������޷�ִ�д˳���</h1>�˳����� IE4 �Ժ�İ汾����ִ��!!")
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

      <div align="center"><font style="font-size:10.8pt" color=red>���ؼ����ʱ��:</FONT><BR>
        <FONT color=#000080 face=ARIAL id=Clock size=3 align="center"></FONT>
        <!--ʱ�� *��ʾ�Զ�����Ϊ�չ��Լʱ��-->
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
            <td width="155" height="1" valign="top"><font size="2"><font style="FONT-SIZE: 9pt"><font color="#0000FF">����</font>��</font><font color=#ffffff
                                                                                                                                             style="FONT-SIZE: 9pt">
              <select name=SY onChange=changeCld()
                      style="FONT-SIZE: 9pt">
                <script language=JavaScript><!--
                    for(i=1900;i<2050;i++) document.write('<option>'+i)
                //--></script>
              </select>
            </font>��<font color=#ffffff
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
                <button onClick="pushBtm('MU')" style="FONT-SIZE: 9pt">�¡�</button>
                <button onClick="pushBtm('MD')" style="FONT-SIZE: 9pt">�¡�</button>
              </div></td>
          </center>
        </center>
        <tr>
        <center>
          <td align=center valign=top height="1" colspan="2">
            <button onClick="pushBtm('YU')" style="FONT-SIZE: 9pt">���</button>
            <button onClick="pushBtm('YD')" style="FONT-SIZE: 9pt">
              <div align="center">���</div>
            </button>
          </td>
        </center>
      </table>
      <center>
        <table border="1" width="100%" height="1" bordercolordark="#FFFFCC">
          <tr>
            <td width="100%" height="1"><font style="font-size:10.8pt" color=#0> ��&nbsp; �������ڿ��������������ҵ��������¿����������£���<font color=blue>[�¡�][�¡�]</font>���������ҵ�����Ҫ��ȷ�����ڡ�</font></td>
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
              <td width="6%"><button onClick="pushBtm('MU')" STYLE="height:20; font:9pt; BORDER-BOTTOM: #cccccc 1px groove; BORDER-RIGHT: #cccccc 1px groove; BACKGROUND-COLOR: #eeeeee">�¡�</button></td>
              <td width="87%"><center><FONT id=YMBG2 color="#ffffff"></FONT>&nbsp;&nbsp;&nbsp;<FONT id=GZ color="#ffffff"></FONT></center></td>
              <td width="7%"><div align="right"><button onClick="pushBtm('MD')" STYLE="height:20; font:9pt; BORDER-BOTTOM: #cccccc 1px groove; BORDER-RIGHT: #cccccc 1px groove; BACKGROUND-COLOR: #eeeeee">�¡�</button></div></td>
            </tr>
          </table>
        </TD></TR>
      <TR align=middle bgColor=#e0e0e0>
        <TD height="23" align="center" bgcolor="#99CCFF"><font size="2">��</font></TD>
        <TD height="23" align="center" bgcolor="#99CCFF"><font size="2">һ</font></TD>
        <TD height="23" align="center" bgcolor="#99CCFF"><font size="2">��</font></TD>
        <TD height="23" align="center" bgcolor="#99CCFF"><font size="2">��</font></TD>
        <TD height="23" align="center" bgcolor="#99CCFF"><font size="2">��</font></TD>
        <TD height="23" align="center" bgcolor="#99CCFF"><font size="2">��</font></TD>
        <TD height="23" align="center" bgcolor="#99CCFF"><font size="2">��</font></TD>
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
function CheckLodop(){
   var oldVersion=LODOP.Version;
       newVerion="5.0.6.2";	
   if (oldVersion==null){
	document.write("<h3><font color='#FF00FF'>��ӡ�ؼ�δ��װ!�������<a href='"+jcontextPath+"/resource/pageframe/js/install_lodop.exe'>ִ�а�װ</a>,��װ����ˢ��ҳ�档</font></h3>");
	if (navigator.appName=="Netscape")
	document.write("<h3><font color='#FF00FF'>��Firefox������û����ȵ������<a href='"+jcontextPath+"/resource/pageframe/js/npActiveXFirefox4x.xpi'>��װ���л���</a>��</font></h3>");
   } else if (oldVersion<newVerion)
	document.write("<h3><font color='#FF00FF'>��ӡ�ؼ���Ҫ����!�������<a href='"+jcontextPath+"/resource/pageframe/js/install_lodop.exe'>ִ������</a>,�����������½��롣</font></h3>");
}


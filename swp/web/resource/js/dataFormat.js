//日期格式化为yyyy-mm-dd
function getDateFormat(){
	    var d, s = "";
		d = new Date();
		s += (d.getFullYear()) + "-";//获取完整的年份(4位,1970-????)
		s += (d.getMonth() + 1) + "-";
		s += d.getDate();
		return s;
	}
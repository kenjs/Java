/**
 * 数据验证文件
 * @author 许晓明
 * @since 2006.04.27
 * @version 1.0
 */

 var NodeType = {
    STRING:         0,                       //字符串
    INTEGER:        1,                       //整形
    DECIMAL:        2,                       //浮点型,浮点型的比较值，以格式：【整数长度.小数长度】给出
    E_MAIL:         3,                       //电子邮件
    PHONE:          4,                       //电话
    POSTALCODE:     5                        //邮政编码
 };

/**
 * 数据验证类
 * @param theForm              表单控件
 * @param controlNameArray     要验证的控件名称数组
 * @param labelNameArray       要验证的控件标签数组
 * @param compareValueArray    比较值数组
 * @param nodeTypeArray        值类型数组
 * @param notNullArray		   不为空数组，布尔数组，TRUE表示不为空；FALSE表示可为空
*/
function DataValidity(theForm, controlNameArray, labelNameArray, compareValueArray, nodeTypeArray, notNullArray) {
    this.theForm = theForm;
    this.controlNameArray = controlNameArray;
    this.labelNameArray = labelNameArray;
    this.compareValueArray = compareValueArray;
    this.nodeTypeArray = nodeTypeArray;
    this.notNullArray = notNullArray;
}

new DataValidity();

//参数合法性验证方法
 DataValidity.prototype.isDefined = function() {
    if (this.theForm == undefined) {
        showError("表单控件未定义，请检查!");
        return false;
    }
    if (this.controlNameArray == undefined) {
        showError("控件名称数组未定义，请检查！");
        return false;
    }
    if (this.labelNameArray == undefined) {
        showError("控件标签数组未定义，请检查！");
        return false;
    }
    if (this.compareValueArray == undefined) {
        showError("比较值数组未定义，请检查！");
        return false;
    }
    if (this.nodeTypeArray == undefined) {
        showError("值类型数组未定义，请检查！");
        return false;
    }
    if (this.notNullArray == undefined) {
		this.notNullArray = new Array(this.controlNameArray.length);
		for (var i = 0; i < this.controlNameArray.length; i++)
			this.notNullArray[i] = true;	        	
    }

    if ((this.controlNameArray.length != this.labelNameArray.length)
        || (this.controlNameArray.length != this.compareValueArray.length)
        || (this.controlNameArray.length != this.nodeTypeArray.length)
        || (this.controlNameArray.length != this.notNullArray.length)) {
        showError("参数长度不相等，请检查！");
        return false;
    }

    return true;
 };
 
 //验证是否可为空
 DataValidity.prototype.checkNullValue = function() {
 	for (var i = 0; i < this.controlNameArray.length; i++) {
 		if (this.notNullArray[i]) {
 			if ($e(this.controlNameArray[i]) == undefined) return false;
		    var value = "";
		    //单条记录
		    if ($e(this.controlNameArray[i]).length == undefined) {
		        value = $e(this.controlNameArray[i]).value;
		        if (trim(value) == "") {
		        	objForFocus = $e(this.controlNameArray[i]);
		            showError("【" + this.labelNameArray[i] + "】必须录入！", "focusSel");
	        		//$e(this.controlNameArray[i]).focus();
	        		//$e(this.controlNameArray[i]).select();
	        		return false;
		        }
		    }
		    //多条记录
		    else {
		        for (var j = 0; j < $e(this.controlNameArray[i]).length; j++) {
		            value = $e(this.controlNameArray[i])[j].value;
		            if (trim(value) == "") {
		            	objForFocus = $e(this.controlNameArray[i])[j];
			            showError("【" + this.labelNameArray[i] + "】必须录入！", "focusSel");
		        		//$e(this.controlNameArray[i])[j].focus();
		        		//$e(this.controlNameArray[i])[j].select();
		        		return false;
			        }
		        }
		    }
 		}
 	}

    return true;
 };

//数据验证方法
 DataValidity.prototype.isValidate = function() {

    if (!this.isDefined()) {
        return false;
    }

    for (var i = 0; i < this.controlNameArray.length; i++) {
        switch (this.nodeTypeArray[i]) {
            case NodeType.STRING:
                var stringValidity = new StringValidity($e(this.controlNameArray[i]), this.compareValueArray[i]);
                if (!stringValidity.isValidate()) {
                    showError("您输入的数值已越界，<" + this.labelNameArray[i] + ">输入字符串的最大长度为" + this.compareValueArray[i] + ",请检查！", "focusSel");
                    return false;
                }
                break;
            case NodeType.INTEGER:
            	var compareValue = this.compareValueArray[i];
            	var integerValidity = new IntegerValidity($e(this.controlNameArray[i]), compareValue);
            	if (!integerValidity.isValidate()) {
                    showError("您输入的数值已越界，<" + this.labelNameArray[i] + ">输入的数值，必须是数字类型，且整数部分最大长度为" + compareValue + "，请检查！", "focusSel");
                    return false;
                }
                break;
            case NodeType.DECIMAL:
                //浮点型的比较值，以格式：【整数长度.小数长度】给出，下面部分主要是分析出整数长度和小数长度
                var compareValue = this.compareValueArray[i];
                compareValue = new String(compareValue);
                var intLength = compareValue.substring(0, compareValue.indexOf("."));
                var precision = compareValue.substring(compareValue.indexOf(".")+1, compareValue.length);
				//showError(intLength + " " + precision);
                var decimalValidity = new DecimalValidity($e(this.controlNameArray[i]), intLength, precision);

                if (!decimalValidity.isValidate()) {
                    showError("您输入的数值已越界，<" + this.labelNameArray[i] + ">输入的数值，必须是数字类型，且整数部分最大长度为" + intLength + ",小数部分最大长度为" + precision +
                        "，请检查！", "focusSel");
                    return false;
                }
                break;
            case NodeType.E_MAIL:
                break;
            case NodeType.PHONE:
                break;
            case NodeType.POSTALCODE:
                break;
            default:
                break;
        }
    }
    
    if (!this.checkNullValue()) {
    	return false;
    }

    return true;
 };

 /**
  * 字符串长度合法性验证类
  * @param obj              控件名称
  * @param length           验证字符串长度
  */
 function StringValidity(obj, length) {
    this.controlObj = obj;
    this.strLength = length;
 }



 StringValidity.prototype.checkLength = function(strValue, strLength) {
    var len = strValue.length;
    for (var i  = 0; i < len; i++) {
        if (strValue.charCodeAt(i) > 255) {
            len++;
        }
    }
    if (len > strLength) return false;

    return true;
 };

 StringValidity.prototype.isValidate = function() {
    if (this.controlObj == undefined) return false;
    var value = "";
    //单条记录
    if (this.controlObj.length == undefined) {
    	objForFocus = this.controlObj;
        value = this.controlObj.value;
        if (!this.checkLength(value, this.strLength)) {
            //this.controlObj.focus();
            //this.controlObj.select();
            return false;
        }
    }
    //多条记录
    else {
        for (var i = 0; i < this.controlObj.length; i++) {
        	objForFocus = this.controlObj[i];
            value = this.controlObj[i].value;
            if (!this.checkLength(value, this.strLength)) {
                //this.controlObj[i].focus();
                //this.controlObj[i].select();
                return false;
            }
        }
    }

    return true;
 };

 //浮点型数据验证
 function DecimalValidity(obj, intLength, precision) {
    this.controlObj  = obj;
    this.intLength = intLength;
    this.precision = precision;
 }


 //判断是否是浮点型数据
 DecimalValidity.prototype.isDecimal = function(value) {
    var regStr = /\-?\d*\.?\d*/g;          //浮点型的正则表达式
    var result = value.match(regStr);
    if (result != null) {
        if (result[0].length == value.length) {
            return true;
        }
    }
    return false;
 };

 //格式化浮点型数据
 DecimalValidity.prototype.format = function(value) {
    //如果没有找到.，则在值后面添加.
    if (value.indexOf(".") == -1) {
        value += ".";
    }
    //如果以.结尾，则在后面添加precision个0
    if (value.indexOf(".") == value.length-1) {
        for (var i = 0; i < this.precision; i++) {
            value += "0";
        }
    }
    //如果以.开头，则在值前面添加一个0
    if (value.indexOf(".") == 0) {
        value = "0" + value;
    }

    //分离出浮点数的整数部分和小数部分，如果小数部分长度大于precision，截取数值；如果小于precison,后添0
    var intStr = value.substring(0,value.indexOf("."));
    var precisionStr = value.substring(value.indexOf(".")+1);


    var carryValue = 0;      //进位值

    //四舍五入操作
    if (precisionStr.length > this.precision) {
        var precisionValue = 0;
        for (var i = 0; i < this.precision; i++) {
            if (precisionStr.charAt(i) == "0") {
                continue;
            } else {
                precisionValue = parseInt(precisionStr.substring(i, this.precision));
                break;
            }
        }
        //如果小数点截止后的一位，数值大于等于5那么进位
        if (parseInt(new String(precisionStr).charAt(this.precision)) >= 5) {
    		precisionValue++;
		}
        //if (parseInt(precisionStr.substring(this.precision, 1)) >= 5) {
        //    precisionValue++;
        //}
        //如果进位后，小数的位数大于精度，那么在向整数进位
        if (new String(precisionValue).length > this.precision) {
            precisionStr = new String(precisionValue).substring(precisionValue.length - this.precision);    //取小数部分的值
            carryValue = parseInt(new String(precisionValue).substring(0, precisionValue.length - this.precision));                               //设置进位值
        } else {
        	precisionStr = precisionValue;
        	//showError(precisionStr + " " + new String(precisionStr).length + " " + this.precision);
        	for (var i = 0; i < this.precision-new String(precisionStr).length; i++) {
	            precisionStr = "0" + precisionStr;
	        }
        	carryValue = 0;
        }
    } else {
        for (var i = 0; i < this.precision-precisionStr.length; i++) {
            precisionStr += "0";
        }
    }

    var intValue = parseInt(intStr);
    intValue += carryValue;             //加上进位值
    intStr = new String(intValue);

	//showError(intStr + "." + precisionStr);
    return intStr + "." + precisionStr;
 };

 //浮点型数据验证
 DecimalValidity.prototype.isValidate = function() {

    if (this.controlObj == undefined) return false;

    var value = "";
    //单条
    if (this.controlObj.length == undefined) {
    	objForFocus = this.controlObj;
        value = this.controlObj.value;
        var temp = this.format(value);
        if (!this.isDecimal(value) || !this.isDecimal(temp)) {
            //this.controlObj.focus();
            //this.controlObj.select();
            return false;
        }
        value = this.format(value);
        var intStr = value.substring(0, value.indexOf("."));
        if (intStr.length > this.intLength) {
            //this.controlObj.focus();
            //this.controlObj.select();
            return false;
        } else {
        	this.controlObj.value = value;	
        }
    }
    //多条
    else {
        for (var i = 0; i < this.controlObj.length; i++) {
        	objForFocus = this.controlObj[i];
            value = this.controlObj[i].value;
            var temp = this.format(value);
            if (!this.isDecimal(value)|| !this.isDecimal(temp)) {
                //this.controlObj[i].focus();
                //this.controlObj[i].select();
                return false;
            }
            value = this.format(value);
            var intStr = value.substring(0, value.indexOf("."));
            if (intStr.length > this.intLength) {
                //this.controlObj[i].focus();
                //this.controlObj[i].select();
                return false;
            } else {
            	this.controlObj[i].value = value;
            }
        }
    }

    return true;
 };
 
 
 //整型数据验证
 function IntegerValidity(obj, intLength) {
    this.controlObj  = obj;
    this.intLength = intLength;
 }
 
  //判断是否是整型数据
 IntegerValidity.prototype.isInteger = function(value) {
    var regStr = /\-?\d*/g;          //整形正则表达式
    var result = value.match(regStr);
    if (result != null) {
        if (result[0].length == value.length) {
            return true;
        }
    }
    return false;
 };
 
  //整形长度验证
  IntegerValidity.prototype.checkLength = function(intValue, intLength) {
    var len = intValue.length;
    for (var i  = 0; i < len; i++) {
        if (intValue.charCodeAt(i) > 255) {
            len++;
        }
    }
    if (len > intLength) return false;

    return true;
 };
 
  //整型数据验证
 IntegerValidity.prototype.isValidate = function() {
	
    if (this.controlObj == undefined) return false;

    var value = ""; 
    //单条
    if (this.controlObj.length == undefined) {
    	objForFocus = this.controlObj;
        value = this.controlObj.value;
        if (!this.isInteger(value) || !this.checkLength(value, this.intLength)) {
            //this.controlObj.focus();
            //this.controlObj.select();
            return false;
        }
    }
    //多条
    else {
        for (var i = 0; i < this.controlObj.length; i++) {
        	objForFocus = this.controlObj[i];
            value = this.controlObj[i].value;
            if (!this.isInteger(value) || !this.checkLength(value, this.intLength)) {
                //this.controlObj[i].focus();
                //this.controlObj[i].select();
                return false;
            }
        }
    }

    return true;
 };
 
 
 
 function $e(controlName){
 	return document.getElementsByName(controlName);			//theForm.elements[""].length for the select form will get the size of options
 }
 
 function focusSel() {
	 objForFocus.focus();
	 objForFocus.select();
 }
 
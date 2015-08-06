/**
 * ������֤�ļ�
 * @author ������
 * @since 2006.04.27
 * @version 1.0
 */

 var NodeType = {
    STRING:         0,                       //�ַ���
    INTEGER:        1,                       //����
    DECIMAL:        2,                       //������,�����͵ıȽ�ֵ���Ը�ʽ������������.С�����ȡ�����
    E_MAIL:         3,                       //�����ʼ�
    PHONE:          4,                       //�绰
    POSTALCODE:     5                        //��������
 };

/**
 * ������֤��
 * @param theForm              ���ؼ�
 * @param controlNameArray     Ҫ��֤�Ŀؼ���������
 * @param labelNameArray       Ҫ��֤�Ŀؼ���ǩ����
 * @param compareValueArray    �Ƚ�ֵ����
 * @param nodeTypeArray        ֵ��������
 * @param notNullArray		   ��Ϊ�����飬�������飬TRUE��ʾ��Ϊ�գ�FALSE��ʾ��Ϊ��
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

//�����Ϸ�����֤����
 DataValidity.prototype.isDefined = function() {
    if (this.theForm == undefined) {
        showError("���ؼ�δ���壬����!");
        return false;
    }
    if (this.controlNameArray == undefined) {
        showError("�ؼ���������δ���壬���飡");
        return false;
    }
    if (this.labelNameArray == undefined) {
        showError("�ؼ���ǩ����δ���壬���飡");
        return false;
    }
    if (this.compareValueArray == undefined) {
        showError("�Ƚ�ֵ����δ���壬���飡");
        return false;
    }
    if (this.nodeTypeArray == undefined) {
        showError("ֵ��������δ���壬���飡");
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
        showError("�������Ȳ���ȣ����飡");
        return false;
    }

    return true;
 };
 
 //��֤�Ƿ��Ϊ��
 DataValidity.prototype.checkNullValue = function() {
 	for (var i = 0; i < this.controlNameArray.length; i++) {
 		if (this.notNullArray[i]) {
 			if ($e(this.controlNameArray[i]) == undefined) return false;
		    var value = "";
		    //������¼
		    if ($e(this.controlNameArray[i]).length == undefined) {
		        value = $e(this.controlNameArray[i]).value;
		        if (trim(value) == "") {
		        	objForFocus = $e(this.controlNameArray[i]);
		            showError("��" + this.labelNameArray[i] + "������¼�룡", "focusSel");
	        		//$e(this.controlNameArray[i]).focus();
	        		//$e(this.controlNameArray[i]).select();
	        		return false;
		        }
		    }
		    //������¼
		    else {
		        for (var j = 0; j < $e(this.controlNameArray[i]).length; j++) {
		            value = $e(this.controlNameArray[i])[j].value;
		            if (trim(value) == "") {
		            	objForFocus = $e(this.controlNameArray[i])[j];
			            showError("��" + this.labelNameArray[i] + "������¼�룡", "focusSel");
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

//������֤����
 DataValidity.prototype.isValidate = function() {

    if (!this.isDefined()) {
        return false;
    }

    for (var i = 0; i < this.controlNameArray.length; i++) {
        switch (this.nodeTypeArray[i]) {
            case NodeType.STRING:
                var stringValidity = new StringValidity($e(this.controlNameArray[i]), this.compareValueArray[i]);
                if (!stringValidity.isValidate()) {
                    showError("���������ֵ��Խ�磬<" + this.labelNameArray[i] + ">�����ַ�������󳤶�Ϊ" + this.compareValueArray[i] + ",���飡", "focusSel");
                    return false;
                }
                break;
            case NodeType.INTEGER:
            	var compareValue = this.compareValueArray[i];
            	var integerValidity = new IntegerValidity($e(this.controlNameArray[i]), compareValue);
            	if (!integerValidity.isValidate()) {
                    showError("���������ֵ��Խ�磬<" + this.labelNameArray[i] + ">�������ֵ���������������ͣ�������������󳤶�Ϊ" + compareValue + "�����飡", "focusSel");
                    return false;
                }
                break;
            case NodeType.DECIMAL:
                //�����͵ıȽ�ֵ���Ը�ʽ������������.С�����ȡ����������沿����Ҫ�Ƿ������������Ⱥ�С������
                var compareValue = this.compareValueArray[i];
                compareValue = new String(compareValue);
                var intLength = compareValue.substring(0, compareValue.indexOf("."));
                var precision = compareValue.substring(compareValue.indexOf(".")+1, compareValue.length);
				//showError(intLength + " " + precision);
                var decimalValidity = new DecimalValidity($e(this.controlNameArray[i]), intLength, precision);

                if (!decimalValidity.isValidate()) {
                    showError("���������ֵ��Խ�磬<" + this.labelNameArray[i] + ">�������ֵ���������������ͣ�������������󳤶�Ϊ" + intLength + ",С��������󳤶�Ϊ" + precision +
                        "�����飡", "focusSel");
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
  * �ַ������ȺϷ�����֤��
  * @param obj              �ؼ�����
  * @param length           ��֤�ַ�������
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
    //������¼
    if (this.controlObj.length == undefined) {
    	objForFocus = this.controlObj;
        value = this.controlObj.value;
        if (!this.checkLength(value, this.strLength)) {
            //this.controlObj.focus();
            //this.controlObj.select();
            return false;
        }
    }
    //������¼
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

 //������������֤
 function DecimalValidity(obj, intLength, precision) {
    this.controlObj  = obj;
    this.intLength = intLength;
    this.precision = precision;
 }


 //�ж��Ƿ��Ǹ���������
 DecimalValidity.prototype.isDecimal = function(value) {
    var regStr = /\-?\d*\.?\d*/g;          //�����͵�������ʽ
    var result = value.match(regStr);
    if (result != null) {
        if (result[0].length == value.length) {
            return true;
        }
    }
    return false;
 };

 //��ʽ������������
 DecimalValidity.prototype.format = function(value) {
    //���û���ҵ�.������ֵ�������.
    if (value.indexOf(".") == -1) {
        value += ".";
    }
    //�����.��β�����ں������precision��0
    if (value.indexOf(".") == value.length-1) {
        for (var i = 0; i < this.precision; i++) {
            value += "0";
        }
    }
    //�����.��ͷ������ֵǰ�����һ��0
    if (value.indexOf(".") == 0) {
        value = "0" + value;
    }

    //��������������������ֺ�С�����֣����С�����ֳ��ȴ���precision����ȡ��ֵ�����С��precison,����0
    var intStr = value.substring(0,value.indexOf("."));
    var precisionStr = value.substring(value.indexOf(".")+1);


    var carryValue = 0;      //��λֵ

    //�����������
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
        //���С�����ֹ���һλ����ֵ���ڵ���5��ô��λ
        if (parseInt(new String(precisionStr).charAt(this.precision)) >= 5) {
    		precisionValue++;
		}
        //if (parseInt(precisionStr.substring(this.precision, 1)) >= 5) {
        //    precisionValue++;
        //}
        //�����λ��С����λ�����ھ��ȣ���ô����������λ
        if (new String(precisionValue).length > this.precision) {
            precisionStr = new String(precisionValue).substring(precisionValue.length - this.precision);    //ȡС�����ֵ�ֵ
            carryValue = parseInt(new String(precisionValue).substring(0, precisionValue.length - this.precision));                               //���ý�λֵ
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
    intValue += carryValue;             //���Ͻ�λֵ
    intStr = new String(intValue);

	//showError(intStr + "." + precisionStr);
    return intStr + "." + precisionStr;
 };

 //������������֤
 DecimalValidity.prototype.isValidate = function() {

    if (this.controlObj == undefined) return false;

    var value = "";
    //����
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
    //����
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
 
 
 //����������֤
 function IntegerValidity(obj, intLength) {
    this.controlObj  = obj;
    this.intLength = intLength;
 }
 
  //�ж��Ƿ�����������
 IntegerValidity.prototype.isInteger = function(value) {
    var regStr = /\-?\d*/g;          //����������ʽ
    var result = value.match(regStr);
    if (result != null) {
        if (result[0].length == value.length) {
            return true;
        }
    }
    return false;
 };
 
  //���γ�����֤
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
 
  //����������֤
 IntegerValidity.prototype.isValidate = function() {
	
    if (this.controlObj == undefined) return false;

    var value = ""; 
    //����
    if (this.controlObj.length == undefined) {
    	objForFocus = this.controlObj;
        value = this.controlObj.value;
        if (!this.isInteger(value) || !this.checkLength(value, this.intLength)) {
            //this.controlObj.focus();
            //this.controlObj.select();
            return false;
        }
    }
    //����
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
 
 /**
   *���� ��hel
   *�汾 ��V1.0 
   */
  function HashMap(){
     /** Ԫ�ظ��� **/
     var size = 0;
     /** ������󳤶�Ĭ�� 256 **/
     var length = arguments[0] ? arguments[0] : 256;
     /**  ���Entry���������� **/
     var list = new Array(length);
      
     /** ��ż�ֵ **/
     this.put = function(key,value)
     {
         /** װ�����Ӵ���0.75����ɢ�� **/
         if(size/length > 0.75)
         {
             resize(this);
         }
         
         var counter = 0;
         var code = getHashCode(key);
         while(counter++ < length)
         {
             if(typeof list[code] == "undefined")
             {
                 size  = size + 1;
                 list[code] = new Entry(key,value);
                 break;
             }
             else if(list[code].key == key )
             {
                 list[code].value = value;
                 break;
             }
             
             if(++code > length)
             {
                 code = 0;
             }
         }
     }
     
     /** ��ȡKeyֵ **/
     this.get = function(key)
     {
         var counter = 0;
         var code = getHashCode(key);
         while(counter++ < length)
         {
              if(typeof list[code] != "undefined")
             {
                 if( list[code].key == key )
                 {
                     return list[code].value;
                 }
             }
             else
             {
                 return null;
             }
             
             if(++code > length)
             {
                 code = 0;
             }
         }
     }
     
     /** �������е� Value **/
     this.values = function ()
     {
         var values = new Array();
         for(var i = 0 ; i < length ; i++)
         {
             if(typeof list[i] != "undefined" )
             {
                 values.push(list[i].value);
             }
         }
         return values;    
     }
     
     /** �������е� Key **/
     this.keys = function ()
     {
         var keys = new Array();
         for(var i = 0 ; i < length ; i++)
         {
             if(typeof list[i] != "undefined" )
             {
                 keys.push(list[i].key);
             }
         }
         return keys;    
     }
     
     /** ����Ԫ�ظ��� **/
     this.size = function ()
     {
         return size;
     }
     
     /** �������Ƿ���� KEY **/
     this.containsKey = function (key)
     {
         var counter = 0;
         var code = getHashCode(key);
         while(counter++ < length)
         {
             if(typeof list[code] != "undefined")
             {
                 if( list[code].key == key )
                 {
                     return true;
                 }
             }
                     
             if(++code > length)
             {
                 code = 0;
             }
         }
         return false;
     }
     
     /** �������Ƿ���� Value **/
     this.containsValue = function (value)
     {
         for(var i = 0 ; i < length ; i++)
         {
             if(typeof list[i] != "undefined" )
             {
                 if(list[i].value == value)
                 {
                     return true;
                 }
             }
         }
         return false;
     }
     
     /** �ڲ��� ��ֵ��Ӧ��ϵ **/
     var Entry = function (key,value)
     {
         this.key = key;
         this.value = value;
     }
     
     /** Hash ���� [��������Բ���. ��������Լ�д] **/
     var getHashCode = function(key)
     {
         var hashCode = (key.charCodeAt(0) * key.charCodeAt(key.length - 1)) % length;
         return hashCode;
     }
     
     /** size / length > 0.75 װ�����Ӵ���0.75����ɢ�� [�˷�ʱ��Ķ���..]**/
     var resize = function ( _this )
     {
         var entryList = new Array();
         /** �ݴ� list **/
         for(var i = 0 ; i < length ; i++)
         {
             if(typeof list[i] != "undefined" )
             {
                 entryList.push(list[i]);
             }
         }
         /** ��������Ϊԭ��2�� **/
         length = length * 2;
         list = new Array(length);
         /** Ԫ�ظ����� 0 **/
         size = 0;
         /** ��ɢ��**/
         for(var i = 0 ; i < entryList.length ; i++)
         {
             var entry = entryList[i];
             _this.put(entry.key , entry.value);    
         }
     }    
 }

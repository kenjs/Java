 /**
   *作者 ：hel
   *版本 ：V1.0 
   */
  function HashMap(){
     /** 元素个数 **/
     var size = 0;
     /** 容器最大长度默认 256 **/
     var length = arguments[0] ? arguments[0] : 256;
     /**  存放Entry的线性数组 **/
     var list = new Array(length);
      
     /** 存放键值 **/
     this.put = function(key,value)
     {
         /** 装填因子大于0.75重新散列 **/
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
     
     /** 获取Key值 **/
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
     
     /** 返回所有的 Value **/
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
     
     /** 返回所有的 Key **/
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
     
     /** 返回元素个数 **/
     this.size = function ()
     {
         return size;
     }
     
     /** 集合中是否存在 KEY **/
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
     
     /** 集合中是否存在 Value **/
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
     
     /** 内部类 键值对应关系 **/
     var Entry = function (key,value)
     {
         this.key = key;
         this.value = value;
     }
     
     /** Hash 函数 [估计随机性不好. 建议高手自己写] **/
     var getHashCode = function(key)
     {
         var hashCode = (key.charCodeAt(0) * key.charCodeAt(key.length - 1)) % length;
         return hashCode;
     }
     
     /** size / length > 0.75 装填因子大于0.75重新散列 [浪费时间的东东..]**/
     var resize = function ( _this )
     {
         var entryList = new Array();
         /** 暂存 list **/
         for(var i = 0 ; i < length ; i++)
         {
             if(typeof list[i] != "undefined" )
             {
                 entryList.push(list[i]);
             }
         }
         /** 长度扩大为原来2倍 **/
         length = length * 2;
         list = new Array(length);
         /** 元素个数置 0 **/
         size = 0;
         /** 重散列**/
         for(var i = 0 ; i < entryList.length ; i++)
         {
             var entry = entryList[i];
             _this.put(entry.key , entry.value);    
         }
     }    
 }

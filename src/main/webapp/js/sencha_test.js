/**  
    * 前言：  
    * 此例子来自sencha touch的官方example  
    * 注释用语如有不当请见谅。  
    */  
   //相信这是每个页面都是一样的  
   Ext.setup({  
       tabletStartupScreen: 'tablet_startup.png',  
       phoneStartupScreen: 'phone_startup.png',  
       icon: 'icon.png',  
       glossOnIcon: false,  
       onReady : function() {  
          //注册一个名为“Contact”的模型，显示的字段为firstName和lastName  
          Ext.regModel('Contact', {  
              fields: ['firstName', 'lastName']  
          });  
            
          //定义一个对象，有点类似Android里面的BaseAdapter  
          var groupingBase = {  
              itemTpl: '<div class="contact2"><strong>{firstName}</strong> {lastName}</div>',  
              selModel: {//选择模型，单选；应该还有多选  
                  mode: 'SINGLE',  
                  allowDeselect: true  
              },  
              grouped: true,//分组  
              indexBar: true, //索引栏  
               
              //定义点击事件  
              onItemDisclosure: {  
                  scope: 'test',  
                  //事件处理  
                  handler: function(record, btn, index) {  
                      alert('Disclose more info for ' + record.get('firstName'));  
                  }  
              },  
              //数据仓库  
              store: new Ext.data.Store({  
                 model: 'Contact',//与上面注册的模型对应  
                 sorters: 'firstName',//排序字段  
                   
                 //分组title显示的数据源，为firstName的首字母  
                 getGroupString : function(record) {  
                     return record.get('firstName')[0];  
                 },  
                 //就是数据了  
                 data: [  
                            {firstName: 'Aaron', lastName: 'Conran'},  
                            {firstName: 'Ape', lastName: 'Evilias'},  
                            {firstName: 'Dave', lastName: 'Kaneda'},  
                            {firstName: 'Michael', lastName: 'Mullany'},  
                            {firstName: 'Abraham', lastName: 'Elias'},  
                            {firstName: 'Jay', lastName: 'Robinson'},  
                            {firstName: 'Tommy', lastName: 'Maintz'},  
                            {firstName: 'Ed', lastName: 'Spencer'},  
                            {firstName: 'Jamie', lastName: 'Avins'},  
                            {firstName: 'Ed', lastName: 'Spencer'},  
                            {firstName: 'Jamie', lastName: 'Avins'},  
                            {firstName: 'Aaron', lastName: 'Conran'},  
                            {firstName: 'Dave', lastName: 'Kaneda'},  
                            {firstName: 'Ape', lastName: 'Evilias'},  
                            {firstName: 'Dave', lastName: 'Kaneda'},  
                            {firstName: 'Michael', lastName: 'Mullany'},  
                            {firstName: 'Abraham', lastName: 'Elias'},  
                            {firstName: 'Jay', lastName: 'Robinson'},  
                            {firstName: 'Tommy', lastName: 'Maintz'},  
                            {firstName: 'Ed', lastName: 'Spencer'},  
                            {firstName: 'Jamie', lastName: 'Avins'},  
                            {firstName: 'Aaron', lastName: 'Conran'},  
                            {firstName: 'Dave', lastName: 'Kaneda'},  
                            {firstName: 'Michael', lastName: 'Mullany'},  
                            {firstName: 'Abraham', lastName: 'Elias'},  
                            {firstName: 'Jay', lastName: 'Robinson'},  
                            {firstName: 'Tommy', lastName: 'Maintz'},  
                            {firstName: 'Ed', lastName: 'Spencer'},  
                            {firstName: 'Jamie', lastName: 'Avins'},  
                            {firstName: 'Aaron', lastName: 'Conran'},  
                            {firstName: 'Dave', lastName: 'Kaneda'},  
                            {firstName: 'Michael', lastName: 'Mullany'},  
                            {firstName: 'Abraham', lastName: 'Elias'},  
                            {firstName: 'Jay', lastName: 'Robinson'},  
                            {firstName: 'Michael', lastName: 'Mullany'},  
                            {firstName: 'Abraham', lastName: 'Elias'},  
                            {firstName: 'Jay', lastName: 'Robinson'},  
                            {firstName: 'Zed', lastName: 'Zacharias'}  
                        ]  
                    })  
          };  
     
          /**  
           * 应该是判断设备类型把  
           * Phone和其他类型有所不同，主要就是屏幕大小了  
           */  
          if (!Ext.is.Phone) {  
              new Ext.List(Ext.apply(groupingBase, {  
                  floating: true,  
                  width: 350,  
                  height: 370,  
                  centered: true,  
                  modal: true,  
                 hideOnMaskTap: false  
             })).show();  
         }  
         else {  
             new Ext.List(Ext.apply(groupingBase, {  
                 fullscreen: true  //全屏  
             }));  
         }  
     }  
 }); 
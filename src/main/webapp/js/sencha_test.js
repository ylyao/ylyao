/**  
    * ǰ�ԣ�  
    * ����������sencha touch�Ĺٷ�example  
    * ע���������в�������¡�  
    */  
   //��������ÿ��ҳ�涼��һ����  
   Ext.setup({  
       tabletStartupScreen: 'tablet_startup.png',  
       phoneStartupScreen: 'phone_startup.png',  
       icon: 'icon.png',  
       glossOnIcon: false,  
       onReady : function() {  
          //ע��һ����Ϊ��Contact����ģ�ͣ���ʾ���ֶ�ΪfirstName��lastName  
          Ext.regModel('Contact', {  
              fields: ['firstName', 'lastName']  
          });  
            
          //����һ�������е�����Android�����BaseAdapter  
          var groupingBase = {  
              itemTpl: '<div class="contact2"><strong>{firstName}</strong> {lastName}</div>',  
              selModel: {//ѡ��ģ�ͣ���ѡ��Ӧ�û��ж�ѡ  
                  mode: 'SINGLE',  
                  allowDeselect: true  
              },  
              grouped: true,//����  
              indexBar: true, //������  
               
              //�������¼�  
              onItemDisclosure: {  
                  scope: 'test',  
                  //�¼�����  
                  handler: function(record, btn, index) {  
                      alert('Disclose more info for ' + record.get('firstName'));  
                  }  
              },  
              //���ݲֿ�  
              store: new Ext.data.Store({  
                 model: 'Contact',//������ע���ģ�Ͷ�Ӧ  
                 sorters: 'firstName',//�����ֶ�  
                   
                 //����title��ʾ������Դ��ΪfirstName������ĸ  
                 getGroupString : function(record) {  
                     return record.get('firstName')[0];  
                 },  
                 //����������  
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
           * Ӧ�����ж��豸���Ͱ�  
           * Phone����������������ͬ����Ҫ������Ļ��С��  
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
                 fullscreen: true  //ȫ��  
             }));  
         }  
     }  
 }); 
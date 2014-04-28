Ext.ns('oreilly', 'aa');

Ext.setup({
    statusBarStyle: 'black',
    onReady: function() {
        oreilly.App = new oreilly.App({
            title: '11122',
            aboutPages: [{
                title: '111111111111111111',
                card: {
                    xtype: 'htmlpage',
                    url: '11.html'
                }
            }, {
                title: '22222222222222',
                card: {
                    xtype: 'htmlpage',
                    url: 'index.jsp'
                }
            }, {
                title: '3333333333333',
                card: {
                    xtype: 'htmlpage',
                    url: '22.html'
                }
            }]
        });
    }
});

oreilly.App = Ext.extend(Ext.TabPanel, {
    fullscreen: true,
    
    cardSwitchAnimation: false,
    initComponent: function() {
            this.items = [{
                xtype: 'mylist',
                iconCls: 'info',
                pages: this.aboutPages
            }];
        oreilly.App.superclass.initComponent.call(this);
    }
    
});

aa.HtmlPage = Ext.extend(Ext.Panel, { //子页面
    autoLoad: 'about.html',
    scroll: 'vertical',
    styleHtmlContent: true,
    initComponent: function(){
        var toolbarBase = {
            xtype: 'toolbar',
            title: this.title
        };
        if (this.prevCard !== undefined) { //返回
            toolbarBase.items = {
                ui: 'back',
                text: this.prevCard.title,
                scope: this,
                handler: function(){
            		//alert(this.prevCard.title);
                    this.ownerCt.setActiveItem(this.prevCard, { type: 'slide', reverse: true });
                }
            }
        }
        this.dockedItems = toolbarBase;
        Ext.Ajax.request({
            url: this.url,
            success: function(rs){
                this.update(rs.responseText);
            },
            scope: this
        });
        aa.HtmlPage.superclass.initComponent.call(this);
    }
});
Ext.reg('htmlpage', aa.HtmlPage);


aa.AboutList = Ext.extend(Ext.Panel, {
    layout: 'card',
    initComponent: function(){
        this.list = new Ext.List({
            itemTpl: '{title}',
            store: new Ext.data.Store({
                fields: ['name', 'card'],
                data: this.pages
            }),
            listeners: {
                selectionchange: {fn: this.onSelect, scope: this}
            }
        });
        this.listpanel = new Ext.Panel({
            title: '返回',
            items: this.list,
            layout: 'fit',
            dockedItems: { //html头
                xtype: 'toolbar',
                title: '标题标题'
            }
        })
        this.listpanel.on('activate', function(){ //清楚上次选择
            this.list.getSelectionModel().deselectAll();
            }, this);
        this.items = [this.listpanel];
        aa.AboutList.superclass.initComponent.call(this);
    },
    
    onSelect: function(sel, records){
    	//alert(records[0].data.title);
        if (records[0] !== undefined) { //返回时records[0] == undefined
            var newCard = Ext.apply({}, records[0].data.card, { 
                prevCard: this.listpanel,
                title: records[0].data.title
            });
            
            this.setActiveItem(Ext.create(newCard), 'pop');
        }
    }
});

Ext.reg('mylist', aa.AboutList);

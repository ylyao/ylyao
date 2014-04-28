Ext.setup({
	statusBarStyle : 'black',
	onReady : function() {
		Ext.create('Ext.view.Form', {
			extend : 'Ext.form.Panel',
			fullscreen : true,
			config : {
				items : [ {
					xtype : 'textfield',
					name : 'name',
					label : 'name'
				}, {
					xtype : 'emailfield',
					name : 'email',
					label : 'email'
				}, {
					xtype : 'passwordfield',
					name : 'password',
					label : 'password'
				} ]
			}
		});
	}
});

function login() {

}
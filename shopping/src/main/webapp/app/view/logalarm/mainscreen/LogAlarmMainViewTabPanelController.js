/**
 * 
 */
Ext.define('Shopping.view.logalarm.mainscreen.LogAlarmMainViewTabPanelController', {
	extend : 'Ext.app.ViewController',
	alias : 'controller.logAlarmMainViewTabPanelController',
	
	init : function()
	{
		
		var component = Ext.create("Shopping.view.logalarm.mainscreen.AddLogAlarm", {
			title : "Log Module",
		});
		this.onAddTabClick(component);
	},
	onAddTabClick: function(component) 
	{
		
		var tabPanel = this.getView(),
        tab = tabPanel.add({
           xtype : component
         });
		tabPanel.setActiveTab(tab);
    },
    
    setFormData : function(logModuleData)
	{
		
		var tabPanel = this.getView();
		
		var logModule = logModuleData[0];
		tabPanel.getActiveTab().setTitle(logModule.name);
		
		tabPanel.getActiveTab().getController().setFormData(logModuleData[0]);
	},
});
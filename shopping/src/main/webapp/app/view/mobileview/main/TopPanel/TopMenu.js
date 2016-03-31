Ext.define('Shopping.view.mobileview.main.TopPanel.TopMenu', { 
	extend: 'Ext.toolbar.Toolbar',
    xtype: 'topMenu',
    requires:['Shopping.view.mobileview.main.TopPanel.TopMenuController',/*'Shopping.view.mobileview.main.ResourceWindow'*/],
    controller :'topMenuController',
    border:0,
    itemId:'topMenuPanel',
    autoScroll :true,
    items:[]
});
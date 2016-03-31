Ext.define('Shopping.view.appreportui.datapoint.DataPointView', {
	extend : 'Ext.panel.Panel',
	requires:['Shopping.view.appreportui.datapoint.DataPointViewController',
	          'Shopping.view.fw.DataPointPanel'],
	controller : 'datapointController',
	xtype:'dataPointView',
	itemId:'dataPointViewId',
	items : [ {
		xtype : "tabbar",
		rotation : "top",
		itemId : 'maindatapointpanel',
		tabRotation : 0,
		adjustTabPositions : function() {
		}
	} ],
	listeners : {
		scope:'controller',
		resize:'onResizeDataPointView'
	}
});

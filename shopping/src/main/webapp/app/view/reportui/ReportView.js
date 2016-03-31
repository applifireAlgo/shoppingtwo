Ext.define('Shopping.view.reportui.ReportView', {
	extend : 'Ext.panel.Panel',
	requires : [ 'Shopping.view.reportui.querycriteria.QueryCriteriaView',
			'Shopping.view.reportui.datachart.DataChartViewTab',
			'Shopping.view.reportui.datachart.DataChartViewPanel',
			'Shopping.view.reportui.ReportViewController' ,
			'Shopping.view.fw.MainDataPointPanel',
			'Shopping.view.googlemaps.map.MapPanel'
			],
	xtype : 'reportview',
	controller : 'reportviewController',
	layout : 'border',
	isCustomReport:false,
	reportWidgets :["1","2","3","4"],
	//autoScroll : true,
	//margin : '3 0 5 0',
	height:500,
	width:"100%",
	listeners : {
		scope : 'controller',
		afterrender : 'renderReport'
	}
});

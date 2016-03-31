Ext.define('Shopping.view.appreportui.ReportView', {
	extend : 'Ext.form.Panel',
	requires : ['Shopping.view.appreportui.ReportViewController',
	            'Shopping.view.appreportui.datagrid.DataGridPanel',
	            'Shopping.view.appreportui.datagrid.DataGridView',
	            'Shopping.view.appreportui.querycriteria.QueryCriteriaView',
	            'Shopping.view.appreportui.chart.ChartView',
	            'Shopping.view.appreportui.datapoint.DataPointView',
	            'Shopping.view.googlemaps.map.MapPanel',
	            'Shopping.view.appreportui.chartpoint.ChartPointView'
	            ],
	xtype : 'reportView',
	controller : 'reportViewController',
	layout : 'border',
	reportJSON:null,
	listeners : {
		scope : 'controller',
		afterrender : 'afterRenderReport',
		boxready : 'fetchReportData'
	}
});

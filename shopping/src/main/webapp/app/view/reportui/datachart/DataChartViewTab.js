Ext.define('Shopping.view.reportui.datachart.DataChartViewTab', {
	extend : 'Ext.tab.Panel',
	requires : [ 'Shopping.view.reportui.datachart.DataChartTController',
	             'Shopping.view.reportui.datachart.datagrid.DataGridView',
	             'Shopping.view.reportui.datachart.chart.ChartTabView',
	             'Shopping.view.reportui.datachart.ChartPointView' ],
	controller : 'datacharttController',
	xtype : 'datachart-tabpanel',
	tabPosition : 'bottom',
	bodyStyle : 'background:#D8D8D8',
	listeners : {
		scope : "controller",
		tabchange : 'tabchange',
		afterrender:'afterTabPanelRender'
	}
});
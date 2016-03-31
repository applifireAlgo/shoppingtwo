Ext.define('Shopping.view.databrowsercalendar.DBCalendar', {
	extend : 'Shopping.view.databrowsercalendar.DBCalendarView',
	requires : [ 'Shopping.view.databrowsercalendar.DBCalendarController',
	             'Shopping.view.databrowsercalendar.DBCalendarView','Ext.layout.container.Card',
	             'Ext.calendar.view.Day', 'Ext.calendar.view.Week',
	             'Ext.calendar.view.Month',
	             'Ext.calendar.form.EventDetails',
	             'Ext.calendar.data.EventMappings'],
	controller : 'databrowsercalendar',
	items : [],
	listeners : {
		afterrender : 'loadData',
		scope : "controller"
	}
});

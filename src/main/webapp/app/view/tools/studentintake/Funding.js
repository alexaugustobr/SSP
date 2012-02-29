Ext.define('Ssp.view.tools.studentintake.Funding', {
	extend: 'Ext.form.Panel',
	id : 'StudentIntakeFunding',
    
    width: '100%',
    height: '100%',
    bodyPadding: 5,

    // Fields will be arranged vertically, stretched to full width
    layout: 'anchor',
    defaults: {
        anchor: '100%'
    },

    // The fields
    defaultType: 'checkbox',
    items: [],
	

	initComponent: function() {	
		this.superclass.initComponent.call(this, arguments);
	}
	
});
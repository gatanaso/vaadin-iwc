'use strict';
/**
 * Iwc frontend wrapper.
 */
window.org_vaadin_iwc_Iwc = function() {

  var iwc = new ozpIwc.Client("//aml-development.github.io/ozp-iwc");
  var dataRef= new iwc.data.Reference("/vaadin-iwc-data");

  /**
	 * Test the IWC connection.
	 */
  this.test = function() {
    iwc.connect().then(function() {
      console.log("IWC Client connected with address: ", iwc.address);
    }).catch(function(err){
      console.error("IWC Client failed to connect: ", err);
 });
  }

  /**
	 * Set the value of the data reference.
	 *
	 * @param data the value to set.
	 */
  this.setData = function(data) {
    dataRef.set(data);
  }
}

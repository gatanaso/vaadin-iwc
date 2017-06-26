'use strict';
/**
 * Iwc frontend wrapper.
 */
window.org_vaadin_iwc_Iwc = function() {

  var iwc = new ozpIwc.Client("https://ozoneplatform.github.io/ozp-iwc");
  var dataRef = new iwc.data.Reference("/vaadin-iwc-data");

  /**
	 * Test the IWC connection.
	 */
  this.test = function() {
    iwc.connect().then(function() {
      console.log("IWC Client connected with address: ", iwc.address);
    }).catch(function(err) {
      console.error("IWC Client failed to connect: ", err);
    });
  }

  /**
	 * Set the value of the data reference.
	 *
	 * @param data the value to set.
	 */
  this.setData = function(data) {
    console.log("setting data: " + data);
    dataRef.set(data);
  }

  /**
   * Adds a watch on the data reference.
   */
  this.addDataWatch = function() {
    console.log("adding a watch on the data reference");
    dataRef.watch(onChange);
  }

  var onChange = function(change, done) {
    // new value === change.newValue
    // old value === change.oldValue
    // resource deleted? === change.deleted
    // done watching? call done()
    var dataLabel = document.getElementsByClassName("data-reference-value");
    dataLabel[0].textContent = JSON.stringify(change, null, 2);
  };

}

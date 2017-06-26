'use strict';
/**
 * Iwc frontend wrapper.
 */
window.org_vaadin_iwc_Iwc = function() {

  var iwc = new ozpIwc.Client("https://ozoneplatform.github.io/ozp-iwc");
  var dataRef = new iwc.data.Reference("/vaadin/iwc/data");

  /**
	 * Connect to the IWC bus.
	 */
  this.connect = function() {
    iwc.connect().then(function() {
      console.log("IWC Client connected with address: ", iwc.address);
    }).catch(function(err) {
      console.error("IWC Client failed to connect: ", err);
    });
  };

  /**
	 * Disconnect from the the IWC bus.
	 */
  this.disconnect = function() {
    iwc.connect().then(function() {
      console.log("IWC Client disconnected");
      iwc.disconnect();
    });
  };

  /**
	 * Set the value of the data reference.
	 *
	 * @param data the value to set.
	 */
  this.set = function(data) {
    console.log("Setting data reference value: " + data);
    dataRef.set(data).catch(function(err) {
      console.log("Error occurred while setting data value: ", err);
    });
  };

  /**
	 * Gets the value of the data reference.
	 */
  this.get = function() {
    dataRef.get().then(function(data) {
      console.log("Data reference value: ", data);
      var dataLabel = document.getElementsByClassName("data-reference-value")[0];
      dataLabel.textContent = data;
    }).catch(function(err) {
      console.log("Could not retrieve data reference value. Reason: ", err);
    });
  };

  // watch data values
  var _watchData = {
    src: null,
    msgId: null
  };

  /**
   * Adds a watch on the data reference.
   */
  this.watch = function() {
    console.log("adding a watch on the data reference");
    dataRef.watch(onChange).then(function(response) {
      _watchData.src = response.dst;
      _watchData.msgId = response.replyTo;
    });
  };

  /**
   * Removes the watch on the data reference.
   */
  this.unwatch = function() {
    console.log("removing the watch on the data reference");
    dataRef.unwatch(_watchData);
  };

  var onChange = function(change, done) {
    // new value === change.newValue
    // old value === change.oldValue
    // resource deleted? === change.deleted
    // done watching? call done()
    if (change.deleted) {
      done(); // unwatch if deleted
    }
    var dataLabel = document.getElementsByClassName("data-reference-value")[0];
    dataLabel.textContent = JSON.stringify(change, null, 2);
  };

}

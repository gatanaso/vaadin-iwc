'use strict';
/**
 * Iwc Data connector.
 */
window.org_vaadin_iwc_IwcData = function() {

  var iwc = new ozpIwc.Client("https://ozoneplatform.github.io/ozp-iwc");

  var config = {
    fullResponse: true
  };
  var dataRef = new iwc.data.Reference("/vaadin/iwc/data", config);

  /**
	 * Set the value of the data reference.
	 *
	 * @param data the value to set.
	 */
  this.set = function(data) {
    console.log("Setting data reference value: " + data);
    dataRef.set(data).catch(function(err) {
      console.error("Error occurred while setting data value: ", err);
    });
  };

  /**
	 * Gets the value of the data reference.
	 */
  this.get = function() {
    dataRef.get().then(function(data) {
      console.log("Data reference value: ", data);
      var dataLabel = document.getElementsByClassName("data-reference-value")[0];
      dataLabel.textContent = JSON.stringify(data, null, 2);
    }).catch(function(err) {
      console.error("Could not retrieve data reference value. Reason: ", err);
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

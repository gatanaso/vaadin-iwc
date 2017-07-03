'use strict';
/**
 * Iwc Data connector.
 */
window.org_vaadin_iwc_IwcData = function() {

  var connector = this;
  var iwc = new ozpIwc.Client("https://ozoneplatform.github.io/ozp-iwc");
  var dataRef;

  // Handle changes from the server-side
  this.onStateChange = function() {
    var dateRefeferenceEndpoint = this.getState().path;
    if (dateRefeferenceEndpoint) {
      console.log('Path set from server: ', dateRefeferenceEndpoint);
      dataRef = new iwc.data.Reference(dateRefeferenceEndpoint, {fullResponse: true});
    }
  };

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
      // execute server callback
      connector.getCallback(data);

      console.log("Data reference value: ", data);
      printInfoMessageToUI(data);
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
    // execute server callback
    connector.watchCallback(change);
    printInfoMessageToUI(change);
  };

  var printInfoMessageToUI = function(data) {
    var dataLabel = document.getElementsByClassName("data-reference-value")[0];
    dataLabel.textContent = JSON.stringify(data, null, 2);
  }
}

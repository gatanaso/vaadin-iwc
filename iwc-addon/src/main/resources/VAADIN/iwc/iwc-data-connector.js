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
	 * Stores the given value to the specified node.
	 *
	 * @param data the value to store in the node.
	 */
  this.set = function(data) {
    console.log("Setting data reference value: " + data);
    dataRef.set(data).catch(function(err) {
      console.error("Error occurred while setting data value: ", err);
    });
  };

  /**
	 * Gathers the node with the specific key.
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

  /**
   * Gathers all nodes who's key matches the given partial-key.
   */
  this.bulkGet = function() {
    dataRef.bulkGet().then(function(values) {
      // execute server callback
      connector.bulkGetCallback(values);

        console.log(JSON.stringify(values, null, 2));
        printInfoMessageToUI(values);
    });
  };

  /**
   * Gathers all node keys who match the given partial-key.
   */
  this.list = function() {
    dataRef.list().then(function(paths) {
      // execute server callback
      connector.listCallback(paths);

      console.log(paths);
      printInfoMessageToUI(paths);
    });
  };

  // watch data values
  var _watchData = {
    src: null,
    msgId: null
  };

  /**
   * Gathers the node with the specific key and calls the registered callback on updates to the node.
   */
  this.watch = function() {
    console.log("adding a watch on the data reference");
    dataRef.watch(onChange).then(function(response) {
      _watchData.src = response.dst;
      _watchData.msgId = response.replyTo;
    });
  };

  /**
   * Unregisters the callback for the node.
   */
  this.unwatch = function() {
    console.log("Removing the watch on the data reference");
    dataRef.unwatch(_watchData);
  };

  /**
   * Deletes the node with the specific key.
   */
  this.delete = function() {
    console.log("Deleting data reference");
    dataRef.delete();
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

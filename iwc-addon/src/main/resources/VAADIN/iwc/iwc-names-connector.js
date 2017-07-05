'use strict';
/**
 * Iwc Names connector.
 */
window.org_vaadin_iwc_IwcNames = function() {

  var connector = this;
  var iwc = new ozpIwc.Client("https://ozoneplatform.github.io/ozp-iwc");
  var namesRef;

  // Handle changes from the server-side
  this.onStateChange = function() {
    var namesRefEndpoint = this.getState().path;
    if (namesRefEndpoint) {
      console.log('Path set from server: ', namesRefEndpoint);
      namesRef = new iwc.names.Reference(namesRefEndpoint, {fullResponse: true});
    }
  };

  /**
	 * Gets the value of the names reference.
	 */
  this.get = function() {
    namesRef.get().then(function(data) {
      // execute server callback
      connector.getCallback(data);

      console.log("Names reference value: ", data);
      printInfoMessageToUI(data);
    }).catch(function(err) {
      console.error("Could not retrieve data reference value. Reason: ", err);
    });
  };

  /**
   * Gathers all nodes who's key matches the given partial-key.
   */
  this.bulkGet = function() {
    namesRef.bulkGet().then(function(values) {
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
    namesRef.list().then(function(paths) {
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
   * Adds a watch on the data reference.
   */
  this.watch = function() {
    console.log("adding a watch on the data reference");
    namesRef.watch(onChange).then(function(response) {
      _watchData.src = response.dst;
      _watchData.msgId = response.replyTo;
    });
  };

  /**
   * Removes the watch on the data reference.
   */
  this.unwatch = function() {
    console.log("removing the watch on the data reference");
    namesRef.unwatch(_watchData);
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
    var dataLabel = document.getElementsByClassName("names-reference-value")[0];
    dataLabel.textContent = JSON.stringify(data, null, 2);
  }
}

'use strict';
/**
 * Iwc Intents connector.
 */
window.org_vaadin_iwc_IwcIntents = function() {

  var connector = this;
  var iwc = new ozpIwc.Client("https://ozoneplatform.github.io/ozp-iwc");

  var intentsEndpoint;
  var baseRef;
  var vaadinAppIntentsRef;

  // Handle changes from the server-side
  this.onStateChange = function() {
    var intentsEndpoint = this.getState().path;
    if (intentsEndpoint) {
      console.log('Path set from server: ', intentsEndpoint);
      baseRef = new iwc.intents.Reference(intentsEndpoint, {fullResponse: true});
      vaadinAppIntentsRef = new iwc.intents.Reference(intentsEndpoint + "/vaadin.iwc.extension", {fullResponse: true});
    }
  };

  /**
	 * Gets the value of the intents reference.
	 */
  this.get = function() {
    baseRef.get().then(function(data) {
      // execute server callback
      connector.getCallback(data);

      console.log("Intents reference: ", data);
      printInfoMessageToUI(data);
    }).catch(function(err) {
      console.error("Could not retrieve data reference value. Reason: ", err);
    });
  };

  var config = {
    "label": "Vaadin Application",
    "icon": "https://vaadin.com/documents/10187/10609024/symbol-preview/d23e77b1-6efd-4bc6-b77c-ffefdb108674?t=1437651445828"
  };

  var onInvoke = function(payload) {
    // execute server callback
    connector.invocationHandler(payload);

    console.log('Handling IWC Intent: ', payload);
    printInfoMessageToUI(payload);
    return "IWC intent handled from Vaadin app";
  };

  /**
	 * Registers the application to handle an IWC Intent.
	 */
  this.register = function() {
    vaadinAppIntentsRef.register(config, onInvoke).then(function(resolution) {
      if ("ok" === resolution.response) {
        printInfoMessageToUI(resolution.entity);
      } else {
        console.error("Could not register for intent");
      }
    });
  };

  var counter = 0;
  var broadcastCounter = 0;

  /**
	 * Invoke an action by broadcasting to all handlers.
	 */
  this.broadcast = function() {
    baseRef.broadcast({
      "message": "vaadin application broadcast invocation: " + broadcastCounter++
    }).then(function(response) {
      console.log("invoke broadcast resolution: ", response);
    });
  };

  /**
	 * Invoke an action.
	 */
  this.invoke = function() {
    baseRef.invoke({
      "message": "vaadin application intent invocation: " + counter++
    }).then(function(response) {
      console.log("invoke resolution: ", response);
    });
  };

  var printInfoMessageToUI = function(data) {
    var infoLabel = document.getElementsByClassName("intents-reference-value")[0];
    infoLabel.textContent = JSON.stringify(data, null, 2);
  }
}

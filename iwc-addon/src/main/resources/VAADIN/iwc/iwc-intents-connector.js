'use strict';
/**
 * Iwc Intents connector.
 */
window.org_vaadin_iwc_IwcIntents = function() {

  var iwc = new ozpIwc.Client("https://ozoneplatform.github.io/ozp-iwc");

  var intentsEndpoint = "/vaadin/iwc/intents";
  var config = {
    fullResponse: true
  };
  var intentsRef = new iwc.intents.Reference(intentsEndpoint, config);

  var config = {
    "label": "Vaadin Application"
  };

  var onInvoke = function(payload) {
    console.log('Handling IWC Intent: ', payload);
    var infoLabel = document.getElementsByClassName("intents-reference-value")[0];
    infoLabel.textContent = "Handling IWC Intent: " + JSON.stringify(payload);
    return "IWC intent handled from Vaadin app";
  };

  /**
	 * Registers the application to handle an IWC Intent.
	 */
  this.register = function() {
    intentsRef.register(config, onInvoke).then(function(resolution) {
      if ("ok" === resolution.response) {
        var infoLabel = document.getElementsByClassName("intents-reference-value")[0];
        infoLabel.textContent = "Registered to: " + JSON.stringify(resolution.entity, null, 2);
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
    intentsRef.broadcast({
      "message": "vaadin application broadcast invocation: " + broadcastCounter++
    }).then(function(response) {
      console.log("invoke handler resolution: ", response);
    });
  };

  /**
	 * Invoke an action.
	 */
  this.invoke = function() {
    intentsRef.invoke(intentsEndpoint, {
      "message": "vaadin application intent invocation: " + counter++
    }).then(function(response) {
      console.log("invoke handler resolution: ", response);
    });
  };
}

'use strict';
/**
 * Iwc Intents connector.
 */
window.org_vaadin_iwc_IwcIntents = function() {

  var iwc = new ozpIwc.Client("https://ozoneplatform.github.io/ozp-iwc");

  var intentsEndpoint = "/vaadin/iwc/intents";
  var baseRef = new iwc.intents.Reference(intentsEndpoint, {fullResponse: true});
  var vaadinAppIntentsRef = new iwc.intents.Reference(intentsEndpoint + "/vaadin.iwc.extension", {fullResponse: true});

  /**
	 * Gets the value of the intents reference.
	 */
  this.get = function() {
    baseRef.get().then(function(data) {
      console.log("Intents reference: ", data);
      var infoLabel = document.getElementsByClassName("intents-reference-value")[0];
      infoLabel.textContent = JSON.stringify(data, null, 2);
    }).catch(function(err) {
      console.error("Could not retrieve data reference value. Reason: ", err);
    });
  };

  var config = {
    "label": "Vaadin Application",
    "icon": "https://vaadin.com/documents/10187/10609024/symbol-preview/d23e77b1-6efd-4bc6-b77c-ffefdb108674?t=1437651445828"
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
    vaadinAppIntentsRef.register(config, onInvoke).then(function(resolution) {
      if ("ok" === resolution.response) {
        var infoLabel = document.getElementsByClassName("intents-reference-value")[0];
        infoLabel.textContent = "Vaadin app intent handler: " + JSON.stringify(resolution.entity, null, 2);
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

}

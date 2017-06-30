package org.vaadin.iwc;

import com.vaadin.annotations.JavaScript;
import com.vaadin.server.AbstractJavaScriptExtension;

/**
 * The IWC Intents Vaadin JavaScript Extension.
 * <p>
 * Exposes the IWC Intents API.
 * 
 * @author gatanaso
 */
@JavaScript({ "https://ozoneplatform.github.io/ozp-iwc/js/ozpIwc-client.js", "vaadin://iwc/iwc-intents-connector.js" })
public class IwcIntents extends AbstractJavaScriptExtension {

	private static final long serialVersionUID = 1L;

	/**
	 * Logs to the browser console the value of the data reference.
	 */
	public void get() {
		callFunction("get");
	}

	/**
	 * Registers the application to handle and IWC Intent.
	 */
	public void register() {
		callFunction("register");
	}
	
	/**
	 * Unregisters the application to handle and IWC Intent.
	 */
	public void unregister() {
		callFunction("unregister");
	}	

	/**
	 * Invokes an action.
	 */
	public void invoke() {
		callFunction("invoke");
	}

	/**
	 * Broadcast an action.
	 */
	public void broadcast() {
		callFunction("broadcast");
	}
}

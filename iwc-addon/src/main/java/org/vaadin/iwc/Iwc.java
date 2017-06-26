package org.vaadin.iwc;

import com.vaadin.annotations.JavaScript;
import com.vaadin.server.AbstractJavaScriptExtension;

/**
 * The IWC Vaadin JavaScript Extension.
 * 
 * @author gatanaso
 *
 */
@JavaScript({ "https://ozoneplatform.github.io/ozp-iwc/js/ozpIwc-client.js", "vaadin://iwc/iwc-connector.js" })
public class Iwc extends AbstractJavaScriptExtension {

	private static final long serialVersionUID = 1L;

	/**
	 * Connects to the IWC bus.
	 */
	public void connect() {
		callFunction("connect");
	}

	/**
	 * Disconnects to the IWC bus.
	 */
	public void disconnect() {
		callFunction("disconnect");
	}

	/**
	 * Set the value of the data reference.
	 * 
	 * @param data
	 *            the value to set.
	 */
	public void set(String data) {
		callFunction("set", data);
	}

	/**
	 * Gets the value of the data reference.
	 */
	public void get() {
		callFunction("get");
	}

	/**
	 * Adds a watch on the data reference.
	 */
	public void watch() {
		callFunction("watch");
	}

	/**
	 * Adds a watch on the data reference.
	 */
	public void unwatch() {
		callFunction("unwatch");
	}

}

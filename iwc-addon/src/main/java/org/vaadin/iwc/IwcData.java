package org.vaadin.iwc;

import com.vaadin.annotations.JavaScript;
import com.vaadin.server.AbstractJavaScriptExtension;

/**
 * The IWC Data Vaadin JavaScript Extension.
 * <p>
 * Exposes the IWC Data API.
 * 
 * @author gatanaso
 */
@JavaScript({ "https://ozoneplatform.github.io/ozp-iwc/js/ozpIwc-client.js", "vaadin://iwc/iwc-data-connector.js" })
public class IwcData extends AbstractJavaScriptExtension {

	private static final long serialVersionUID = 1L;

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
	 * Logs to the browser console the value of the data reference.
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

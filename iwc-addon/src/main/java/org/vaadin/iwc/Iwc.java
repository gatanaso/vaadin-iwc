package org.vaadin.iwc;

import com.vaadin.annotations.JavaScript;
import com.vaadin.server.AbstractJavaScriptExtension;

/**
 * The IWC Vaadin JavaScript Extension.
 * 
 * @author gatanaso
 *
 */
@JavaScript({
	"https://ozoneplatform.github.io/ozp-iwc/js/ozpIwc-client.js",
	"vaadin://iwc/iwc-connector.js"})
public class Iwc extends AbstractJavaScriptExtension {

	private static final long serialVersionUID = 1L;

	/**
	 * Test the IWC connection.
	 */
	public void test() {
		callFunction("test");
	}
	
	/**
	 * Set the value of the data reference.
	 * 
	 * @param data the value to set.
	 */
	public void setData(String data) {
		callFunction("setData", data);
	}
}

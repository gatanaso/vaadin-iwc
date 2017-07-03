package org.vaadin.iwc;

import java.util.function.Consumer;

import com.vaadin.annotations.JavaScript;
import com.vaadin.server.AbstractJavaScriptExtension;
import com.vaadin.ui.JavaScriptFunction;

import elemental.json.JsonArray;

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

	private Consumer<String> getCallback;
	private Consumer<String> watchCallback;

	@SuppressWarnings("serial")
	public IwcData() {

		addFunction("getCallback", new JavaScriptFunction() {
			@Override
			public void call(JsonArray arguments) {
				if (getCallback != null) {
					// handle client-side response
					getCallback.accept(arguments.toJson());
				}
			}
		});
		
		addFunction("watchCallback", new JavaScriptFunction() {
			@Override
			public void call(JsonArray arguments) {
				if (watchCallback != null) {
					// handle client-side response
					watchCallback.accept(arguments.toJson());
				}
			}
		});
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
	 * <p>
	 * The response from the client-side is sent back when the value becomes
	 * available. To use this value on the server-side a callback has to be
	 * registered via {{@link #registerGetCallback(Consumer)} method.
	 */
	public void get() {
		callFunction("get");
	}

	/**
	 * Registers a callback that accepts the value from the call to the {{@link #get()} method.
	 * 
	 * @param callback the method to execute when the value becomes available.
	 */
	public void registerGetCallback(Consumer<String> callback) {
		this.getCallback = callback;
	}

	/**
	 * Adds a watch on the data reference.
	 */
	public void watch() {
		callFunction("watch");
	}
	
	/**
	 * Registers a callback that accepts the value from the call to the {{@link #watch()} method.
	 * 
	 * @param callback the method to execute when the value becomes available.
	 */
	public void registerWatchCallback(Consumer<String> callback) {
		this.watchCallback = callback;
	}	

	/**
	 * Adds a watch on the data reference.
	 */
	public void unwatch() {
		callFunction("unwatch");
	}

	/**
	 * Sets the base path for the data resource.
	 * 
	 * @param path
	 *            the path to set.
	 */
	public void setPath(String path) {
		getState().path = path;
	}

	@Override
	protected IwcDataState getState() {
		return (IwcDataState) super.getState();
	}
}

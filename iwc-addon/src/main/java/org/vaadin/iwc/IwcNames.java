package org.vaadin.iwc;

import java.util.function.Consumer;

import com.vaadin.annotations.JavaScript;
import com.vaadin.server.AbstractJavaScriptExtension;
import com.vaadin.ui.JavaScriptFunction;

import elemental.json.JsonArray;

/**
 * The IWC Names Vaadin JavaScript Extension.
 * <p>
 * Exposes the IWC Names API.
 * 
 * @author gatanaso
 */
@JavaScript({ "https://ozoneplatform.github.io/ozp-iwc/js/ozpIwc-client.js", "vaadin://iwc/iwc-names-connector.js" })
public class IwcNames extends AbstractJavaScriptExtension {

	private static final long serialVersionUID = 1L;

	private Consumer<String> getCallback;
	private Consumer<String> bulkGetCallback;
	private Consumer<String> watchCallback;
	private Consumer<String> listCallback;

	@SuppressWarnings("serial")
	public IwcNames() {

		addFunction("getCallback", new JavaScriptFunction() {
			@Override
			public void call(JsonArray arguments) {
				if (getCallback != null) {
					// handle client-side response
					getCallback.accept(arguments.toJson());
				}
			}
		});
		
		addFunction("bulkGetCallback", new JavaScriptFunction() {
			@Override
			public void call(JsonArray arguments) {
				if (bulkGetCallback != null) {
					// handle client-side response
					bulkGetCallback.accept(arguments.toJson());
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
		
		addFunction("listCallback", new JavaScriptFunction() {
			@Override
			public void call(JsonArray arguments) {
				if (listCallback != null) {
					// handle client-side response
					listCallback.accept(arguments.toJson());
				}
			}
		});		
	}

	/**
	 * Gets the value of the names reference.
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
	 * Gathers all nodes who's key matches the given partial-key.
	 * <p>
	 * The response from the client-side is sent back when the value becomes
	 * available. To use this value on the server-side a callback has to be
	 * registered via {{@link #registerBulkGetCallback(Consumer)} method.
	 */
	public void bulkGet() {
		callFunction("bulkGet");
	}

	/**
	 * Registers a callback that accepts the value from the call to the
	 * {{@link #bulkGet()} method.
	 * 
	 * @param callback
	 *            the method to execute when the value becomes available.
	 */
	public void registerBulkGetCallback(Consumer<String> callback) {
		this.bulkGetCallback = callback;
	}	

	/**
	 * Adds a watch on the names reference.
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
	 * Gathers all node keys who match the given partial-key.
	 * <p>
	 * The response from the client-side is sent back when the value becomes
	 * available. To use this value on the server-side a callback has to be
	 * registered via {{@link #registerListCallback(Consumer)} method.
	 */
	public void list() {
		callFunction("list");
	}

	/**
	 * Registers a callback that accepts the value from the call to the
	 * {{@link #list()} method.
	 * 
	 * @param callback
	 *            the method to execute when the value becomes available.
	 */
	public void registerListCallback(Consumer<String> callback) {
		this.listCallback = callback;
	}	

	/**
	 * Adds a watch on the names reference.
	 */
	public void unwatch() {
		callFunction("unwatch");
	}

	/**
	 * Sets the base path for the names resource.
	 * 
	 * @param path
	 *            the path to set.
	 */
	public void setPath(String path) {
		getState().path = path;
	}

	@Override
	protected IwcNamesState getState() {
		return (IwcNamesState) super.getState();
	}

}

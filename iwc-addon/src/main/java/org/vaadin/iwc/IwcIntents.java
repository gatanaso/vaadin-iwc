package org.vaadin.iwc;

import java.util.function.Consumer;

import com.vaadin.annotations.JavaScript;
import com.vaadin.server.AbstractJavaScriptExtension;
import com.vaadin.ui.JavaScriptFunction;

import elemental.json.JsonArray;

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

	private Consumer<String> getCallback;
	private Consumer<String> invocationHandler;

	@SuppressWarnings("serial")
	public IwcIntents() {

		addFunction("getCallback", new JavaScriptFunction() {
			@Override
			public void call(JsonArray arguments) {
				if (getCallback != null) {
					// handle client-side response
					getCallback.accept(arguments.toJson());
				}
			}
		});
		
		addFunction("invocationHandler", new JavaScriptFunction() {
			@Override
			public void call(JsonArray arguments) {
				if (invocationHandler != null) {
					// handle client-side response
					invocationHandler.accept(arguments.toJson());
				}
			}
		});
	}

	/**
	 * Gathers the node with the specific key
	 * <p>
	 * The response from the client-side is sent back when the value becomes
	 * available. To use this value on the server-side a callback has to be
	 * registered via {{@link #registerGetCallback(Consumer)} method.
	 */
	public void get() {
		callFunction("get");
	}

	/**
	 * Registers a callback that accepts the value from the call to the
	 * {{@link #get()} method.
	 * 
	 * @param callback
	 *            the method to execute when the value becomes available.
	 */
	public void registerGetCallback(Consumer<String> callback) {
		this.getCallback = callback;
	}

	/**
	 * Registers the application to handle and IWC Intent.
	 */
	public void register() {
		callFunction("register");
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

	/**
	 * Sets the base path for the intents resource.
	 * 
	 * @param path
	 *            the path to set.
	 */
	public void setPath(String path) {
		getState().path = path;
	}
	
	/**
	 * Sets the widget handler label.
	 * <p>
	 * A short string noting the widget handling the intent (typically the widget title).
	 * 
	 * @param label the label to set.
	 */
	public void setLabel(String label) {
		getState().label = label;
	}
	
	/**
	 * Set the icon url.
	 * <p>
	 * A url path to a icon to use for the widget.
	 * 
	 * @param iconUrl the icon url to set.
	 */
	public void setIconUrl(String iconUrl) {
		getState().iconUrl = iconUrl;
	}

	/**
	 * Sets the invocation handler.
	 * <p>
	 * When this applicaiton is invoked, this method is executed with the
	 * payload passed during the invocation.
	 * 
	 * @param handler the invocation handler.
	 */
	public void setInvocationHandler(Consumer<String> handler) {
		this.invocationHandler = handler;
	}

	@Override
	protected IwcIntentsState getState() {
		return (IwcIntentsState) super.getState();
	}
}

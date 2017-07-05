package org.vaadin.iwc.demo;

import org.vaadin.iwc.IwcIntents;

import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

/**
 * Demo component for the IWC Intents API.
 * 
 * @author gatanaso
 */
public class IwcIntentsDemoComponent extends VerticalLayout {

	private static final long serialVersionUID = 1L;

	private static final String INTENTS_PATH = "/vaadin/iwc/intents";
	private static final String INTENTS_VALUE_LABEL_CLASS = "intents-reference-value";

	public IwcIntentsDemoComponent() {

		IwcIntents iwcIntents = new IwcIntents();
		iwcIntents.setPath(INTENTS_PATH);
		iwcIntents.setLabel("Vaadin Application");
		iwcIntents.setIconUrl("https://vaadin.com/documents/10187/10609024/symbol-preview/d23e77b1-6efd-4bc6-b77c-ffefdb108674?t=1437651445828");
		iwcIntents.registerGetCallback(this::getCallback);
		iwcIntents.setInvocationHandler(this::invocationHandler);
		addExtension(iwcIntents);

		Button registerBtn = new Button("register");
		registerBtn.addClickListener(event -> iwcIntents.register());

		Button invokeBtn = new Button("invoke external app");
		invokeBtn.addClickListener(event -> iwcIntents.invoke());

		Button broadcastBtn = new Button("broadcast");
		broadcastBtn.addClickListener(event -> iwcIntents.broadcast());

		Button getData = new Button("get");
		getData.addClickListener(event -> iwcIntents.get());

		HorizontalLayout dataApiActions = new HorizontalLayout(registerBtn, invokeBtn, broadcastBtn,
				getData);
		dataApiActions.setSpacing(true);

		Label infoLbl = new Label();
		infoLbl.setContentMode(ContentMode.PREFORMATTED);
		infoLbl.addStyleName(INTENTS_VALUE_LABEL_CLASS);
		infoLbl.setWidth(80, Unit.PERCENTAGE);

		Label intentsReference = new Label("Intents reference path: " + INTENTS_PATH);

		addComponents(intentsReference, dataApiActions, infoLbl);
		setSpacing(true);
		setMargin(false);
	}
	
	private void getCallback(String value) {
		System.out.println("get() method return value: " + value);
	}
	
	private void invocationHandler(String value) {
		System.out.println("application inovked with paylaod: " + value);
	}	
}

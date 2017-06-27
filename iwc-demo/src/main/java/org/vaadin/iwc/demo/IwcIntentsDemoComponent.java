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
	private static final String INTENTS_VALUE_LABEL_CLASS = "intents-reference-value";

	public IwcIntentsDemoComponent() {

		IwcIntents iwcIntents = new IwcIntents();
		addExtension(iwcIntents);

		Button registerBtn = new Button("register");
		registerBtn.addClickListener(event -> iwcIntents.register());

		Button invokeBtn = new Button("invoke external app");
		invokeBtn.addClickListener(event -> iwcIntents.invoke());

		Button broadcastBtn = new Button("broadcast");
		broadcastBtn.addClickListener(event -> iwcIntents.broadcast());

		HorizontalLayout dataApiActions = new HorizontalLayout(registerBtn, invokeBtn, broadcastBtn);
		dataApiActions.setSpacing(true);

		Label dataApiResultLbl = new Label();
		dataApiResultLbl.setContentMode(ContentMode.PREFORMATTED);
		dataApiResultLbl.addStyleName(INTENTS_VALUE_LABEL_CLASS);

		Label dataReference = new Label("Intents reference path: /vaadin/iwc/intents");

		addComponents(dataReference, dataApiActions, dataApiResultLbl);
		setSpacing(true);
	}
}

package org.vaadin.iwc.demo;

import org.vaadin.iwc.IwcIntents;

import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
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

		Button unregisterBtn = new Button("unregister");
		unregisterBtn.addClickListener(event -> iwcIntents.unregister());

		TextField dataInput = new TextField();
		Button setDataBtn = new Button("set");
		setDataBtn.addClickListener(event -> iwcIntents.set(dataInput.getValue()));
		HorizontalLayout setAction = new HorizontalLayout(dataInput, setDataBtn);
		setAction.setSpacing(true);

		Button getData = new Button("get");
		getData.addClickListener(event -> iwcIntents.get());

		HorizontalLayout dataApiActions = new HorizontalLayout(registerBtn, invokeBtn, broadcastBtn, unregisterBtn,
				getData);
		dataApiActions.setSpacing(true);

		Label infoLbl = new Label();
		infoLbl.setContentMode(ContentMode.PREFORMATTED);
		infoLbl.addStyleName(INTENTS_VALUE_LABEL_CLASS);
		infoLbl.setWidth(80, Unit.PERCENTAGE);

		Label intentsReference = new Label("Intents reference path: /vaadin/iwc/intents");

		addComponents(intentsReference, dataApiActions, infoLbl);
		setSpacing(true);
	}
}

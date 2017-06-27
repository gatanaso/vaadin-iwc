package org.vaadin.iwc.demo;

import org.vaadin.iwc.IwcData;

import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

/**
 * Demo component for the IWC Data API.
 * 
 * @author gatanaso
 */
public class IwcDataDemoComponent extends VerticalLayout {

	private static final long serialVersionUID = 1L;
	private static final String DATA_VALUE_LABEL_CLASS = "data-reference-value";

	public IwcDataDemoComponent() {

		IwcData iwcData = new IwcData();
		addExtension(iwcData);

		Button watch = new Button("watch");
		watch.addClickListener(event -> iwcData.watch());

		Button unwatch = new Button("unwatch");
		unwatch.addClickListener(event -> iwcData.unwatch());

		TextField dataInput = new TextField();
		Button setDataBtn = new Button("set");
		setDataBtn.addClickListener(event -> iwcData.set(dataInput.getValue()));
		HorizontalLayout setAction = new HorizontalLayout(dataInput, setDataBtn);
		setAction.setSpacing(true);

		Button getData = new Button("get");
		getData.addClickListener(event -> iwcData.get());

		HorizontalLayout dataApiActions = new HorizontalLayout(watch, unwatch, setAction, getData);
		dataApiActions.setSpacing(true);

		Label dataApiResultLbl = new Label();
		dataApiResultLbl.setContentMode(ContentMode.PREFORMATTED);
		dataApiResultLbl.addStyleName(DATA_VALUE_LABEL_CLASS);

		Label dataReference = new Label("Data reference path: /vaadin/iwc/data");
		
		addComponents(dataReference, dataApiActions, dataApiResultLbl);
		setSpacing(true);
	}
}

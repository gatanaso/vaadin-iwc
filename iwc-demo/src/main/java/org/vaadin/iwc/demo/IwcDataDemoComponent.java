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

	private static final String DATA_PATH = "/vaadin/iwc/data";
	private static final String DATA_VALUE_LABEL_CLASS = "data-reference-value";

	public IwcDataDemoComponent() {

		IwcData iwcData = new IwcData();
		iwcData.setPath(DATA_PATH);
		iwcData.registerGetCallback(this::getCallback);
		iwcData.registerBulkGetCallback(this::bulkGetCallback);
		iwcData.registerWatchCallback(this::watchCallback);
		iwcData.registerListCallback(this::listCallback);
		addExtension(iwcData);

		Button watch = new Button("watch");
		watch.addClickListener(event -> iwcData.watch());

		Button list = new Button("list");
		list.addClickListener(event -> iwcData.list());

		TextField dataInput = new TextField();
		Button setDataBtn = new Button("set");
		setDataBtn.addClickListener(event -> iwcData.set(dataInput.getValue()));
		HorizontalLayout set = new HorizontalLayout(dataInput, setDataBtn);
		set.setSpacing(true);

		Button bulkGet = new Button("bulk get");
		bulkGet.addClickListener(event -> iwcData.bulkGet());

		Button get = new Button("get");
		get.addClickListener(event -> iwcData.get());

		HorizontalLayout dataApiActions = new HorizontalLayout(watch, set, get, bulkGet, list);
		dataApiActions.setSpacing(true);

		Label infoLbl = new Label();
		infoLbl.setContentMode(ContentMode.PREFORMATTED);
		infoLbl.addStyleName(DATA_VALUE_LABEL_CLASS);
		infoLbl.setWidth(80, Unit.PERCENTAGE);

		Label dataReference = new Label("Data reference path: " + DATA_PATH);
		addComponents(dataReference, dataApiActions, infoLbl);
		setSpacing(true);
		setMargin(false);
	}

	private void getCallback(String value) {
		System.out.println("get() method return value: " + value);
	}

	private void bulkGetCallback(String value) {
		System.out.println("bulkGet() method return value: " + value);
	}

	private void watchCallback(String value) {
		System.out.println("watch() method return value: " + value);
	}

	private void listCallback(String value) {
		System.out.println("list() method return value: " + value);
	}
}

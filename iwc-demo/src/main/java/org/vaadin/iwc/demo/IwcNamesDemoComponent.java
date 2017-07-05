package org.vaadin.iwc.demo;

import org.vaadin.iwc.IwcNames;

import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

/**
 * Demo component for the IWC Names API.
 * 
 * @author gatanaso
 */
public class IwcNamesDemoComponent extends VerticalLayout {

	private static final long serialVersionUID = 1L;

	private static final String NAMES_VALUE_LABEL_CLASS = "names-reference-value";
	
	public IwcNamesDemoComponent(String path, boolean addInfoLbl) {

		IwcNames iwcNames = new IwcNames();
		iwcNames.setPath(path);
		iwcNames.registerGetCallback(this::getCallback);
		iwcNames.registerWatchCallback(this::watchCallback);
		iwcNames.registerListCallback(this::listCallback);
		addExtension(iwcNames);

		Button watch = new Button("watch");
		watch.addClickListener(event -> iwcNames.watch());

		Button list = new Button("list");
		list.addClickListener(event -> iwcNames.list());		

		Button getData = new Button("get");
		getData.addClickListener(event -> iwcNames.get());
		
		Button bulkGet = new Button("bulkGet");
		bulkGet.addClickListener(event -> iwcNames.bulkGet());		

		HorizontalLayout dataApiActions = new HorizontalLayout(watch, list, getData, bulkGet);
		dataApiActions.setSpacing(true);
		
		Label infoLbl = new Label();
		infoLbl.setContentMode(ContentMode.PREFORMATTED);
		infoLbl.addStyleName(NAMES_VALUE_LABEL_CLASS);
		infoLbl.setWidth(80, Unit.PERCENTAGE);

		Label dataReference = new Label("Names reference path: " + path);
		addComponents(dataReference, dataApiActions);
		if (addInfoLbl) {
			addComponent(infoLbl);
		}
		setSpacing(true);
		setMargin(false);
	}
	
	private void getCallback(String value) {
		System.out.println("get() method return value: " + value);
	}
	
	private void watchCallback(String value) {
		System.out.println("watch() method return value: " + value);
	}
	
	private void listCallback(String value) {
		System.out.println("list() method return value: " + value);
	}
}

package org.vaadin.iwc.demo;

import javax.servlet.annotation.WebServlet;

import org.vaadin.iwc.IwcData;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

@Theme("valo")
@SuppressWarnings("serial")
public class MyUI extends UI {

	@WebServlet(value = "/*", asyncSupported = true)
	@VaadinServletConfiguration(productionMode = false, ui = MyUI.class)
	public static class Servlet extends VaadinServlet {
	}

	@Override
	protected void init(VaadinRequest request) {

		IwcData iwcData = new IwcData();
		this.addExtension(iwcData);

		// Data API examples
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
		dataApiResultLbl.addStyleName("data-reference-value");

		Label dataReference = new Label("Data reference path: /vaadin/iwc/data");
		VerticalLayout content = new VerticalLayout(dataReference, dataApiActions, dataApiResultLbl);
		content.setSpacing(true);
		setContent(content);
	}
}

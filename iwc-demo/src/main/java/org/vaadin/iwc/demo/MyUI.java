package org.vaadin.iwc.demo;

import javax.servlet.annotation.WebServlet;

import org.vaadin.iwc.Iwc;

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

		Iwc iwc = new Iwc();
		this.addExtension(iwc);

		Button test = new Button("Test IWC connection");
		test.addClickListener(event -> iwc.test());
		
		Button addDataWatch = new Button("Add data watch");
		addDataWatch.addClickListener(event -> iwc.addDataWatch());

		HorizontalLayout btnContainer = new HorizontalLayout(test, addDataWatch);
		btnContainer.setSpacing(true);		
		
		TextField dataInput = new TextField();
		Button setDataBtn = new Button("Set data");
		setDataBtn.addClickListener(event -> iwc.setData(dataInput.getValue()));
		HorizontalLayout dataContainer = new HorizontalLayout(dataInput, setDataBtn);
		dataContainer.setSpacing(true);

		Label dataValueLbl = new Label();
		dataValueLbl.setContentMode(ContentMode.PREFORMATTED);
		dataValueLbl.addStyleName("data-reference-value");

		VerticalLayout content = new VerticalLayout(btnContainer, dataContainer, dataValueLbl);
		content.setSpacing(true);
		setContent(content);
	}
}

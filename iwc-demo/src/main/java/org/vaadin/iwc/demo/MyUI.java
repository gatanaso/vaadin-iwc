package org.vaadin.iwc.demo;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
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

		IwcDataDemoComponent iwcData = new IwcDataDemoComponent();
		IwcIntentsDemoComponent iwcIntents = new IwcIntentsDemoComponent();

		IwcNamesDemoComponent iwcNamesApi = new IwcNamesDemoComponent("/api", false);
		IwcNamesDemoComponent iwcNamesAddress = new IwcNamesDemoComponent("/address", false);
		IwcNamesDemoComponent iwcNamesMutlicast = new IwcNamesDemoComponent("/multicast", false);
		IwcNamesDemoComponent iwcNamesRouter = new IwcNamesDemoComponent("/router", true);

		VerticalLayout content = new VerticalLayout(iwcData, iwcIntents, iwcNamesApi, iwcNamesAddress,
				iwcNamesMutlicast, iwcNamesRouter);
		content.setSpacing(true);
		setContent(content);
	}
}

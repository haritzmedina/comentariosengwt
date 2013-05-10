package index.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;

public class Registro implements EntryPoint {

	@Override
	public void onModuleLoad() {
		RootPanel rootPanel = RootPanel.get("registerContainer");
		rootPanel.setSize("100%", "100%");
		
		Label labelUsername = new Label("Username:");
		rootPanel.add(labelUsername, 77, 57);
		labelUsername.setSize("65px", "30px");
		
		TextBox textBox = new TextBox();
		rootPanel.add(textBox, 168, 57);
		textBox.setSize("159px", "18px");
		
		Label labelContrasenya = new Label("Contrase\u00F1a:");
		rootPanel.add(labelContrasenya, 77, 104);
		
		TextBox textBox_1 = new TextBox();
		rootPanel.add(textBox_1, 168, 104);
		textBox_1.setSize("159px", "18px");

	}
}

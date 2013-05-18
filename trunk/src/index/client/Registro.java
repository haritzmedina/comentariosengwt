package index.client;

import index.client.services.RegistroService;
import index.client.services.RegistroServiceAsync;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.i18n.client.HasDirection.Direction;

public class Registro implements EntryPoint {
	
	private final RegistroServiceAsync registroService = GWT.create(RegistroService.class);

	@Override
	public void onModuleLoad() {
		RootPanel rootPanel = RootPanel.get("registerContainer");
		rootPanel.setSize("100%", "100%");
		
		Label labelUsername = new Label("Username:");
		rootPanel.add(labelUsername, 84, 57);
		labelUsername.setSize("67px", "30px");
		
		final TextBox textBoxUsername = new TextBox();
		rootPanel.add(textBoxUsername, 168, 57);
		textBoxUsername.setSize("159px", "18px");
		
		Label labelContrasenya = new Label("Contrase\u00F1a:");
		rootPanel.add(labelContrasenya, 77, 104);
		labelContrasenya.setSize("74px", "20px");
		
		final TextBox textBoxContrasenya = new TextBox();
		rootPanel.add(textBoxContrasenya, 168, 104);
		textBoxContrasenya.setSize("159px", "18px");
		
		Label labelContrasenya2 = new Label("Repetir contrase\u00F1a:");
		rootPanel.add(labelContrasenya2, 34, 154);
		labelContrasenya2.setSize("117px", "22px");
		
		final TextBox textBoxContrasenya2 = new TextBox();
		rootPanel.add(textBoxContrasenya2, 168, 154);
		textBoxContrasenya2.setSize("159px", "16px");
		
		Label lblEmail = new Label("Email:");
		rootPanel.add(lblEmail, 113, 199);
		lblEmail.setSize("38px", "20px");
		
		final TextBox textBoxEmail = new TextBox();
		rootPanel.add(textBoxEmail, 168, 197);
		textBoxEmail.setSize("159px", "16px");
		
		final Label labelInfo = new Label("Label Informativo");
		
		Button buttonRegistrarse = new Button("Registrarse");
		buttonRegistrarse.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				if(textBoxContrasenya.getText().compareTo(textBoxContrasenya2.getText())==0)
					registroService.registrar(textBoxUsername.getText(), textBoxEmail.getText(), textBoxContrasenya.getText(), new AsyncCallback<Integer>()
					{

						@Override
						public void onFailure(Throwable caught) {
							// TODO Auto-generated method stub
							labelInfo.setText("Se ha producido un error al conectar con la BD");
							
						}

						@Override
						public void onSuccess(Integer result) {
							// TODO Auto-generated method stub
							labelInfo.setText("Registro completado");
						}
						
					});
				else
					labelInfo.setText("Contraseñas no coinciden");
					
			}
		});
		rootPanel.add(buttonRegistrarse, 168, 253);
		rootPanel.add(labelInfo, 114, 285);

	}
}

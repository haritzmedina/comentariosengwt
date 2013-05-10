package index.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.event.dom.client.FocusHandler;
import com.google.gwt.event.dom.client.FocusEvent;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.i18n.client.HasDirection.Direction;
import com.google.gwt.user.client.ui.ValueBoxBase.TextAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;


/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Login implements EntryPoint {

	/**
	 * Create a remote service proxy to talk to the server-side Greeting service.
	 */
	
	private final LoginServiceAsync loginService = GWT.create(LoginService.class);

	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {

		// Add the nameField and sendButton to the RootPanel
		// Use RootPanel.get() to get the entire body element
		RootPanel rootPanel = RootPanel.get("loginContainer");
		rootPanel.setStyleName("tituloLogin");
		
		VerticalPanel verticalPanel = new VerticalPanel();
		rootPanel.add(verticalPanel, 185, 158);
		verticalPanel.setSize("290px", "188px");
		verticalPanel.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
		verticalPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		
		Label lblInicioDeSesin = new Label("Inicio de sesi\u00F3n");
		lblInicioDeSesin.setStyleName("TituloLogin");
		verticalPanel.add(lblInicioDeSesin);
		
		final TextBox textBoxUsername = new TextBox();
		textBoxUsername.addFocusHandler(new FocusHandler() {
			public void onFocus(FocusEvent event) {
				if(textBoxUsername.getText()=="Username")
					textBoxUsername.setText("");
			}
		});
		textBoxUsername.setText("Username");
		textBoxUsername.setFocus(false);
		textBoxUsername.setDirection(Direction.RTL);
		textBoxUsername.setAlignment(TextAlignment.CENTER);
		verticalPanel.add(textBoxUsername);
		textBoxUsername.setSize("185px", "22px");
		
		final TextBox textBoxPassword = new TextBox();
		textBoxPassword.setText("Password");
		textBoxPassword.setFocus(true);
		textBoxPassword.setAlignment(TextAlignment.CENTER);
		verticalPanel.add(textBoxPassword);
		textBoxPassword.setSize("182px", "22px");
		
		Button buttonLogin = new Button("Send");
		buttonLogin.setText("Login");
		verticalPanel.add(buttonLogin);
		buttonLogin.setWidth("65px");
		
		//Creamos el dialogo emergente
		final DialogBox dialogBox = new DialogBox();
		dialogBox.setAnimationEnabled(true);
		VerticalPanel dialogVPanel = new VerticalPanel();
		Button closeButton = new Button("Cerrar");
		dialogVPanel.add(closeButton);
		final Label loginInfo = new Label();
		dialogVPanel.add(loginInfo);
		dialogBox.setWidget(dialogVPanel);
		closeButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				dialogBox.hide();
			}
		});
		
		
		
		// Create a handler for the sendButton and nameField
		class MyHandler implements ClickHandler, KeyUpHandler {
			/**
			 * Fired when the user clicks on the sendButton.
			 */
			public void onClick(ClickEvent event) {
				sendNameToServer();
			}

			/**
			 * Fired when the user types in the nameField.
			 */
			public void onKeyUp(KeyUpEvent event) {
				if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
					sendNameToServer();
				}
			}

			/**
			 * Send the name from the nameField to the server and wait for a response.
			 */
			private void sendNameToServer() {
				
				loginService.login(textBoxUsername.getText(), textBoxPassword.getText(), new AsyncCallback<Integer>() {

					@Override
					public void onFailure(Throwable caught) {
						dialogBox.setText("No se ha podido conectar con el servidor");
						System.out.println("Ventana desplegada");
						dialogBox.center();
					}

					@Override
					public void onSuccess(Integer result) {
						switch(result)
						{
							case 1:
								dialogBox.setText("Login correcto");
								loginInfo.setText("Se ha iniciado correctamente la sesión");					
								break;
							case 0:
								dialogBox.setText("Login incorrecto");
								loginInfo.setText("El usuario o la contraseña no coincide");
								break;
							default:
								dialogBox.setText("No se ha podido conectar con el servidor");
								loginInfo.setText("Ha surgido algun problema con el servidor, contacte con el administrador.");
						}
					}
					
				});
			} 
					
			}

		// Add a handler to send the name to the server
		MyHandler handler = new MyHandler();
		buttonLogin.addClickHandler(handler);
		textBoxUsername.addKeyUpHandler(handler);
		textBoxPassword.addKeyUpHandler(handler);
	}
}

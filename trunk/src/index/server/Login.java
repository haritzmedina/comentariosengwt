package index.server;

import index.client.LoginService;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class Login extends RemoteServiceServlet implements
		LoginService {

	public Integer login(String user, String pass) throws IllegalArgumentException {
		user = escapeHtml(user);
		pass = escapeHtml(pass);
		//Se verifica si el usuario existe
		if(AccesoBD.crearConexion())
		{
			Long consulta = (Long) AccesoBD.selectEscalar("count(*)", "Usuarios", "(Username='"+user+"' || Email='"+user+"') && Password='"+pass+"'");
			if(consulta!= null && consulta <= 0)
				return 0;
			else
				return 1;
		}
		else
			return -1;
	}

	/**
	 * Escape an html string. Escaping data received from the client helps to
	 * prevent cross-site script vulnerabilities.
	 * 
	 * @param html the html string to escape
	 * @return the escaped string
	 */
	private String escapeHtml(String html) {
		if (html == null) {
			return null;
		}
		return html.replaceAll("&", "&amp;").replaceAll("<", "&lt;")
				.replaceAll(">", "&gt;");
	}
}

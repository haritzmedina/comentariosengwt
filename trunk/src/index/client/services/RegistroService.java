package index.client.services;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("registroService")
public interface RegistroService extends RemoteService {
	Integer registrar(String username, String email, String password);
}

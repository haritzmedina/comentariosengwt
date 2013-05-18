package index.client.services;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface RegistroServiceAsync {

	void registrar(String username, String email, String password, AsyncCallback<Integer> callback);
	
}

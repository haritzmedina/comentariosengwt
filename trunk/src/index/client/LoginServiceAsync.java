package index.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface LoginServiceAsync {
	void login(String name, String pass, AsyncCallback<Integer> callback)
			throws IllegalArgumentException;
}

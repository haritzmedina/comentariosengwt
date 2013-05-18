package index.client.services;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("loginService")
public interface LoginService extends RemoteService {
	Integer login(String name, String pass) throws IllegalArgumentException;
}

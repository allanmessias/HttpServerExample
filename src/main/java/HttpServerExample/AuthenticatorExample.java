package HttpServerExample;
import com.sun.net.httpserver.BasicAuthenticator; 



public class AuthenticatorExample extends BasicAuthenticator {

	public AuthenticatorExample(String realm) {
		super(realm);
	}

	@Override
	public boolean checkCredentials(String username, String password) {
		return username.equalsIgnoreCase(username) && password.equalsIgnoreCase(password);
	}

	
}

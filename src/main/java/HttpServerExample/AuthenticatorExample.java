package HttpServerExample;
import com.sun.net.httpserver.Authenticator;

import com.sun.net.httpserver.BasicAuthenticator; 
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpPrincipal; 

public class AuthenticatorExample extends BasicAuthenticator {

	public AuthenticatorExample(String realm) {
		super(realm);
	}

	@Override 
	public Authenticator.Result authenticate(HttpExchange exchange) {
		Authenticator.Result result = super.authenticate(exchange);
		if(result instanceof Authenticator.Success) {
			HttpPrincipal principal = ((Authenticator.Success)result).getPrincipal();
	        String requestMethod = exchange.getRequestMethod();
		}
		return result; 
	}
	
	@Override
	public boolean checkCredentials(String username, String password) {
		return username.equalsIgnoreCase("allan") && password.equalsIgnoreCase("1234");
	}

	
}

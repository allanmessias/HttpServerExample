package HttpServerExample.Handlers;

import java.io.IOException;
import java.io.OutputStream;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class HttpHandlerExample implements HttpHandler {

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
    	String response = "{\"message\":\"ok\"}"; 
    	
        httpExchange.sendResponseHeaders(200, response.length());
        OutputStream outputStream = httpExchange.getResponseBody();
        outputStream.write(response.getBytes("UTF-8"));
        httpExchange.close();
    }
    	
}

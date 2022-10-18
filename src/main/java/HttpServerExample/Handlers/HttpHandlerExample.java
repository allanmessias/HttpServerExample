package HttpServerExample.Handlers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;

public class HttpHandlerExample implements HttpHandler {

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        String response = httpExchange.getResponseBody().toString();
        httpExchange.sendResponseHeaders(200, response.length());
        OutputStream outputStream = httpExchange.getResponseBody();
        outputStream.write(response.getBytes());
        httpExchange.close();
    }
}

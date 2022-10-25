package HttpServerExample.Handlers;

import java.io.*;
import java.net.URI;
import java.util.stream.Collectors;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class HttpHandlerExample implements HttpHandler {

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
    	String response = "{\"message\":\"ok\"}";

        httpExchange.sendResponseHeaders(200, response.length());
        OutputStream outputStream = httpExchange.getResponseBody();
        InputStream inputStream = httpExchange.getRequestBody();
        System.out.println(this.convertsToString(inputStream));
        outputStream.write(response.getBytes("UTF-8"));
        httpExchange.close();
    }

    private String convertsToString(InputStream inputStream) {
        return new BufferedReader(new InputStreamReader(inputStream))
                .lines().collect(Collectors.joining("\n"));
    }
}

package HttpServerExample.Handlers;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class HttpHandlerExample implements HttpHandler {

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
//    	String response = "{\"message\":\"ok\"}"; 
//    	
//        httpExchange.sendResponseHeaders(200, response.length());
//        OutputStream outputStream = httpExchange.getResponseBody();
//        outputStream.write(response.getBytes("UTF-8"));
//        httpExchange.close();
    	
    	final InputStream inputStream;
    	final OutputStream outputStream;
    	StringBuilder buffer;
    	int bytes;
    	final String request, response;
    	
    	buffer = new StringBuilder();
    	inputStream = httpExchange.getRequestBody();
    	
    	// Este método read do inputStream lê o byte 0 até 255
    	// Quando chega em 255, ele vai para -1
    	while((bytes = inputStream.read()) != -1) {
    		// Aqui, damos um append no bufferString
    		// Que sofre cast para char, afim de lermos o seu length 
    		// Parsando para string.
    		buffer.append((char) bytes);
    	}
    	
    }
}

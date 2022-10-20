package HttpServerExample;

import HttpServerExample.Handlers.HttpHandlerExample;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;

public class ServerExample {
    private HttpServer httpServer;
    private static final int port = 8000;
    public void createServer() {
        try {
            this.httpServer = HttpServer.create(new InetSocketAddress(ServerExample.port), 0);
        } catch (IOException e) {
            e.getMessage();
        }
    }

    public HttpServer getHttpServer() {
        return this.httpServer;
    }

    private void handleRequests(HttpHandler handler) {
        this.httpServer.createContext("/test", handler).setAuthenticator(new AuthenticatorExample("test"));
    }

    public void startServer() {
        this.handleRequests(new HttpHandlerExample());
        this.httpServer.start();
    }
}

package HttpServerExample;

import HttpServerExample.Handlers.HttpHandlerExample;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

public class ServerExample {
    private HttpServer httpServer;
    private static final int port = 8000;

    private ServerExample createServer() {
        try {
            this.httpServer = HttpServer.create(new InetSocketAddress(ServerExample.port), 0);
        } catch (IOException e) {
            e.getMessage();
        }
        return this;
    }

    private ServerExample handleRequests(HttpHandler handler) {
        this.httpServer.createContext("/test", handler);
        this.httpServer.createContext("/test/login", handler)
                .setAuthenticator(new AuthenticatorExample("login"));
        return this;
    }

    private ServerExample setThreadsExecutors (int threadsExecutors) {
        if (threadsExecutors < 1) throw new IllegalArgumentException("Need to inform an number higher than 0");
        this.httpServer.setExecutor(Executors.newFixedThreadPool(threadsExecutors));
        return this;
    }

    public void startServer() {
        this.createServer()
                .setThreadsExecutors(10)
                .handleRequests(new HttpHandlerExample())
                .httpServer.start();
   }

    @Override
    public String toString() {
        return String.valueOf(ServerExample.port);
    }
}

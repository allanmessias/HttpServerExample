package HttpServerExample;

import HttpServerExample.Handlers.HttpHandlerExample;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

class ServerTest {
    @BeforeEach
    public void createServerWithIpAndStart() throws IOException {
        ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) Executors.newFixedThreadPool(10);
        ServerExample serverExample = new ServerExample();
        serverExample.createServer();
        serverExample.getHttpServer().setExecutor(threadPoolExecutor);
        serverExample.startServer();
        System.out.println("Server started on port " + serverExample.getHttpServer().getAddress());
    }

    @Test
    void itShouldSendRequestAndReturnHttpCode200() throws URISyntaxException, InterruptedException, IOException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI("http://localhost:8000/test"))
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        Assertions.assertEquals(response.statusCode(), 200);
    }
}
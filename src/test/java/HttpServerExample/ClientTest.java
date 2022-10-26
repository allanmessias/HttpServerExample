package HttpServerExample;

import HttpClientExample.ClientExample;
import HttpServerExample.Handlers.HttpHandlerExample;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

class ClientTest {
    private final String url = "http://localhost:8000/test";
    @BeforeAll
    public static void createServerWithIpAndStart() {
        ServerExample serverExample = new ServerExample();
        serverExample.startServer();
        System.out.println("Server started on port " + serverExample.toString());
    }

    @Test
    void itShouldSendAGETRequestWithAuthenticatedUser() throws ExecutionException, InterruptedException {
        ClientExample clientExample = new ClientExample();

        Assertions.assertEquals(clientExample.get().statusCode(), 200);
        Assertions.assertEquals(clientExample.get().body(), "{\"message\":\"ok\"}");
    }

    private HttpRequest.BodyPublisher buildFormDataFromMap(Map<String, String> data) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Map.Entry<String, String> entry : data.entrySet()) {
            if (stringBuilder.length() > 0) {
                stringBuilder.append("&");
            }
            stringBuilder.append(URLEncoder.encode(entry.getKey().toString(), StandardCharsets.UTF_8));
            stringBuilder.append("=");
            stringBuilder.append(URLEncoder.encode(entry.getValue().toString(), StandardCharsets.UTF_8));
        }
        return HttpRequest.BodyPublishers.ofString(stringBuilder.toString());
    }

    @Test
    void itShouldSendAPOSTRequestWithAuthenticationData() throws ExecutionException, InterruptedException {
        HttpClient httpClient = HttpClient.newHttpClient();

        // Form data
        Map<String, String> data = new HashMap<>();
        data.put("username", "allan");
        data.put("password", "1234");

        HttpRequest httpRequest = HttpRequest.newBuilder()
                .POST(buildFormDataFromMap(data))
                .uri(URI.create("http://localhost:8000/test/login"))
                .setHeader("Authorization", ClientExample.getBasicAuthentication(data.get("username"), data.get("password")))
                .header("Content-Type", "application/x-www-form-urlencoded")
                .build();

        CompletableFuture<HttpResponse<String>> response =
                httpClient.sendAsync(httpRequest, HttpResponse.BodyHandlers.ofString())
                        .whenComplete((s, t) -> s.headers());

        Assertions.assertEquals(response.get().statusCode(), 200);
        Assertions.assertEquals(response.get().body(), "{\"message\":\"ok\"}");

    }

    @Test
    void itShouldSendAPOSTRequestWithAuthenticatedUser() throws ExecutionException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .setHeader("Authorization", ClientExample.getBasicAuthentication("allan", "1234"))
                .uri(URI.create(this.url + "/user"))
                .POST(HttpRequest.BodyPublishers.noBody())
                .build();
        CompletableFuture<HttpResponse<String>> response =
                client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                        .whenComplete((s, t) -> s.headers());

        Assertions.assertEquals(response.get().statusCode(), 200);
        Assertions.assertEquals(response.get().body(), "{\"message\":\"ok\"}");

    }

    @Test
    void itShouldThrow401StatusCodeToUnauthicatedUser() throws InterruptedException, ExecutionException, IOException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .setHeader("Authorization", ClientExample.getBasicAuthentication("flag", "1234"))
                .uri(URI.create("http://localhost:8000/test/user"))
                .build();
        HttpResponse<String> response =
                client.send(request, HttpResponse.BodyHandlers.ofString());

        Assertions.assertEquals(response.statusCode(), 401);
    }
}
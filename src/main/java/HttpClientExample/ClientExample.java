package HttpClientExample;

import org.jetbrains.annotations.NotNull;

import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;


public class ClientExample implements HttpRequestsExample {
    private final HttpClient httpClient;

    public ClientExample() {
        this.httpClient = HttpClient.newHttpClient();
    }

    public HttpResponse<String> get() throws ExecutionException, InterruptedException {
            HttpRequest request  = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8000/test"))
                .setHeader("Authorization", ClientExample.getBasicAuthentication("allan", "1234"))
                .build();
        return this.httpClient.sendAsync(request, HttpResponse.BodyHandlers.ofString()).get();

    }

    @Override
    public HttpResponse<String> post() throws ExecutionException, InterruptedException {
        return null;
    }


    @Override
    public HttpResponse<String> put() throws ExecutionException, InterruptedException {
        return null;
    }

    @Override
    public HttpResponse<String> delete() throws ExecutionException, InterruptedException {
        return null;
    }

    public static @NotNull String getBasicAuthentication(String username, String password) {
        String valueToEncode = username + ":" + password;
        return "Basic " + Base64.getEncoder().encodeToString(valueToEncode.getBytes());
    }

    private static String buildFormDataFromMap(Map<String, String> data) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Map.Entry<String, String> entry : data.entrySet()) {
            if(stringBuilder.length() > 0) {
                stringBuilder.append("&");
            }
            stringBuilder.append(URLEncoder.encode(entry.getKey().toString(), StandardCharsets.UTF_8));
            stringBuilder.append("=");
            stringBuilder.append(URLEncoder.encode(entry.getValue().toString(), StandardCharsets.UTF_8));
        }
        return stringBuilder.toString();
    }
}

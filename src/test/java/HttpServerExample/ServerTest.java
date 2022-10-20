package HttpServerExample;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Base64;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

class ServerTest {
	private String url = "http://localhost:8000/test";
	
	
    @BeforeAll
    public static void createServerWithIpAndStart() throws IOException {
        ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) Executors.newFixedThreadPool(10);
        ServerExample serverExample = new ServerExample();
        serverExample.createServer();
        serverExample.getHttpServer().setExecutor(threadPoolExecutor);
        serverExample.startServer();
        System.out.println("Server started on port " + serverExample.getHttpServer().getAddress());
    }

    private static final String getBasicAuthentication(String username, String password) {
    	String valueToEncode = username + ":" + password;
    	return "Basic " + Base64.getEncoder().encodeToString(valueToEncode.getBytes()); 
    }
    
    @Test
    void itShouldSendAGETRequestWithAuthenticatedUser() throws InterruptedException, ExecutionException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
        		.setHeader("Authorization", getBasicAuthentication("allan", "1234"))
                .uri(URI.create(this.url))
                .build();
        CompletableFuture<HttpResponse<String>> response = 
        		client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
        		.whenComplete((s, t) -> s.body()); 

        Assertions.assertEquals(response.get().statusCode(), 200);
    }
    
    @Test
    void itShouldSendAPOSTRequestWithAuthenticatedUser() throws ExecutionException, InterruptedException {
    	HttpClient client = HttpClient.newHttpClient();
    	HttpRequest request = HttpRequest.newBuilder()
    			.setHeader("Authorization", getBasicAuthentication("allan", "1234"))
    			.uri(URI.create(this.url))
    			.POST(HttpRequest.BodyPublishers.noBody())
    			.build();
    	CompletableFuture<HttpResponse<String>> response = 
    			client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
    			.whenComplete((s, t) -> s.body());
    	
    	Assertions.assertEquals(response.get().statusCode(), 200);
    	Assertions.assertEquals(response.get().body(), "{\"message\":\"ok\"}");
    	
    }
}
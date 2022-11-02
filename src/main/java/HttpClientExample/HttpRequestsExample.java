package HttpClientExample;

import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.ExecutionException;

public interface HttpRequestsExample {
    public HttpResponse<String> get() throws ExecutionException, InterruptedException;
    public HttpResponse<String> post() throws ExecutionException, InterruptedException;
    public HttpResponse<String> put() throws ExecutionException, InterruptedException;
    public HttpResponse<String> delete() throws ExecutionException, InterruptedException;
}

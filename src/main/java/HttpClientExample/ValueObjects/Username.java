package HttpClientExample.ValueObjects;

public class Username {
    private String userName;

    private Username(String userName) {
        this.userName = userName;
    }

    public static Username create(String userName) {
        return new Username(userName);
    }
}

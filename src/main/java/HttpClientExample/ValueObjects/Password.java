package HttpClientExample.ValueObjects;

public class Password {
    private String password;
    private Password(String password) {
        this.password = password;
    }

    public static Password create(String password) {
        return new Password(password);
    }
}

package constants;

public enum UrlAndPathConstants {

    BASE_URL("https://reqres.in"),
    LOGIN_PATH("/api/login"),
    COLOR_PATH("/api/unknown"),
    USER_PATH("/api/users");

    private final String uri;

    UrlAndPathConstants(String uri) {
        this.uri = uri;
    }

    public String get() {
        return uri;
    }
}

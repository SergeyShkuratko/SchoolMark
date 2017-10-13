package classes;

public class UserCredentials {
    private final String login;
    private final String passwordHash;

    public UserCredentials(String login, String passwordHash) {
        this.login = login;
        this.passwordHash = passwordHash;
    }

    public String getLogin() {
        return login;
    }

    public String getPasswordHash() {
        return passwordHash;
    }
}

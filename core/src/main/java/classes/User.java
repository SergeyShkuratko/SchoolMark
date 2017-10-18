package classes;

import java.time.LocalDate;
import java.util.Set;

public class User {
    private final int id;
    private final String login;
    private String password;
    private final LocalDate registrationDate;
    private LocalDate lastLoginDate;
    private Role role;

    public User(int id, String login, LocalDate registrationDate) {
        this.id = id;
        this.login = login;
        this.registrationDate = registrationDate;
    }

    public int getUserId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public LocalDate getLastLoginDate() {
        return lastLoginDate;
    }

    public void setLastLoginDate(LocalDate lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}

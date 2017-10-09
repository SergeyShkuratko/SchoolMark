package classes;

import java.time.LocalDate;
import java.util.Set;

public class User {
    private final int id;
    private final String login;
    private String password;
    private final LocalDate date_reg;
    private LocalDate date_last_login;
    private Set<Role> userRoles;

    public User(int id, String login, int id_role, LocalDate date_reg) {
        this.id = id;
        this.login = login;
        this.date_reg = date_reg;
    }

    public int getId() {
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

    public LocalDate getDate_reg() {
        return date_reg;
    }

    public LocalDate getDate_last_login() {
        return date_last_login;
    }

    public void setDate_last_login(LocalDate date_last_login) {
        this.date_last_login = date_last_login;
    }

    public Set<Role> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(Set<Role> userRoles) {
        this.userRoles = userRoles;
    }
}

package User;

import java.io.Serializable;

public class User implements Serializable {
    private String username;
    private String password;
    private boolean isAuthorization;
    private boolean isRegistration;

    public User() {
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAuthorization() {
        return isAuthorization;
    }

    public void setAuthorization(boolean isAuthorization) {
        this.isAuthorization = isAuthorization;
    }

    public boolean isRegistration() {
        return isRegistration;
    }

    public void setRegistration(boolean registration) {
        this.isRegistration = registration;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
package ro.ase.acs.quizz.activity.Navigation;

import android.graphics.Bitmap;

import java.io.Serializable;
import java.util.Objects;

public class User implements Serializable {
    String username;
    String password;
    String email;
    int points;
    Bitmap bitmap;

    public User(String username, String password, String email, int points, Bitmap bitmap) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public User() {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(username, user.username) &&
                Objects.equals(password, user.password);
    }

    @Override
    public int hashCode() {

        return Objects.hash(username, password);
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

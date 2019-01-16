package ro.ase.acs.quizz.activity.Navigation;

import android.graphics.Bitmap;
import android.support.annotation.NonNull;

import java.io.Serializable;
import java.util.Objects;

public class User implements Serializable,Comparable<User> {
    String username;
    String password;
    String email;
    int points;
    Bitmap bitmap;

    public User(String username, String password, String email, int points, Bitmap bitmap) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.points = points;
        this.bitmap= bitmap;
    }

    public User() {

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

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    @Override
    public int compareTo(@NonNull User o) {
        if(this.getPoints()>o.getPoints()){
            return 1;
        }
        return -1;
    }

}

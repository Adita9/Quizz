package ro.ase.acs.quizz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.List;
import java.util.Random;

import ro.ase.acs.quizz.activity.Home;
import ro.ase.acs.quizz.activity.Navigation.User;
import ro.ase.acs.quizz.activity.SignUp;

public class Login extends AppCompatActivity {

    Button loginButton;
    Button singUpButton;
    TextView tvName;
    TextView tvPass;
    public static List<User> users;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loginButton = findViewById(R.id.btnLogin);
        singUpButton = findViewById(R.id.btn_singUpLogin);
        tvName = findViewById(R.id.tbLoginName);
        tvPass = findViewById(R.id.tbLoginPassword);

        loginButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String name = (String)tvName.getText();
                String pass = (String)tvPass.getText();
                User checkUser = new User(name, pass, "");
                if(users.contains(checkUser)) {
                    Intent i = new Intent(getApplicationContext(), Home.class);
                    i.putExtra("user", checkUser);
                    Random r = new Random();
                    i.putExtra("points", r.nextInt(100) );

                    startActivity(i);
                }else{
                    Toast.makeText(Login.this, "wrong credentials", Toast.LENGTH_SHORT).show();
                }
            }

        });

        singUpButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),SignUp.class);
                startActivity(i);
            }

        });






    }
}

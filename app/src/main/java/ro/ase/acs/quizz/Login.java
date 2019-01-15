package ro.ase.acs.quizz;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import ro.ase.acs.quizz.activity.Home;
import ro.ase.acs.quizz.activity.SignUp;

public class Login extends AppCompatActivity {

    Button loginButton;
    Button singUpButton;
    TextView tvName;
    TextView tvPass;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loginButton = findViewById(R.id.btnLogin);
        singUpButton = findViewById(R.id.btnLoginSignUp);
        tvName = findViewById(R.id.tbLoginName);
        tvPass = findViewById(R.id.tbLoginPassword);
        firebaseAuth = FirebaseAuth.getInstance();

        loginButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                boolean correctC = true;
                if (String.valueOf(tvName.getText()).length() < 8) {
                    Toast.makeText(getApplicationContext(), "Name empty/incorrect", Toast.LENGTH_LONG).show();
                    correctC = false;
                }

                if (String.valueOf(tvPass.getText()).length() < 8) {
                    Toast.makeText(getApplicationContext(), "Password empty/incorrect", Toast.LENGTH_LONG).show();
                    correctC = false;
                }
                if (correctC) {
                    firebaseAuth.signInWithEmailAndPassword(String.valueOf(tvName.getText()), String.valueOf(tvPass.getText()))
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        Intent i = new Intent(getApplicationContext(), Home.class);
                                        startActivity(i);
                                    } else
                                        Toast.makeText(getApplicationContext(), "Invalid credentials", Toast.LENGTH_LONG);
                                }
                            })
                    ;


                }

            }

        });

        singUpButton.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SignUp.class);
                startActivity(intent);
            }
        });

    }
}

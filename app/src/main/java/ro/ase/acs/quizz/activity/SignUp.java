package ro.ase.acs.quizz.activity;

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
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import ro.ase.acs.quizz.R;
import ro.ase.acs.quizz.activity.Navigation.User;

public class SignUp extends AppCompatActivity {
    Button btnSingUp;
    TextView tvName;
    TextView tvPass;
    TextView tvConfirmPass;
    TextView tvEmail;
    TextView tvExistent;
    FirebaseAuth firebaseAuth;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        btnSingUp = findViewById(R.id.btn_signUp);
        tvName = findViewById(R.id.et_signUpName);
        tvPass = findViewById(R.id.et_signUpPassword);
        tvConfirmPass = findViewById(R.id.et_signUpConfirmPassword);
        tvEmail = findViewById(R.id.et_signUpEmail);
        tvExistent = findViewById(R.id.textView3);
        firebaseAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference();

        btnSingUp.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                final String name = String.valueOf(tvName.getText());
                final String pass = String.valueOf(tvPass.getText());
                String confirmPass = String.valueOf(tvConfirmPass.getText());
                final String email = String.valueOf(tvEmail.getText());
                boolean correctC = true;
                if (String.valueOf(tvName.getText()).length() < 8) {
                    Toast.makeText(getApplicationContext(), "Name empty/incorrect", Toast.LENGTH_LONG).show();
                    correctC = false;
                }
                if (String.valueOf(tvPass.getText()).length() < 8) {
                    Toast.makeText(getApplicationContext(), "Name empty/incorrect", Toast.LENGTH_LONG).show();
                    correctC = false;
                }
                if (String.valueOf(tvPass.getText()).compareTo(confirmPass)==1) {
                    Toast.makeText(getApplicationContext(), "Incorrect confirmPass", Toast.LENGTH_LONG).show();
                    correctC = false;
                }
                if (correctC) {
                    firebaseAuth.createUserWithEmailAndPassword(name, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                User user = new User(email, pass, name, 0, null);
                                FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
                                databaseReference.child(firebaseUser.getUid()).setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if(task.isSuccessful()) {
                                            Intent intent = new Intent(getApplicationContext(), Home.class);
                                            Toast.makeText(getApplicationContext(), "User created", Toast.LENGTH_SHORT).show();
                                            startActivity(intent);
                                        }
                                    }
                                });


                            }
                        }
                    });
                }

            }

        });

    }
}

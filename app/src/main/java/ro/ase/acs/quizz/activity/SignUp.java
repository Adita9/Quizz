package ro.ase.acs.quizz.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import ro.ase.acs.quizz.Login;
import ro.ase.acs.quizz.R;
import ro.ase.acs.quizz.activity.Navigation.User;

public class SignUp extends AppCompatActivity {
    Button btnSingUp;
    TextView tvName;
    TextView tvPass;
    TextView tvConfirmPass;
    TextView tvEmail;
    TextView tvExistent;
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

        btnSingUp.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String name = (String) tvName.getText();
                String pass = (String) tvPass.getText();
                String confirmPass = (String) tvConfirmPass.getText();
                String email = (String) tvEmail.getText();
                if(!pass.contentEquals(confirmPass)){
                    Toast.makeText(SignUp.this, "Passwords do not match", Toast.LENGTH_SHORT).show();
                }else{
                    if(name.isEmpty()||pass.isEmpty()||confirmPass.isEmpty()||email.isEmpty()){
                        Toast.makeText(SignUp.this, "Cannot be empty", Toast.LENGTH_SHORT).show();
                    }else {
                        User newUser = new User(name, pass, email);
                        if(Login.users.contains(newUser)){
                            tvExistent.setVisibility(View.VISIBLE);
                        }else{
                            Login.users.add(newUser);

                            Intent i = new Intent(getApplicationContext(), Login.class);
                            startActivity(i);
                        }



                    }
                }

            }

        });

    }
}

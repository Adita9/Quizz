package ro.ase.acs.quizz.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import ro.ase.acs.quizz.R;
import ro.ase.acs.quizz.activity.Navigation.User;

public class Home extends AppCompatActivity {

    ImageView homeProfile;
    TextView tvGreeting;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        tvGreeting = findViewById(R.id.tv_homeName);

        Intent i = getIntent();
        User userName = (User) i.getSerializableExtra("user");
        int points;
        points = i.getIntExtra("points", 0);

        tvGreeting.setText("Hello Mr. "+userName+"! Your score is: "+points);

        homeProfile = (ImageView) findViewById(R.id.im_homeProfile);
        homeProfile.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),Profile.class);
                startActivity(i);
            }

        });



    }
}

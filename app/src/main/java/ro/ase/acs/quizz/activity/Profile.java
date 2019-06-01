package ro.ase.acs.quizz.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import ro.ase.acs.quizz.DataBaseHelper;
import ro.ase.acs.quizz.R;

public class Profile extends AppCompatActivity {

    Button btnLeaderboard;
    DataBaseHelper dataBaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        btnLeaderboard = findViewById(R.id.btn_leaderBoard);

        btnLeaderboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), LeaderBoard.class);
                startActivity(intent);
            }
        });
    }
}

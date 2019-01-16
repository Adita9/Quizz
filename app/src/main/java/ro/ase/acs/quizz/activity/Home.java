package ro.ase.acs.quizz.activity;


import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.IOException;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

import ro.ase.acs.quizz.Model.Question;
import ro.ase.acs.quizz.R;
import ro.ase.acs.quizz.activity.Navigation.User;

public class Home extends AppCompatActivity {


    ImageView homeProfile;
    TextView tvGreeting;
    ImageView imQuizz;
    Question question = null;
    DatabaseReference databaseReference;
    FirebaseAuth firebaseAuth;
    ImageView leaderboard;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        tvGreeting = findViewById(R.id.tv_homeName);

        databaseReference = FirebaseDatabase.getInstance().getReference();
        firebaseAuth = FirebaseAuth.getInstance();
        final String key = firebaseAuth.getCurrentUser().getUid();
        user = new User();
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                HashMap userMap = (HashMap) dataSnapshot.child(key).getValue();
                assert userMap != null;
                user.setEmail((String) userMap.get("email"));
                user.setPassword((String) userMap.get("password"));
                user.setPoints(Integer.valueOf(String.valueOf((Long) userMap.get("points"))));
                user.setUsername((String) userMap.get("username"));
                tvGreeting.setText(new StringBuilder().append("Hello Mr. ").append(user.getUsername()).append("! Your score is: ").append(user.getPoints()).toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        homeProfile = (ImageView) findViewById(R.id.im_homeProfile);
        homeProfile.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), Profile.class);
                startActivity(i);
            }

        });
        new AsyncTaskRunner().execute();
        imQuizz = findViewById(R.id.im_homeQuizz);
        imQuizz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Quizz.class);
                intent.putExtra("quizz", (Serializable) question);
                intent.putExtra("user",user);
                startActivity(intent);
            }
        });

        leaderboard= findViewById(R.id.im_homeLeaderBoard);
        leaderboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),LeaderBoard.class);
                intent.putExtra("user",user);
                startActivity(intent);
            }
        });
    }

    private class AsyncTaskRunner extends AsyncTask<Question, String, Question> {

        @Override
        protected Question doInBackground(Question... questions) {
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                question = objectMapper.readValue(new URL("http://www.json-generator.com/api/json/get/bTRwMOYPPC?indent=2"), Question.class);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (JsonParseException e) {
                e.printStackTrace();
            } catch (JsonMappingException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return question;
        }

    }
}


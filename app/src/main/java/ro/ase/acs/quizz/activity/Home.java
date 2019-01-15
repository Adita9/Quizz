package ro.ase.acs.quizz.activity;


import android.content.Intent;
import android.os.AsyncTask;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URL;

import ro.ase.acs.quizz.Model.Question;
import ro.ase.acs.quizz.R;
import ro.ase.acs.quizz.activity.Navigation.User;

public class Home extends AppCompatActivity {


    ImageView homeProfile;
    TextView tvGreeting;
    ImageView imQuizz;
    Question question = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        tvGreeting = findViewById(R.id.tv_homeName);

        Intent i = getIntent();
        User userName = (User) i.getSerializableExtra("user");
        int points;
        points = i.getIntExtra("points", 0);

        tvGreeting.setText("Hello Mr. " + userName + "! Your score is: " + points);

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
                startActivity(intent);

            }
        });


        tvGreeting.setText("Hello Mr. " + userName + "! Your score is: " + getIntent().getIntExtra("quickResult",0));

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


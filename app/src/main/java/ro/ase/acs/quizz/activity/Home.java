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

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import ro.ase.acs.quizz.Model.Question;
import ro.ase.acs.quizz.R;

public class Home extends AppCompatActivity {
ImageView imQuizz;
Question question = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
            imQuizz = findViewById(R.id.im_homeQuizz);
        imQuizz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),Quizz.class);
                new AsyncTaskRunner().execute();
                intent.putExtra("quizz", (Parcelable) question);
                startActivity(intent);
            }
        });
    }

    private class AsyncTaskRunner extends AsyncTask<Question, String, Question>{


        @Override
        protected Question doInBackground(Question... questions) {
        ObjectMapper objectMapper = new ObjectMapper();
        try{if(questions!=null)
            questions[0]= objectMapper.readValue(new URL("http://www.json-generator.com/api/json/get/bVnrqiCicy?indent=2"),Question.class);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(questions!=null)
        question=questions[0];
        return question;
        }
    }



}

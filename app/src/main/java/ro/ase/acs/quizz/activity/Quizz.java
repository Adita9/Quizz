package ro.ase.acs.quizz.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

import ro.ase.acs.quizz.Adapter.ListViewAdapter;
import ro.ase.acs.quizz.Model.Answer;
import ro.ase.acs.quizz.Model.Question;
import ro.ase.acs.quizz.R;

public class Quizz extends AppCompatActivity  {

    Question question;
    ListView listView;
    ListViewAdapter listViewAdapter=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quizz);
        question=getIntent().getParcelableExtra("quizz");

        if(question !=null &&question.getAnswers()!=null){
            listViewAdapter = new ListViewAdapter(getApplicationContext(), R.layout.list_view_item, question.getAnswers());
        } else {
            listViewAdapter = new ListViewAdapter(getApplicationContext(), R.layout.list_view_item, new ArrayList<Answer>());
        }
        listView= findViewById(R.id.listview);
        listView.setAdapter(listViewAdapter);
    }


}

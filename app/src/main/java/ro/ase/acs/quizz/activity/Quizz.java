package ro.ase.acs.quizz.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ro.ase.acs.quizz.Adapter.ListViewAdapter;
import ro.ase.acs.quizz.Model.Answer;
import ro.ase.acs.quizz.Model.Question;
import ro.ase.acs.quizz.R;
import ro.ase.acs.quizz.activity.Navigation.User;

public class Quizz extends AppCompatActivity {

    Question question;
    ListView listView;
    TextView textView;
    ListViewAdapter listViewAdapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quizz);
        question = (Question) getIntent().getSerializableExtra("quizz");
        final Map<String, Boolean> result = new HashMap<>();
        if (question != null && question.getAnswers() != null) {
            List<String> possibleQ = new ArrayList<>();
            for (Answer a : question.getAnswers()) {
                possibleQ.add(a.getLine());
                result.put(a.getLine(), a.isCorrect());
            }
            listViewAdapter = new ListViewAdapter(getApplicationContext(), R.layout.list_view_item, possibleQ);
        } else {
            listViewAdapter = new ListViewAdapter(getApplicationContext(), R.layout.list_view_item, new ArrayList<String>());
        }
        listView = findViewById(R.id.listview);
        textView = findViewById(R.id.textView4);
        textView.setText(question.getTitle());
        listView.setAdapter(listViewAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String o = (String) adapterView.getItemAtPosition(i);
                if (result.containsKey(o)) {
                    Toast.makeText(getApplicationContext(), "The answer is " + result.get(o).toString(), Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), Home.class);
                    boolean b = result.get(o);
                    if (b) {
                        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
                        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
                        final String key = firebaseAuth.getCurrentUser().getUid();
                        User user = (User) getIntent().getSerializableExtra("user");
                        user.setPoints(+10);
                        databaseReference.child(key).setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {

                            }
                        });

                    }
                    startActivity(intent);
                }

            }
        });
    }


}

package ro.ase.acs.quizz.activity;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import ro.ase.acs.quizz.Adapter.ListViewAdapter;
import ro.ase.acs.quizz.R;
import ro.ase.acs.quizz.activity.Navigation.User;

public class LeaderBoard extends AppCompatActivity {
    ListView listView;
    TextView textView;
    ListViewAdapter listViewAdapter = null;
    Button button;
    DatabaseReference databaseReference;
    FirebaseAuth firebaseAuth;
    List<User> users = new ArrayList<>();
    HashMap hashMap = new HashMap();

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        databaseReference = FirebaseDatabase.getInstance().getReference();
        firebaseAuth = FirebaseAuth.getInstance();
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                HashMap<String, HashMap> hash = (HashMap) dataSnapshot.getValue();
                assert hash != null;
                for (HashMap e : hash.values()) {
                    User user = new User();
                    user.setEmail((String) e.get("email"));
                    user.setPassword((String) e.get("password"));
                    user.setPoints(Integer.valueOf(String.valueOf((Long) e.get("points"))));
                    user.setUsername((String) e.get("username"));
                    users.add(user);
                }
                Collections.sort(users);
                List<String> leaderboard = new ArrayList<>();
                for(User u : users){
                    leaderboard.add("Username" +" = "+u.getUsername()+" has "+ ":"+u.getPoints()+"points");
                }
                listViewAdapter = new ListViewAdapter(getApplicationContext(), R.layout.list_view_item, leaderboard);
                listView = findViewById(R.id.listviewLeader);
                textView = findViewById(R.id.textView5);
                listView.setAdapter(listViewAdapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leader_board);
    }
}

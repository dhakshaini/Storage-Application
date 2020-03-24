package com.example.evernote;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.DatabaseException;
import com.google.firebase.database.ValueEventListener;
import android.widget.ArrayAdapter;
import java.util.ArrayList;
public class Chat extends AppCompatActivity {
    Button btn, btn1;
    EditText note;
    //TextView show;
    ListView show;
    ListView listView;
    //FirebaseDatabase database;
   // DatabaseReference ref;
    private FirebaseAuth mAuth;
    ArrayList<String> list;
    //ArrayAdapter<String> adapter;
    Note use;
    ArrayList<String> arrayList=new ArrayList<>();
    private ArrayAdapter<String> adapter;
     FirebaseDatabase database;
     DatabaseReference ref;
    private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        btn = (Button) findViewById(R.id.button2);
        note = (EditText) findViewById(R.id.editText13);
        show = (ListView) findViewById(R.id.listView);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String feedback = note.getText().toString().trim();
                FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
                DatabaseReference register = firebaseDatabase.getReference().child("demo1").child("notes1");
                register.push().child("feedback").setValue(feedback);
                Note no = new Note(feedback);
                Toast.makeText(getApplicationContext(), "Msg send", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(getApplicationContext(), Chat.class);
                startActivity(i);
            }
        });

        mAuth = FirebaseAuth.getInstance();
        use = new Note();
        listView = (ListView) findViewById(R.id.listView);
        database = FirebaseDatabase.getInstance();
        ref = database.getReference().child("demo1").child("notes1");
        list = new ArrayList<>();
        int i=1;
        adapter = new ArrayAdapter<String>(this, R.layout.user_status, R.id.name, list);
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    use = ds.getValue(Note.class);
                    list.add(use.getFeedback());
                }
                listView.setAdapter(adapter);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }
}

package com.example.evernote;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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

import java.util.ArrayList;

public class NoteActivity extends AppCompatActivity {
    Button btn, btn1;
    EditText note;
    //TextView show;
    ListView show;
    ArrayList<String> arrayList=new ArrayList<>();
    private ArrayAdapter<String> adapter;
    private FirebaseDatabase database;
    private DatabaseReference ref;
    private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);
        btn = (Button) findViewById(R.id.button2);
        btn1 = (Button) findViewById(R.id.button15);
        note = (EditText) findViewById(R.id.editText13);
        show = (ListView) findViewById(R.id.listView);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String feedback = note.getText().toString().trim();
                FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
                DatabaseReference register = firebaseDatabase.getReference().child("demo").child("notes");
                register.push().child("feedback").setValue(feedback);
                Note no = new Note(feedback);
                Toast.makeText(getApplicationContext(), "Notes updated", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(getApplicationContext(), NoteActivity.class);
                startActivity(i);

            }
        });
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), Add.class);
                startActivity(i);
               /* ref = FirebaseDatabase.getInstance().getReference().child("demo").child("notes");
                adapter = new ArrayAdapter<Note>(getApplicationContext(),R.layout.notelist,R.id.textView3,arrayList);
                ref.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for(DataSnapshot ds:dataSnapshot.getChildren()){
                            no=ds.getValue(Note.class);
                            arrayList.add(no);

                        }
                        show.setAdapter(adapter);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
            }
        });
                ref.addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                        String string = dataSnapshot.getValue(String.class);
                        arrayList.add(string);
                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                    }

                    @Override
                    public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
                        String string = dataSnapshot.getValue(String.class);
                        arrayList.remove(string);
                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                    }
                });
                show.setAdapter(adapter);*/
            }
        });
    }
}

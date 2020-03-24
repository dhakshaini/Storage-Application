package com.example.evernote;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import com.google.firebase.database.*;
import android.os.Bundle;
import java.util.ArrayList;
import com.google.firebase.database.DataSnapshot;

import com.google.firebase.database.DatabaseError;

import com.google.firebase.database.DatabaseReference;

import com.google.firebase.database.FirebaseDatabase;

import com.google.firebase.auth.AuthResult;

import com.google.firebase.auth.FirebaseAuth;

import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;


public class Add extends AppCompatActivity {

    ListView listView;

    FirebaseDatabase database;

    DatabaseReference ref;


    private FirebaseAuth mAuth;

    ArrayList<String> list;

    ArrayAdapter<String> adapter;

    Note use;


    @Override

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_add);


        mAuth = FirebaseAuth.getInstance();


        use = new Note();


        listView = (ListView) findViewById(R.id.listView);

        database = FirebaseDatabase.getInstance();

        ref = database.getReference().child("demo").child("notes");

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
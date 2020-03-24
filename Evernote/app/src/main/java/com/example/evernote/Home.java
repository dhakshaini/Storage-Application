package com.example.evernote;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Home extends AppCompatActivity {
    Button btn,btn2,btn3,btn4,btn5,btn6,btn7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        btn=(Button)findViewById(R.id.button);
        btn2=(Button)findViewById(R.id.button13);
        btn3=(Button)findViewById(R.id.button3);
        btn4=(Button)findViewById(R.id.button12);
        btn5=(Button)findViewById(R.id.button8);
        btn6=(Button)findViewById(R.id.button9);
        btn7=(Button)findViewById(R.id.button4);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(Home.this,NoteActivity.class);
                startActivity(i);

            }

        });
        btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(Home.this,Chat.class);
                startActivity(i);

            }

        });


        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent k= new Intent(Home.this, Links.class);
                startActivity(k);
            }
        });

        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent m= new Intent(Home.this, demo.class);
                startActivity(m);
            }
        });
        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent n= new Intent(Home.this, First.class);
                startActivity(n);
            }
        });
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent y= new Intent(Home.this, Videos.class);
                startActivity(y);
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent c= new Intent(Home.this, Audios.class);
                startActivity(c);
            }
        });
            }
}

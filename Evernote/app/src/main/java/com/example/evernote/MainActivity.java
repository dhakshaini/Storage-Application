package com.example.evernote;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
public class MainActivity extends AppCompatActivity {
    Button btn;
    TextView tv1,tv2;
    EditText mail,pass;
    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mail=(EditText)findViewById(R.id.editTexts);
        pass=(EditText)findViewById(R.id.editText2);
        btn=(Button)findViewById(R.id.button5);
        tv1=(TextView)findViewById(R.id.textView4);
        tv2=(TextView)findViewById(R.id.textView6);
        firebaseAuth=FirebaseAuth.getInstance();
        FirebaseUser userfb=firebaseAuth.getCurrentUser();
        progressDialog=new ProgressDialog(this );
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user, password;
                user = mail.getText().toString();
                password = pass.getText().toString();
                progressDialog.setMessage("Wait for a moment");

                firebaseAuth.signInWithEmailAndPassword(user, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(getApplicationContext(), "Login Successfull", Toast.LENGTH_LONG).show();
                            Intent i = new Intent(getApplicationContext(), Home.class);
                            startActivity(i);
                        } else {
                            Toast.makeText(getApplicationContext(), "LOGIN FAILED", Toast.LENGTH_LONG).show();
                        }
                    }

                });
            }
        });

        tv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent j=new Intent(getApplicationContext(),RegActivity.class);
                startActivity(j);
            }
        });
        tv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent k=new Intent(getApplicationContext(),Password.class);
                startActivity(k);
            }
        });

    }
}

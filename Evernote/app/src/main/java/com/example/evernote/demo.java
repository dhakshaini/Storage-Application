package com.example.evernote;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;

import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class demo extends AppCompatActivity {
 Button btn;
 URLCLASS file;
 EditText name;
 private static final int PICK_FILE=1;
 private DatabaseReference ref;
    TextView show;
    ProgressDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);
        btn=(Button)findViewById(R.id.button99);
        show = (TextView) findViewById(R.id.textview99);
        name=findViewById(R.id.editTexts);
         dialog=new ProgressDialog(demo.this);
        ref= FirebaseDatabase.getInstance().getReference().child("FILES");

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fileupload(view);

            }
        });
        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),FILELIST.class);
                startActivity(i);
            }
        });
        dialog = new ProgressDialog(demo.this);

        // Setting up message in Progress dialog.
        dialog.setMessage("Uploading Images");

        // Showing progress dialog.
        dialog.show();

    }

    public void Fileupload(View view){
        Intent intent=new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("*/*");
        startActivityForResult(intent,PICK_FILE);
       // dialog.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==PICK_FILE){
            if(resultCode==RESULT_OK){
                Uri fileuri=data.getData();
                StorageReference Folder=FirebaseStorage.getInstance().getReference().child("Files");
                final StorageReference file_name=Folder.child("file"+fileuri.getLastPathSegment());
                file_name.putFile(fileuri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                           file_name.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                               @Override
                               public void onSuccess(Uri uri) {
                                  // HashMap<String ,String> hashMap=new HashMap<>();
                                  // hashMap.put("url",String.valueOf(uri));
                                   String url=String.valueOf(uri);
                                   String file_name=name.getText().toString().trim();
                                   file=new URLCLASS(file_name,url);
                                   ref.push().setValue(file);
                                   Toast.makeText(getApplicationContext(),"File Uploaded", Toast.LENGTH_SHORT).show();
                               }
                           });
                    }
                });

            }
        }
    }
}

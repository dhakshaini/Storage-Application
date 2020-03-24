package com.example.evernote;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.Button;
import androidx.annotation.Nullable;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import android.os.Bundle;
public class Videos extends AppCompatActivity {
    Button btn;
    VIDEOLIST file;
    EditText name;
    private static final int PICK_FILE=102;
    private DatabaseReference ref;
    TextView show;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_videos);
        btn=(Button)findViewById(R.id.button99);
        show = (TextView) findViewById(R.id.textview99);
        name=findViewById(R.id.editTexts);
        ref= FirebaseDatabase.getInstance().getReference().child("VIDEOFILES");
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fileupload(view);
            }
        });
        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),Showvideos.class);
                startActivity(i);
            }
        });
    }
    public void Fileupload(View view){
        Intent intent=new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("*/*");
        startActivityForResult(intent,PICK_FILE);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==PICK_FILE){
            if(resultCode==RESULT_OK){
                Uri fileuri=data.getData();
                StorageReference Folder=FirebaseStorage.getInstance().getReference().child("VIDEOS");
                final StorageReference file_name=Folder.child("video"+fileuri.getLastPathSegment());
                file_name.putFile(fileuri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        file_name.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                String url=String.valueOf(uri);
                                String file_name=name.getText().toString().trim();
                                file=new VIDEOLIST(file_name,url);
                                ref.push().setValue(file);
                                Toast.makeText(getApplicationContext(),"Video Uploaded", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });

            }
        }
    }

}

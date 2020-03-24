package com.example.evernote;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.app.DownloadManager;
import android.content.Context;
import android.net.Uri;
import android.os.Environment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import android.app.ProgressDialog;
import java.util.ArrayList;
import java.util.List;
public class Showimages extends AppCompatActivity {
    ListView listView1;
    FirebaseDatabase database;
    ProgressDialog progressDialog;
    DatabaseReference ref;
    FirebaseStorage firebaseStorage;
    StorageReference store,ref1;
    private FirebaseAuth mAuth;
    List<Upload> list;
    Upload file;
    ArrayAdapter<Upload> adapter;
    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showimages);
        listView1 = (ListView) findViewById(R.id.listViews);
        database = FirebaseDatabase.getInstance();
        list = new ArrayList<>();
        file= new Upload();
        DatabaseReference ref= FirebaseDatabase.getInstance().getReference().child("IMAGES");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    file = ds.getValue(Upload.class);
                    list.add(file);
                }
                String[] uploads = new String[list.size()];

                for (int i = 0; i < uploads.length; i++) {
                    uploads[i] = list.get(i).getName();
                }
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, uploads);
                listView1.setAdapter(adapter);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
        listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Upload urlclass=list.get(i);
                download(urlclass.getImageUrl());
            }
        });
    }

    public  void  download(String ImageUrl){
        store=firebaseStorage.getInstance().getReference();
        ref1=store.child("PICTURES");
        downloadfiles(getApplicationContext(),"image1",".jpg", Environment.DIRECTORY_DOWNLOADS,ImageUrl);
    }
    public void downloadfiles(Context context,String imagename, String imageextension,String destination,String ImageUrl) {
        DownloadManager downloadManager = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
        Uri uri = Uri.parse(ImageUrl);
        DownloadManager.Request request = new DownloadManager.Request(uri);
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        request.setDestinationInExternalFilesDir(context, destination, imagename + imageextension);
        downloadManager.enqueue(request);
    }
}



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
import java.util.ArrayList;
import java.util.List;
public class Showvideos extends AppCompatActivity {
    ListView listView2;
    FirebaseDatabase database;
    DatabaseReference ref;
    FirebaseStorage firebaseStorage;
    StorageReference store,ref1;
    private FirebaseAuth mAuth;
    List<VIDEOLIST> list;
    VIDEOLIST file;
    ArrayAdapter<VIDEOLIST> adapter;
    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showvideos);
        listView2 = (ListView) findViewById(R.id.listView1);
        database = FirebaseDatabase.getInstance();
        list = new ArrayList<>();
        file= new VIDEOLIST();
        DatabaseReference ref= FirebaseDatabase.getInstance().getReference().child("VIDEOFILES");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot ds:dataSnapshot.getChildren()){
                    file= ds.getValue(VIDEOLIST.class);
                    list.add(file);
                }
                String[] uploads = new String[list.size()];
                for (int i = 0; i < uploads.length; i++) {
                    uploads[i] = list.get(i).getName();
                }
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, uploads);
                listView2.setAdapter(adapter);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
        listView2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                VIDEOLIST urlclass=list.get(i);
                download(urlclass.getvideoUrl());
            }
        });
    }
    public  void  download(String videourl){
        store=firebaseStorage.getInstance().getReference();
        ref1=store.child("VIDEOS");
        downloadfiles(getApplicationContext(),"video1",".MP4", Environment.DIRECTORY_DOWNLOADS,videourl);
    }
    public void downloadfiles(Context context,String videoname, String videoextension,String destination,String videourl) {
        DownloadManager downloadManager = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
        Uri uri = Uri.parse(videourl);
        DownloadManager.Request request = new DownloadManager.Request(uri);
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        request.setDestinationInExternalFilesDir(context, destination, videoname + videoextension);
        downloadManager.enqueue(request);

    }
}

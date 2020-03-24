package com.example.evernote;
import androidx.appcompat.app.AppCompatActivity;
import android.app.DownloadManager;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
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
public class Showaudios extends AppCompatActivity {
    ListView listView3;
    FirebaseDatabase database;
    DatabaseReference ref;
    FirebaseStorage firebaseStorage;
    StorageReference store,ref1;
    private FirebaseAuth mAuth;
    List<AUDIOLIST> list;
    AUDIOLIST file;
    ArrayAdapter<AUDIOLIST> adapter;
    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showaudios);
        listView3 = (ListView) findViewById(R.id.listView2);
        database = FirebaseDatabase.getInstance();
        list = new ArrayList<>();
        file= new AUDIOLIST();
        DatabaseReference ref= FirebaseDatabase.getInstance().getReference().child("AUDIOFILES");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot ds:dataSnapshot.getChildren()){
                    file= ds.getValue(AUDIOLIST.class);
                    list.add(file);
                }
                String[] uploads = new String[list.size()];
                for (int i = 0; i < uploads.length; i++) {
                    uploads[i] = list.get(i).getName();
                }
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, uploads);
                listView3.setAdapter(adapter);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
        listView3.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                AUDIOLIST urlclass=list.get(i);
                download(urlclass.getAudiourl());
            }
        });
    }
    public  void  download(String audiourl){
        store=firebaseStorage.getInstance().getReference();
        ref1=store.child("AUDIOS");
        downloadfiles(getApplicationContext(),"audio1",".MP4", Environment.DIRECTORY_DOWNLOADS,audiourl);
    }
    public void downloadfiles(Context context,String audioname, String audioextension,String destination,String audioourl) {
        DownloadManager downloadManager = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
        Uri uri = Uri.parse(audioourl);
        DownloadManager.Request request = new DownloadManager.Request(uri);
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        request.setDestinationInExternalFilesDir(context, destination, audioname + audioextension);
        downloadManager.enqueue(request);

    }
}


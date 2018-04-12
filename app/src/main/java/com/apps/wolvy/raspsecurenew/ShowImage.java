package com.apps.wolvy.raspsecurenew;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class ShowImage extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private ImageAdapter mAdapter;

    private DatabaseReference mDatabaseRef;
    private List<Upload> mUploads;
    String sDate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_image);

        sDate = getIntent().getStringExtra("selecteddate");
        mRecyclerView = findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        //Toast.makeText(this, sDate, Toast.LENGTH_LONG).show();
        mUploads = new ArrayList<>();

        mDatabaseRef = FirebaseDatabase.getInstance().getReference("image").child(sDate);

        mDatabaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                mUploads.clear();
                for(DataSnapshot postSnapshot : dataSnapshot.getChildren()){
                    //mUploads.clear();
                    //Upload upload = postSnapshot.getValue(Upload.class);
                    //String upload1 = postSnapshot.getKey().toString();
                    String upload2 = postSnapshot.getValue().toString();
                    //Toast.makeText(ShowImage.this, "{"+upload1+"="+upload2+"}", Toast.LENGTH_LONG).show();
                    //String uploadf = "{"+upload1+"="+upload2+"}";
                    Upload upload = new Upload(upload2);
                    mUploads.add(upload);
                }

                mAdapter = new ImageAdapter(ShowImage.this, mUploads);
                mRecyclerView.setAdapter(mAdapter);


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



    }

}

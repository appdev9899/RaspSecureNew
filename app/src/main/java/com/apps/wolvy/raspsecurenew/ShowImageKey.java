package com.apps.wolvy.raspsecurenew;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;

public class ShowImageKey extends Fragment {

    ListView listView;
    private DatabaseReference mDatabaseRef;
    String getkey;
    String selected;

    List<String> your_array_list = new ArrayList<String>();
    LinkedHashSet<String> lhs = new LinkedHashSet<String>();
    private ProgressDialog dialog;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        dialog = new ProgressDialog(this.getActivity().getWindow().getContext());
        View view = inflater.inflate(R.layout.activity_show_image_key, null);
        listView = view.findViewById(R.id.listdate);
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("image");
        //mDatabaseRef = FirebaseDatabase.getInstance().getReference("image").child("20180412");
        dialog.setMessage("Doing something, please wait.");
        dialog.show();
        mDatabaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for(DataSnapshot postSnapshot : dataSnapshot.getChildren()){

                    getkey = postSnapshot.getRef().getKey();
                    //getKey = postSnapshot.getValue().toString();
                    your_array_list.add(getkey);
                }
                lhs.addAll(your_array_list);
                your_array_list.clear();
                your_array_list.addAll(lhs);
                Collections.sort(your_array_list);
                Collections.reverse(your_array_list);
                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getActivity().getApplicationContext(), android.R.layout.simple_list_item_1, your_array_list );
                listView.setAdapter(arrayAdapter);
                dialog.dismiss();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                selected = your_array_list.get(i);
                //Toast.makeText(getActivity().getApplicationContext(), selected, Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getActivity(), ShowImage.class);
                intent.putExtra("selecteddate", selected);
                startActivity(intent);
            }
        });
        return view;
    }

}

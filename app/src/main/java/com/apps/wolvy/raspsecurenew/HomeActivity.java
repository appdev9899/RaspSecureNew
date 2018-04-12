package com.apps.wolvy.raspsecurenew;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;


public class HomeActivity extends Fragment {

    String dates,times;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.activity_home,null);
        /*DateFormat dateFormat1 = new SimpleDateFormat("ddMMyyyy");
        DateFormat dateFormat2 = new SimpleDateFormat("HHmmss");
        Date date1 = new Date();
        Date date2 = new Date();
        //dates contains the date
        dates=dateFormat1.format(date1);
        times=dateFormat2.format(date2);*/





        return view;
    }
}
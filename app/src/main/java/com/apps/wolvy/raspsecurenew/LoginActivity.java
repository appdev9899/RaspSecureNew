package com.apps.wolvy.raspsecurenew;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import java.io.*;
import java.net.*;

public class LoginActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Boolean isFirstRun = getSharedPreferences("PREFERENCE", MODE_PRIVATE).getBoolean("isfirstrun", true);

        if (isFirstRun) {
            // do some thing
            String tosend = "\"apiKey\" :\"AlzaSyBRkchoUxL-3csyyUqlpapolWVYrdzLBU\"\n\"authDomain\":\"raspnew-47b2d.firebaseapp.com\"\n\"databaseURL\":\"https://raspnew-47b2d.firebaseio.com\"\n\"storageBucket\":\"raspnew-47b2d.appspot.com\"\n\"serviceAccount\":\"RaspSecureNew-10d1e83ba8d7.json\"";
            new con().execute(tosend);
            getSharedPreferences("PREFERENCE", MODE_PRIVATE).edit().putBoolean("isfirstrun", false).commit();
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
            finish();
        }

        else {
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
            finish();
        }
    }

    public class con extends AsyncTask<String, Void, Void>{

        @Override
        protected Void doInBackground(String... strings) {
            String message = strings[0];
            try{
                try {
                    Socket s = new Socket("192.168.0.198", 8888);
                    PrintWriter outToServer = new PrintWriter(new OutputStreamWriter(s.getOutputStream()));
                    outToServer.print(message);
                    outToServer.flush();
                } catch (Exception e) {
                    //System.err.println("Ooops!");
                    e.printStackTrace();
                }
            }
            catch (Exception e){
                e.printStackTrace();
            }
            return null;
        }
    }

}

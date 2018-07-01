package com.apps.wolvy.raspsecurenew;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;

import static android.provider.Contacts.SettingsColumns.KEY;


public class ShowLive extends Fragment {
    EditText editText;
    Button sendb;
    String ip;
    WebView webView;
    String url;

    private static final String TEXT_KEY = "tkey";
    private SharedPreferences preferences;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.activity_show_live,null);
        preferences = this.getActivity().getSharedPreferences("preferences",Context.MODE_PRIVATE);
        editText = (EditText) view.findViewById(R.id.ip);
        sendb = (Button) view.findViewById(R.id.send);
        webView =(WebView) view.findViewById(R.id.webView);
        editText.setText(preferences.getString(TEXT_KEY, ""));
        sendb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ip = editText.getText().toString();
                url = "http://"+ip;
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString(TEXT_KEY, editText.getText().toString()).commit();
                webView.getSettings().setJavaScriptEnabled(true);
                webView.loadUrl(url);
                webView.setWebViewClient(new WebViewClient());


            }
        });

        return view;
    }
    @Override
    public void onPause() {

        webView.stopLoading();
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
    }
}

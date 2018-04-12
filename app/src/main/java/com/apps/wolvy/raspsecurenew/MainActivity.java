package com.apps.wolvy.raspsecurenew;

import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    DrawerLayout mDrawerLayout;
    NavigationView mNavigationView;
    FragmentManager mFragmentManager;
    FragmentTransaction mFragmentTransaction;
    ActionBarDrawerToggle mDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer);
        mNavigationView = (NavigationView) findViewById(R.id.nav) ;

        mFragmentManager = getSupportFragmentManager();
        mFragmentTransaction = mFragmentManager.beginTransaction();
        //mFragmentTransaction.replace(R.id.containerView,new TabFragment()).commit();

        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                mDrawerLayout.closeDrawers();

                if (menuItem.getItemId() == R.id.home) {
                    //Toast.makeText(navandtab.this,"selected",Toast.LENGTH_SHORT).show();
                    //startActivity(new Intent(MainActivity.this, MainActivity.class));
                    HomeActivity homeActivity=new HomeActivity();
                    getSupportFragmentManager().beginTransaction().replace(R.id.containerView,homeActivity).commit();
                }

                if (menuItem.getItemId() == R.id.img) {
                    //Toast.makeText(navandtab.this,"selected",Toast.LENGTH_SHORT).show();
                    ShowImageKey showImageKey=new ShowImageKey();
                    getSupportFragmentManager().beginTransaction().replace(R.id.containerView,showImageKey).commit();
                }

                if (menuItem.getItemId() == R.id.video) {
                    //Toast.makeText(navandtab.this,"selected",Toast.LENGTH_SHORT).show();
                    ShowVideoKey showVideoKey=new ShowVideoKey();
                    getSupportFragmentManager().beginTransaction().replace(R.id.containerView,showVideoKey).commit();
                }
                if(menuItem.getItemId()==R.id.livefeed)
                {
                    // Toast.makeText(navandtab.this,"selected",Toast.LENGTH_SHORT).show();
                    ShowLive showLive=new ShowLive();
                    getSupportFragmentManager().beginTransaction().replace(R.id.containerView,showLive).commit();
                }



                return false;
            }

        });

        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.open, R.string.close);
        mDrawerLayout.addDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(mDrawerToggle.onOptionsItemSelected(item)){
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private Boolean exit = false;
    @Override
    public void onBackPressed() {
        if (exit) {
            finish(); // finish activity
        } else {
            Toast.makeText(this, "Press Back again to Exit.",
                    Toast.LENGTH_SHORT).show();
            exit = true;
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    exit = false;
                }
            }, 3 * 1000);

        }
    }

}

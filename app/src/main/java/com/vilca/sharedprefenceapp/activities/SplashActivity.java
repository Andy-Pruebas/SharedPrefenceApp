package com.vilca.sharedprefenceapp.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.vilca.sharedprefenceapp.R;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                nextActivity();
            }
        },200);
    }
    private void nextActivity(){
        SharedPreferences sp= PreferenceManager.getDefaultSharedPreferences(this);
        if(sp.getBoolean("islogged",false)){
            startActivity(new Intent(this,MainActivity.class));
        }else{
            startActivity(new Intent(this,LoginActivity.class));
        }
        finish();
    }
}

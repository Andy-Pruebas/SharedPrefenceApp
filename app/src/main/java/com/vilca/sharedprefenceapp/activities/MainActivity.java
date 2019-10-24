package com.vilca.sharedprefenceapp.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.vilca.sharedprefenceapp.R;
import com.vilca.sharedprefenceapp.model.User;
import com.vilca.sharedprefenceapp.repositories.UserRepository;

public class MainActivity extends AppCompatActivity {
    private TextView fullname;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SharedPreferences sp= PreferenceManager.getDefaultSharedPreferences(this);
        String username=sp.getString("username",null);
        fullname=findViewById(R.id.fullname);
        User user= UserRepository.findByUsername(username);

        if (user!=null){
            fullname.setText(user.getFullname());
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.logout_item:
                callLogout();
                break;
            case R.id.encuesta:
                callencuesta();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
    private void callencuesta(){
        startActivity(new Intent(this,EncuestaActivity.class));
    }
    private void callLogout() {
        // Eliminar el estado islogged de la SP
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
        sp.edit().remove("islogged").commit();

        // Finalizamos
        finish();

        // y si se desea redireccionamos al LoginActivity
        ///startActivity(...);
    }
}

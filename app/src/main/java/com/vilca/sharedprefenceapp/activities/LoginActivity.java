package com.vilca.sharedprefenceapp.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.vilca.sharedprefenceapp.R;
import com.vilca.sharedprefenceapp.model.User;
import com.vilca.sharedprefenceapp.repositories.UserRepository;

public class LoginActivity extends AppCompatActivity {
    private View loginpanel;
    private EditText username_input,password_input;
    private Button sign;
    private ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loginpanel=findViewById(R.id.login_panel);
        username_input=findViewById(R.id.username_input);
        password_input=findViewById(R.id.password_input);
        sign=findViewById(R.id.sigin_button);
        progressBar=findViewById(R.id.progressbar);
        loadLastUsername();
        sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doLogin();
            }
        });
    }
    private void doLogin(){
        String username= username_input.getText().toString();
        String pass=password_input.getText().toString();
        if (username.isEmpty()){
            username_input.setError("Ingrese el usuario");
            Toast.makeText(this,"Ingrese el usuario",Toast.LENGTH_SHORT).show();
            return;
        }
        if (pass.isEmpty()){
            password_input.setError("Ingrese la contraseña");
            Toast.makeText(this,"Ingrese la contraseña",Toast.LENGTH_SHORT).show();
            return;
        }
        loginpanel.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);

        User user= UserRepository.login(username,pass);

        if(user==null){
            Toast.makeText(this,"Credenciales invalidas",Toast.LENGTH_SHORT).show();
            loginpanel.setVisibility(View.VISIBLE);
            progressBar.setVisibility(View.GONE);
            return;
        }
        SharedPreferences sp=PreferenceManager.getDefaultSharedPreferences(this);
        sp.edit().putBoolean("islogged",true)
                .putString("username",username).commit();

        startActivity(new Intent(this,MainActivity.class));
        finish();
    }
    private void loadLastUsername() {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
        String username = sp.getString("username", null);
        if(username != null) {
            username_input.setText(username);
            password_input.requestFocus();
        }
    }
}

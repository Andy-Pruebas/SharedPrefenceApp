package com.vilca.sharedprefenceapp.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioGroup;

import com.vilca.sharedprefenceapp.R;
import com.vilca.sharedprefenceapp.model.User;
import com.vilca.sharedprefenceapp.repositories.UserRepository;

public class EncuestaActivity extends AppCompatActivity {
    private SharedPreferences sp;
    private EditText fullname,carrera_input;
    private RadioGroup genero;
    private CheckBox terminos;
    private Button enviar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_encuesta);
        carrera_input=findViewById(R.id.carrera);
        enviar=findViewById(R.id.Enviar);
        sp=PreferenceManager.getDefaultSharedPreferences(this);
        carrera_input.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String carrera=s.toString();
                sp.edit().putString("carrera",carrera).commit();
            }
        });
        String carrera=sp.getString("carrera",null);
        if(carrera!=null){
            carrera_input.setText(carrera);
        }
        genero=findViewById(R.id.genero);
        genero.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.man:
                        sp.edit().putString("gen","M").commit();
                        break;
                    case R.id.female:
                        sp.edit().putString("gen","F").commit();
                        break;
                }
            }
        });

        String gen=sp.getString("gen",null);
        if(gen!=null){
            if("M".equals(gen)){
                genero.check(R.id.man);
            }else if("F".equals(gen)){
                genero.check(R.id.female);
            }
        }
        terminos=findViewById(R.id.aceptar_terminos);
        terminos.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                sp.edit().putBoolean("terminos",isChecked).commit();
            }
        });
        boolean term=sp.getBoolean("terminos",false);
        if(term){
            terminos.setChecked(term);
        }
        sp= PreferenceManager.getDefaultSharedPreferences(this);
        String username=sp.getString("username",null);
        fullname=findViewById(R.id.fullname);
        User user= UserRepository.findByUsername(username);
        if (user!=null){
            fullname.setText(user.getFullname());
        }
        enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callenviar();
            }
        });
    }
    public void callenviar(){
        sp= PreferenceManager.getDefaultSharedPreferences(this);
        sp.edit().remove("carrera").remove("gen").remove("terminos").commit();
        startActivity(new Intent(this,MainActivity.class));
        finish();
    }
}

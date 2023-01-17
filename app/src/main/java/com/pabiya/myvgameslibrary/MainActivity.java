package com.pabiya.myvgameslibrary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.pabiya.myvgameslibrary.db.DBUsersHelper;

public class MainActivity extends AppCompatActivity {

    EditText userText,passText,conPassText,email;
    Button bRegister, signIn;
    CheckBox admin;
    DBUsersHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userText=findViewById(R.id.userText);
        passText=findViewById(R.id.passText);
        conPassText=findViewById(R.id.conPassText);
        bRegister=findViewById(R.id.login);
        signIn=findViewById(R.id.signIn);
        email=findViewById(R.id.email);
        admin=findViewById(R.id.admin);

        DB= new DBUsersHelper(this);

        signIn.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
            startActivity(intent);
        });

        bRegister.setOnClickListener(view -> {
            String pass,confirmPass,user,email;
            user=userText.getText().toString();
            pass=passText.getText().toString();
            confirmPass=conPassText.getText().toString();
            email=this.email.getText().toString();
            int onAdmin=0;
            if (admin.isChecked()){
                onAdmin=1;
            }

            if (user.isEmpty() || pass.isEmpty() || confirmPass.isEmpty()){
                Toast.makeText(this, "Uno o varios campos estan vacios", Toast.LENGTH_SHORT).show();
            }else {
                if (pass.equals(confirmPass)){
                    if (DB.checkUser(user)){
                        if (DB.insertData(user,pass,email,onAdmin)){
                            Toast.makeText(this, "Usuario insertado", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(),SplashActivity.class);
                            intent.putExtra("name",user);
                            startActivity(intent);
                        }else {
                            Toast.makeText(this, "registro fallido", Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        Toast.makeText(this, "Usuario ya existente", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


}
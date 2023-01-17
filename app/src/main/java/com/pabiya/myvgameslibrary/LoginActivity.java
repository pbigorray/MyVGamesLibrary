package com.pabiya.myvgameslibrary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.pabiya.myvgameslibrary.db.DBUsersHelper;

public class LoginActivity extends AppCompatActivity {
    EditText userText,passText,conPassText;
    Button login, signIn;
    DBUsersHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        userText=findViewById(R.id.userText);
        passText=findViewById(R.id.passText);
        conPassText=findViewById(R.id.conPassText);
        login =findViewById(R.id.login);
        signIn=findViewById(R.id.signIn);

        DB = new DBUsersHelper(this);


        login.setOnClickListener(view -> {
            String pass,user;
            user=userText.getText().toString();
            pass=passText.getText().toString();

            if (user.isEmpty() || pass.isEmpty()){
                Toast.makeText(this, "Uno o varios campos estan vacios", Toast.LENGTH_SHORT).show();
            }else {
                    if (!DB.checkUser(user)){
                        if (!DB.checkUserPass(user,pass)){
                            Toast.makeText(this, "Usuario correcto", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(),HomeActivity.class);
                            startActivity(intent);
                        }else {
                            Toast.makeText(this, "la contraseña no es correcta", Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        Toast.makeText(this, "el usuario no existe", Toast.LENGTH_SHORT).show();
                    }
            }
        });

    }
}
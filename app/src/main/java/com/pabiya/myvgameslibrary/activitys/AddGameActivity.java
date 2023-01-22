package com.pabiya.myvgameslibrary.activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.pabiya.myvgameslibrary.R;
import com.pabiya.myvgameslibrary.SplashActivity;
import com.pabiya.myvgameslibrary.VideoGames;
import com.pabiya.myvgameslibrary.db.DBGamesHelper;

public class AddGameActivity extends AppCompatActivity {
    EditText nombre,id,genero,precio;
    CheckBox alquilado;
    Button add,cancel;
    DBGamesHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_game);
        db = new DBGamesHelper(this);
        nombre=findViewById(R.id.nombre);
        id=findViewById(R.id.id);
        genero=findViewById(R.id.genero);
        precio=findViewById(R.id.precio);
        alquilado=findViewById(R.id.alquilado);
        add=findViewById(R.id.add);
        cancel=findViewById(R.id.cancel);

        Bundle aux = getIntent().getExtras();

        add.setOnClickListener(view -> {

            String name= nombre.getText().toString();
            String gender= genero.getText().toString();
            int id = Integer.parseInt(this.id.getText().toString());
            float price= Float.parseFloat(precio.getText().toString());
            int al=0;
            if (alquilado.isChecked()){
                al=1;
            }
            if (!db.checkGame(id,this)){
                db.updateData(id,name,gender,price,al);
                Toast.makeText(this, "El juego sepau actualizado correctamente", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, VideoGames.class);
                intent.putExtra("admin",aux.getBoolean("admin"));
                startActivity(intent);
            }else {
                db.insertData(id,name,gender,price,al);
                Toast.makeText(this, "El juego se a añadido correctamente", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, VideoGames.class);
                intent.putExtra("admin",aux.getBoolean("admin"));
                startActivity(intent);
            }

        });

        cancel.setOnClickListener(view -> {
            Intent intent = new Intent(this, VideoGames.class);
            intent.putExtra("admin",aux.getBoolean("admin"));
            startActivity(intent);
        });
    }
}
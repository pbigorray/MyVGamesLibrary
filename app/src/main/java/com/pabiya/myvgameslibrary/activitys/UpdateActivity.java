package com.pabiya.myvgameslibrary.activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.pabiya.myvgameslibrary.R;
import com.pabiya.myvgameslibrary.VideoGames;
import com.pabiya.myvgameslibrary.db.DBGamesHelper;

public class UpdateActivity extends AppCompatActivity {
    EditText id;
    CheckBox alquilado;
    Button add,cancel, update;
    DBGamesHelper db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        db = new DBGamesHelper(this);
        id=findViewById(R.id.id);
        alquilado=findViewById(R.id.alquilado);
        add=findViewById(R.id.add);
        cancel=findViewById(R.id.cancel);
        update=findViewById(R.id.update);

        Bundle aux = getIntent().getExtras();

        if (aux.getInt("id")!=0){
            id.setText(""+aux.getInt("id"));
            int al=db.getGame(aux.getInt("id"));
            if (al==1){
                alquilado.setChecked(true);
            }

        }
        update.setOnClickListener(view -> {
            int id = Integer.parseInt(this.id.getText().toString());

            int al=0;
            if (alquilado.isChecked()){
                al=1;
            }

            db.updateData(id,al);
            Toast.makeText(this, "El juego se actualizado correctamente", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, VideoGames.class);
            intent.putExtra("admin",aux.getBoolean("admin"));
            startActivity(intent);
        });

        add.setOnClickListener(view -> {
            Intent intent = new Intent(this, AddGameActivity.class);
            intent.putExtra("admin",aux.getBoolean("admin"));
            startActivity(intent);
        });
        cancel.setOnClickListener(view -> {
            Intent intent = new Intent(this, VideoGames.class);
            intent.putExtra("admin",aux.getBoolean("admin"));
            startActivity(intent);
        });


    }
}
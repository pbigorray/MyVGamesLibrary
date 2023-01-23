package com.pabiya.myvgameslibrary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.pabiya.myvgameslibrary.activitys.Game;
import com.pabiya.myvgameslibrary.activitys.UpdateActivity;
import com.pabiya.myvgameslibrary.db.DBGamesHelper;

import java.util.List;

public class GameInfoActivity extends AppCompatActivity {
    TextView name,id,price,gender,alquilado;
    DBGamesHelper DB;
    Button update;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_info);
        name=findViewById(R.id.name);
        id=findViewById(R.id.id);
        price=findViewById(R.id.price);
        gender=findViewById(R.id.gender);
        alquilado=findViewById(R.id.alquilado);
        update=findViewById(R.id.update);

        DB=new DBGamesHelper(this);
        Bundle aux = getIntent().getExtras();
        List gameList= DB.getAll();

        Game g =(Game) gameList.get(getIntent().getExtras().getInt("position"));

        name.setText(g.getName());
        id.setText("Id: "+g.getId());
        price.setText("Precio: "+g.getPrice());
        gender.setText("Generos: "+g.getGender());

        if (g.getAlquilado()==1){
            alquilado.setText("Alquilado");
        }else {
            alquilado.setText("Disponible");
        }
        if (aux.getBoolean("admin")){
            update.setVisibility(View.VISIBLE);
            update.setOnClickListener(view -> {
                Intent intent = new Intent(this, UpdateActivity.class);
                intent.putExtra("admin",aux.getBoolean("admin"));
                intent.putExtra("id",g.getId());
                startActivity(intent);
            });
        }

    }
}
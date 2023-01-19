package com.pabiya.myvgameslibrary;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.pabiya.myvgameslibrary.activitys.Game;
import com.pabiya.myvgameslibrary.db.DBGamesHelper;

import java.util.List;

public class GameInfoActivity extends AppCompatActivity {
    TextView name,id,price,gender,alquilado;
    DBGamesHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_info);
        name=findViewById(R.id.name);
        id=findViewById(R.id.id);
        price=findViewById(R.id.price);
        gender=findViewById(R.id.gender);
        alquilado=findViewById(R.id.alquilado);

        DB=new DBGamesHelper(this);

        List gameList= DB.getAll();

        Game g =(Game) gameList.get(getIntent().getExtras().getInt("position"));

        name.setText(g.getName());
        id.setText(""+g.getId());
        price.setText("Precio: "+g.getPrice());
        gender.setText("Generos: "+g.getGender());
    }
}
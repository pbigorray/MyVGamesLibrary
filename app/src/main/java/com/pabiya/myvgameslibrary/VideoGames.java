package com.pabiya.myvgameslibrary;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.pabiya.myvgameslibrary.activitys.AddGameActivity;
import com.pabiya.myvgameslibrary.activitys.MyRecyclerViewAdapter;
import com.pabiya.myvgameslibrary.db.DBGamesHelper;

public class VideoGames extends AppCompatActivity {
    RecyclerView recycler;
    DBGamesHelper DB;
    Button add;
    boolean admin;
    Bundle aux;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_games);
        DB = new DBGamesHelper(this);
        DB.addData();
        recycler = findViewById(R.id.recicle);
        add = findViewById(R.id.add);
        aux = getIntent().getExtras();
        admin = aux.getBoolean("admin");
        add.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), AddGameActivity.class);
            intent.putExtra("admin", admin);
            startActivity(intent);
        });
        if (admin) {
            add.setVisibility(View.VISIBLE);
        }

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, RecyclerView.VERTICAL);
        recycler.addItemDecoration(dividerItemDecoration);
        MyRecyclerViewAdapter myRecyclerViewAdapter = new MyRecyclerViewAdapter(this);
        myRecyclerViewAdapter.setListener(view -> {
            Intent intent = new Intent(getApplicationContext(), GameInfoActivity.class);
            int position = recycler.getChildAdapterPosition(view);
            intent.putExtra("position", position);
            intent.putExtra("admin", admin);
            startActivity(intent);
        });
        recycler.setAdapter(myRecyclerViewAdapter);

        LinearLayoutManager myLinearLayaoutManager = new LinearLayoutManager(this);
        recycler.setLayoutManager(myLinearLayaoutManager);
        recycler.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), GameInfoActivity.class);
            int position = recycler.getChildAdapterPosition(view);
            intent.putExtra("position", position);
            intent.putExtra("admin", admin);
            startActivity(intent);
        });
    }
}
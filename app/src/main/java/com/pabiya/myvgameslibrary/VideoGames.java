package com.pabiya.myvgameslibrary;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.pabiya.myvgameslibrary.activitys.MyRecyclerViewAdapter;
import com.pabiya.myvgameslibrary.db.DBGamesHelper;

public class VideoGames extends AppCompatActivity implements View.OnClickListener {
    RecyclerView recycler;
    DBGamesHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_games);
        DB= new DBGamesHelper(this);
        DB.addData();
        recycler=findViewById(R.id.recicle);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, RecyclerView.VERTICAL);
        recycler.addItemDecoration(dividerItemDecoration);
        MyRecyclerViewAdapter myRecyclerViewAdapter = new MyRecyclerViewAdapter(this);
        myRecyclerViewAdapter.setListener(this);
        recycler.setAdapter(myRecyclerViewAdapter);

        LinearLayoutManager myLinearLayaoutManager=new LinearLayoutManager(this);
        recycler.setLayoutManager(myLinearLayaoutManager);
        recycler.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        Intent intent =new Intent(getApplicationContext(),SecondActivity.class);
        int position=recyclerView.getChildAdapterPosition(view);
        intent.putExtra("position",position);
        intent.putExtra("roo",root);
        intent.putExtra("city",ciudad);
        startActivity(intent);
    }
}
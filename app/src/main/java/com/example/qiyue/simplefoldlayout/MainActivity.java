package com.example.qiyue.simplefoldlayout;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private List<Integer> walls = Arrays.asList(R.mipmap.wall01,
            R.mipmap.wall02, R.mipmap.wall03, R.mipmap.wall04, R.mipmap.wall05,
            R.mipmap.wall06, R.mipmap.wall07, R.mipmap.wall08, R.mipmap.wall09,
            R.mipmap.wall10);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setAdapter(new MyAdapter(this,walls));
    }
}

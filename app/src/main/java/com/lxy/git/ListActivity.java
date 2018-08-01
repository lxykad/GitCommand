package com.lxy.git;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lxy
 */
public class ListActivity extends AppCompatActivity {

    private List<String> mList = new ArrayList<>();
    private RecyclerView mRecyclerView;
    private ActionSheetAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        initData();
        mRecyclerView = findViewById(R.id.recycler_view);
        mAdapter = new ActionSheetAdapter(mList);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mAdapter);

        mRecyclerView.addOnScrollListener(new RecyclerScrollListener());
        View headView = LayoutInflater.from(this).inflate(R.layout.list_head,mRecyclerView,false);

    }

    private void initData() {
        for (int i = 0; i < 5; i++) {
            mList.add(String.valueOf(i));
        }
    }
}

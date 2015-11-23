package com.edu.fa7.memogame;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

public class ViewRecordActivity extends AppCompatActivity {

    private List<Record> mRecords;
    private RecyclerView mRecyclerView;
    private RecordAdapter mRecordAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_record);

        mRecords = Record.listAll(Record.class);
        mRecyclerView = (RecyclerView) findViewById(R.id.rv_records);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mRecordAdapter = new RecordAdapter(this, mRecords);
        mRecordAdapter.ordenaLista();
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setAdapter(mRecordAdapter);
        mRecyclerView.setLayoutManager(linearLayoutManager);
    }
}

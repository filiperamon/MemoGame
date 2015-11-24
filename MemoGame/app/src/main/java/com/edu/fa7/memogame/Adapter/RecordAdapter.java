package com.edu.fa7.memogame.Adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.edu.fa7.memogame.R;
import com.edu.fa7.memogame.Utils.Record;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Creonilso on 05/11/2015.
 */
public class RecordAdapter extends RecyclerView.Adapter<RecordAdapter.RecordHolder> {

    private Activity mActivity;
    private List<Record> mRecords;

    public RecordAdapter(Activity activity, List<Record> records) {
        this.mActivity = activity;
        this.mRecords = records;
    }

    public void addTarefa(Record record) {
        mRecords.add(record);
        record.save();
        this.notifyDataSetChanged();
    }

    public void ordenaLista(){
        Collections.sort(mRecords, new Comparator<Record>() {
            @Override
            public int compare(Record lhs, Record rhs) {
                return lhs.getRecord() > rhs.getRecord() ? 0 : 1;
            }
        });
    }

    public void remove(Record record) {
        mRecords.remove(record);
        record.delete();
        this.notifyDataSetChanged();
    }

    @Override
    public RecordHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.record_item, viewGroup, false);
        return new RecordHolder(v);
    }

    @Override
    public void onBindViewHolder(RecordHolder tarefaHolder, int i) {
        Record record = mRecords.get(i);
        tarefaHolder.mRecord.setText(String.valueOf(record.getRecord()));
        tarefaHolder.mName.setText(record.getName());
    }

    @Override
    public int getItemCount() {
        return mRecords.size();
    }

    public class RecordHolder extends RecyclerView.ViewHolder{
        public TextView mRecord;
        public TextView mName;

        public RecordHolder(View itemView) {
            super(itemView);
            mName = (TextView) itemView.findViewById(R.id.tv_name_record);
            mRecord = (TextView) itemView.findViewById(R.id.tv_record);
        }

    }
}


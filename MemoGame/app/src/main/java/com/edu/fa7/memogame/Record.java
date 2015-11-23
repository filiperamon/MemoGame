package com.edu.fa7.memogame;

import com.orm.SugarRecord;

/**
 * Created by Creonilso on 23/11/2015.
 */
public class Record extends SugarRecord{

    private String name;
    private Long record;

    public Record() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getRecord() {
        return record;
    }

    public void setRecord(Long record) {
        this.record = record;
    }
}

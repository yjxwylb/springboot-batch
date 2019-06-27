package com.xyj.learn.entity;

import lombok.Data;

import java.util.List;

/**
 * version 1.0
 * parameter
 * since
 * return
 */
@Data
public class BatchEntity<T> {

    private List<T> entitys;
    private Class mapperClass;
    private Class entityClass;
    private String mthodName = "batchSave";

    public BatchEntity() {
        super();
    }

    public BatchEntity(List<T> entitys, Class mapperClass, String mthodName, Class entityClass) {
        super();
        this.entitys = entitys;
        this.mapperClass = mapperClass;
        this.mthodName = mthodName;
        this.entityClass = entityClass;
    }
}

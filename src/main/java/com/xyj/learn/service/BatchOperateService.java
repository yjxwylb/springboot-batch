package com.xyj.learn.service;


import com.xyj.learn.entity.BatchEntity;

import java.util.List;

/**
 * version 1.0
 * parameter
 * since
 * return
 */
public interface BatchOperateService {

    void batchSave(BatchEntity batchEntity) throws Exception;

    int executeBatchUpdate(BatchEntity batch) throws Exception;

    @SuppressWarnings ( {"rawtypes","unchecked"})
    List<Integer> executeBatchUpdateWithIndex(BatchEntity batch) throws Exception;
}

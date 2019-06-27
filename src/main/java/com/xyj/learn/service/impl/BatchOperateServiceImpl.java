package com.xyj.learn.service.impl;


import com.xyj.learn.entity.BatchEntity;
import com.xyj.learn.service.BatchOperateService;
import org.apache.ibatis.executor.BatchResult;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * version 1.0
 * parameter
 * since
 * return
 * 用于大批量的插入及更新操作
 */
@Service("batchOperateService")
public class BatchOperateServiceImpl implements BatchOperateService {
    private static Logger logger = LoggerFactory.getLogger(BatchOperateServiceImpl.class);
    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;

    @Override
    @SuppressWarnings({"rawtypes", "unchecked"})
    public void batchSave(BatchEntity batchEntity) throws Exception {
        if (null != batchEntity) {
            SqlSession sqlSession = sqlSessionTemplate.getSqlSessionFactory().openSession(ExecutorType.BATCH);
            if (null == sqlSession) {
                logger.error("get_sqlsession_fail...");
                throw new Exception("get_sqlsession_fail  open session ERR");
            }
            try {
                if (!CollectionUtils.isEmpty(batchEntity.getEntitys()) && null != batchEntity.getMapperClass()) {
                    Object mapper = sqlSession.getMapper(batchEntity.getMapperClass());
                    Class entityClass = null != batchEntity.getEntityClass() ? batchEntity.getEntityClass() :
                            batchEntity.getEntitys().get(0).getClass();
                    Method method = batchEntity.getMapperClass().getMethod(batchEntity.getMthodName(), entityClass);
                    for (Object entity : batchEntity.getEntitys()) {
                        method.invoke(mapper, entity);
                    }
                }
                sqlSession.commit();
                sqlSession.clearCache();
            } catch (Exception e) {
                sqlSession.rollback();
                logger.error("Exception occurred during updateBatch : ", e);
                throw new Exception("BATCH_SAVE_ERR");
            } finally {
                sqlSession.close();
            }
        }
    }

    @Override
    public int executeBatchUpdate(BatchEntity batch) throws Exception {
        int flag = 0;
        List<BatchResult> tmpResult = null;
        if (!CollectionUtils.isEmpty(batch.getEntitys()) && null != batch.getMapperClass()) {
            SqlSession sqlSession = sqlSessionTemplate.getSqlSessionFactory().openSession(ExecutorType.BATCH);
            if (null == sqlSession) {
                logger.error("get_sqlsession_fail...");
                throw new Exception("get_sqlsession_fail open session ERR");
            }
            try {
                Object mapper = sqlSession.getMapper(batch.getMapperClass());
                Class entityClass = null != batch.getEntityClass() ? batch.getEntityClass() : batch.getEntitys().get
                        (0).getClass();
                Method method = batch.getMapperClass().getMethod(batch.getMthodName(), entityClass);
                for (Object entity : batch.getEntitys()) {
                    method.invoke(mapper, entity);
                }
                tmpResult = sqlSession.flushStatements();
                sqlSession.commit();
                sqlSession.clearCache();
                if (tmpResult != null) {
                    for (BatchResult tmp : tmpResult) {
                        int x[] = tmp.getUpdateCounts();
                        for (int i = 0; i < x.length; i++) {
                            flag += x[i];
                        }
                    }
                }
            } catch (Exception e) {
                logger.error("executeBatchUpdate ERR : {}", e);
                throw new Exception("BATCH_UPDATE_ERR");
            } finally {
                sqlSession.close();
            }
        }
        return flag;
    }

    @Override
    @SuppressWarnings({"rawtypes", "unchecked"})
    public List<Integer> executeBatchUpdateWithIndex(BatchEntity batch) throws Exception {
        List<BatchResult> tmpResult = null;
        List<Integer> result = new ArrayList<>();
        if (!CollectionUtils.isEmpty(batch.getEntitys()) && null != batch.getMapperClass()) {
            SqlSession sqlSession = sqlSessionTemplate.getSqlSessionFactory().openSession(ExecutorType.BATCH);
            if (null == sqlSession) {
                logger.error("get_sqlsession_fail...");
                throw new Exception("get_sqlsession_fail open session ERR");
            }
            try {
                Object mapper = sqlSession.getMapper(batch.getMapperClass());
                Class entityClass = null != batch.getEntityClass() ? batch.getEntityClass() : batch.getEntitys().get
                        (0).getClass();
                Method method = batch.getMapperClass().getMethod(batch.getMthodName(), entityClass);
                for (Object entity : batch.getEntitys()) {
                    method.invoke(mapper, entity);
                }
                tmpResult = sqlSession.flushStatements();
                sqlSession.commit();
                sqlSession.clearCache();
                if (tmpResult != null) {
                    for (BatchResult tmp : tmpResult) {
                        int x[] = tmp.getUpdateCounts();
                        for (int i = 0; i < x.length; i++) {
                            if (x[i] == 0) {//如果更新条数为0，则认为是失败
                                result.add(i);
                            }
                        }
                    }
                }
            } catch (Exception e) {
                logger.error("executeBatchUpdate ERR : {}", e);
                throw new Exception("BATCH_UPDATE_ERR");
            } finally {
                sqlSession.close();
            }
        }
        return result;
    }
}

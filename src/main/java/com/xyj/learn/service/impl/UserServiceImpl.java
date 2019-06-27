package com.xyj.learn.service.impl;

import com.xyj.learn.entity.BatchEntity;
import com.xyj.learn.entity.UserEntity;
import com.xyj.learn.mapper.UserMapper;
import com.xyj.learn.service.BatchOperateService;
import com.xyj.learn.service.UserService;
import com.xyj.learn.vo.UserVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserServiceImpl implements UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private BatchOperateService batchOperateService;

    @Override
    public List<UserVo> query(UserVo userVo) {
        List<UserEntity> userEntities = userMapper.query(null);
        return null;
    }

    @Override
    public Long save(UserVo userVo) {
        return null;
    }

    @Override
    public Long update(UserVo userVo) {
        return null;
    }
    @Override
    public void batchUpdate(List<Long> userIds) {

        List<UserEntity> users = userMapper.getUsers(userIds);
        users.forEach(e->{
            e.setUserStatus(2);
        });
        BatchEntity<UserEntity> batchEntity = new BatchEntity<>();
        batchEntity.setMthodName("update");
        batchEntity.setMapperClass(UserMapper.class);
        batchEntity.setEntitys(users);
        try {
            batchOperateService.executeBatchUpdate(batchEntity);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
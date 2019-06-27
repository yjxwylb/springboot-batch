package com.xyj.learn.controller;

import com.xyj.learn.entity.BatchEntity;
import com.xyj.learn.entity.UserEntity;
import com.xyj.learn.mapper.UserMapper;
import com.xyj.learn.service.BatchOperateService;
import com.xyj.learn.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: xiayuejie
 * @date: 2019/6/26 18:51
 * @description:
 */
@RestController
public class BatchOperatorController  {

    @Autowired
    private BatchOperateService batchOperateService;
    @Autowired
    private UserService userService;

    @PostMapping("/test/batch/save")
    public String batchSave(){
        List<UserEntity> userEntities = new ArrayList<>();
        for (long i = 0; i < 5; i++) {
            UserEntity userEntity = new UserEntity();
            userEntity.setUserId(1000+i);
            userEntity.setUserName("张"+i);
            userEntity.setUserStatus(1);
            userEntity.setPhone("1385588400"+i);
            userEntity.setUserPassword("12345"+i);
            userEntities.add(userEntity);
        }

        BatchEntity<UserEntity> batchEntity = new BatchEntity<>();
        //batchEntity.setEntityClass(UserEntity.class);
        batchEntity.setEntitys(userEntities);
        batchEntity.setMapperClass(UserMapper.class);
        batchEntity.setMthodName("save");
        try {
            batchOperateService.batchSave(batchEntity);
        } catch (Exception e) {
            e.printStackTrace();

        }
        return "SUCCESS";
    }
    @PostMapping("/test/batch/update")
    public String batchUpdate(@RequestParam("userIds") List<Long> userIds){
        userService.batchUpdate(userIds);
        return "SUCCESS";
    }
}

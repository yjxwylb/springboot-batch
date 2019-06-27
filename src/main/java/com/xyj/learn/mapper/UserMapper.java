package com.xyj.learn.mapper;

import com.xyj.learn.entity.UserEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author: xiayuejie
 * @date: 2019/6/27 13:42
 * @description:
 */
public interface UserMapper {

     List<UserEntity> query(UserEntity userEntity);

     Long save(UserEntity userEntity);

     Long update(UserEntity userEntity);

     List<UserEntity> getUsers(@Param("userIds") List<Long> userIds);
}

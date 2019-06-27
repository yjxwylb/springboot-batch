package com.xyj.learn.entity;

import lombok.Data;

/**
 * @author: xiayuejie
 * @date: 2019/6/27 13:31
 * @description:
 */
@Data
public class UserEntity {

    private Long id;
    private Long userId;
    private String userName;
    private Integer userStatus;
    private String userPassword;
    private String phone;
}

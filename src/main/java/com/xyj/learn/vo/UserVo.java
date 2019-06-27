package com.xyj.learn.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author: xiayuejie
 * @date: 2019/6/27 14:51
 * @description:
 */
@Data
public class UserVo implements Serializable {

    private Long id;
    private Long userId;
    private String userName;
    private Integer userStatus;
    private String password;
    private String phone;
}

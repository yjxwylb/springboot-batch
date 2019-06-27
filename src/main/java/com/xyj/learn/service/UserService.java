package com.xyj.learn.service;

import com.xyj.learn.vo.UserVo;

import java.util.List;

public interface UserService{
	 List<UserVo> query(UserVo userVo) ;
	
	 Long save(UserVo userVo) ;

	 Long update(UserVo userVo) ;

	void batchUpdate(List<Long> userIds);

}

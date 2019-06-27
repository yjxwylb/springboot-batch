package com.xyj.learn;

import com.xyj.learn.entity.BatchEntity;
import com.xyj.learn.entity.UserEntity;
import com.xyj.learn.mapper.UserMapper;
import com.xyj.learn.service.BatchOperateService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BatchApplicationTests {

	@Autowired
	private BatchOperateService batchOperateService;


	@Test
	public void contextLoads()throws Exception {

		List<UserEntity> userEntities = new ArrayList<>();
		for (long i = 0; i < 5; i++) {
			UserEntity userEntity = new UserEntity();
			userEntity.setUserId(1000+i);
			userEntity.setUserName("张"+i);
			userEntity.setPhone("1385588400"+i);
			userEntity.setUserPassword("12345"+i);
			userEntities.add(userEntity);
		}

		BatchEntity<UserEntity> batchEntity = new BatchEntity<>();
		batchEntity.setEntitys(userEntities);
		batchEntity.setMapperClass(UserMapper.class);
		batchEntity.setMthodName("save");
		batchOperateService.batchSave(batchEntity);

	}

}

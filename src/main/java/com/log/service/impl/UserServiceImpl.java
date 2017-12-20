package com.log.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.log.dao.userMapper;
import com.log.model.user;
import com.log.service.UserService;

@Service("UserService")
public class UserServiceImpl  implements UserService{
   @Resource 
   private userMapper usermapper;

@Override
public user getUser(String user_string_id) {

	System.out.println(user_string_id);
	return this.usermapper.selectByUserId(user_string_id);
}

@Override
public int getUserIndex(String user_string_id) {
	// TODO Auto-generated method stub
	return this.usermapper.getUserIndex(user_string_id);
}

@Override
public user selectByPrimaryKey(Integer id) {
	// TODO Auto-generated method stub
	return this.usermapper.selectByPrimaryKey(id);
}



}

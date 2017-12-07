package com.test.service.impl;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service; 

import com.test.dao.UserMapper;
import com.test.model.User;
import com.test.service.UserService;


@Service
public class UserServiceImpl  implements UserService{
	@Resource
	private UserMapper userDao;
	@Override
	public User getUserById(int userId) {
		// TODO Auto-generated method stub
		return this.userDao.selectByPrimaryKey(userId);
	}
   
}

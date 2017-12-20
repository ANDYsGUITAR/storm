package com.log.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.log.dao.preBookMapper;
import com.log.model.preBook;
import com.log.service.preBookService;

@Service("preBookService")
public class preBookServiceImpl implements  preBookService{
	
	   @Resource
	   private preBookMapper prebookmapper ;

	@Override
	public preBook selectByPrimaryKey(int userIndex) {
		// TODO Auto-generated method stub
		return this.prebookmapper.selectByPrimaryKey(userIndex);
	}

}

package com.log.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.log.dao.bookMapper;
import com.log.model.book;
import com.log.service.BookService;
@Service("BookService")
public class BookImpl implements BookService{
	@Resource 
  private bookMapper  bookmapper;
	@Override
	public book getbook(int id) {
		// TODO Auto-generated method stub
		return this.bookmapper.selectByPrimaryKey(id);
	}
             
}

package com.log.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.log.dao.MonthyLearnTimeMapper;
import com.log.model.MonthyLearnTime;
import com.log.service.MonthyLearnTimeService;

@Service("MonthyLearnTimeService")

public class MonthyLearnTimeServiceImpl  implements MonthyLearnTimeService {
	@Resource
	private MonthyLearnTimeMapper  monthyLearnTimeMapper;
	@Override
	public List<MonthyLearnTime> selectByYear(String year, String account_no) {
		// TODO Auto-generated method stub
		List<MonthyLearnTime> list = this.monthyLearnTimeMapper.selectByYear(year, account_no);
		return list;
	}

}

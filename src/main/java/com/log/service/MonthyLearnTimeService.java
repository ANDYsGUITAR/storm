package com.log.service;

import java.util.List;


import com.log.model.MonthyLearnTime;

public interface MonthyLearnTimeService {
	 public List< MonthyLearnTime> selectByYear(String  year,String account_no);
}

package com.log.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.log.dao.LearnStatisticsMapper;
import com.log.pojo.LearnStatisticsPojo;
import com.log.service.LearnStatisticsService;

@Service("LearnStatistics")

public class LearnStatisticsServiceImpl implements LearnStatisticsService{
	@Resource
	private LearnStatisticsMapper learnStaticsMapper;
	public String timeTransfer(String hour){
		String transferedTime = null;
		double tempHour=Double.parseDouble(hour);
		int transferedHour=(int) Math.floor(tempHour);
		int transferedMinute=(int) Math.rint((tempHour-transferedHour)*60);
		transferedTime=String.valueOf(transferedHour)+"小时"+String.valueOf(transferedMinute)+"分钟";				
		return transferedTime;
	}

	@Override
	public List<LearnStatisticsPojo> selectByAccount(String account_no) {
		// TODO Auto-generated method stub
		List<LearnStatisticsPojo> list=this.learnStaticsMapper.selectByAccount(account_no);
		for(int i=0;i<list.size();i++){
			String temp=timeTransfer(list.get(i).getRuntime());
			list.get(i).setRuntime(temp);
		}
		return list;
	}

	@Override
	public int learnBookNum(String account_no) {
		// TODO Auto-generated method stub
		
		return this.learnStaticsMapper.learnBookNum(account_no);
	}

	@Override
	public String sumLearnTime(String account_no) {
		// TODO Auto-generated method stub
		return timeTransfer(this.learnStaticsMapper.sumLearnTime(account_no));
	}

//	@Override
//	public List<LearnStatisticsPojo> allStatistics(String account_no) {
//		List<LearnStatisticsPojo> learnStatisticsPojoList=this.learnStaticsMapper.selectByAccount(account_no);
//		int booknum=this.learnStaticsMapper.learnBookNum(account_no);
//		String allTime=this.learnStaticsMapper.sumLearnTime(account_no);
//		learnStatisticsPojoList.get(0).setBooknum(booknum);
//		learnStatisticsPojoList.get(0).setAllRuntime(allTime);
//		// TODO Auto-generated method stub
//		return learnStatisticsPojoList;
//	}
	
	


}

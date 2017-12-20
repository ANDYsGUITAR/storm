package com.log.model;

public class MonthyLearnTimeKey {
    private String account_no;

    private String date;
    
    

//    public MonthyLearnTimeKey(String account_no, String date) {
//		this.account_no = account_no;
//		this.date = date;
//	}

	public String getAccount_no() {
        return account_no;
    }

    public void setAccount_no(String account_no) {
        this.account_no = account_no == null ? null : account_no.trim();
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date == null ? null : date.trim();
    }
}
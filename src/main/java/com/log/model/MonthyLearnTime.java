package com.log.model;

public class MonthyLearnTime extends MonthyLearnTimeKey {
    private String runtime;


    public String getRuntime() {
        return runtime;
    }

    public void setRuntime(String runtime) {
        this.runtime = runtime == null ? null : runtime.trim();
    }

	

//	public MonthyLearnTime(String account_no, String date,String runtime) {
//		super(account_no,date);
//		this.runtime = runtime;
//	}
    
    
}
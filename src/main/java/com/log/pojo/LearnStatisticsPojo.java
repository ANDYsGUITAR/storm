package com.log.pojo;



public class LearnStatisticsPojo {

	private String book_name;
	private String runtime;
	private String account_no;

	
	

	public LearnStatisticsPojo(String book_name, String runtime, String account_no) {
		this.book_name = book_name;
		this.runtime = runtime;
		this.account_no = account_no;
	}
	
	public LearnStatisticsPojo()
	{
		
	}



	public String getBook_name() {
		return book_name;
	}

	public void setBook_name(String book_name) {
		this.book_name = book_name;
	}

	public String getRuntime() {
		return runtime;
	}

	public void setRuntime(String runtime) {
		this.runtime = runtime;
	}

	public String getAccount_no() {
		return account_no;
	}

	public void setAccount_no(String account_no) {
		this.account_no = account_no;
	}


}

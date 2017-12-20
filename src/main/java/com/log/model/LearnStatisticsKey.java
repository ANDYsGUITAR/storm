package com.log.model;

public class LearnStatisticsKey {
    private String account_no;

    private String book_id;

    public String getAccount_no() {
        return account_no;
    }

    public void setAccount_no(String account_no) {
        this.account_no = account_no == null ? null : account_no.trim();
    }

    public String getBook_id() {
        return book_id;
    }

    public void setBook_id(String book_id) {
        this.book_id = book_id == null ? null : book_id.trim();
    }
}
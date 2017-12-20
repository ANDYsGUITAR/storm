package com.log.model;

public class book {
    private Integer id;

    private String book_id;

    private String book_name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBook_id() {
        return book_id;
    }

    public void setBook_id(String book_id) {
        this.book_id = book_id == null ? null : book_id.trim();
    }

    public String getBook_name() {
        return book_name;
    }

    public void setBook_name(String book_name) {
        this.book_name = book_name == null ? null : book_name.trim();
    }
}
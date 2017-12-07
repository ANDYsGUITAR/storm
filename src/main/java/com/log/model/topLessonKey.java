package com.log.model;

public class topLessonKey {
    private String book_id;

    private String logyear;

    public String getBook_id() {
        return book_id;
    }

    public void setBook_id(String book_id) {
        this.book_id = book_id == null ? null : book_id.trim();
    }

    public String getLogyear() {
        return logyear;
    }

    public void setLogyear(String logyear) {
        this.logyear = logyear == null ? null : logyear.trim();
    }
}
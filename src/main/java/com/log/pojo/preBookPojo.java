package com.log.pojo;

public class preBookPojo {
        private String book_name;
        private String score;
        private String mark;
        private   int rank;
		public preBookPojo(String book_name, String score, int rank) {
			super();
			this.book_name = book_name;
			this.score = score;
			this.rank = rank;
		}
		public String getMark() {
			return mark;
		}
		public void setMark(String mark) {
			this.mark = mark;
		}
		public String getBook_name() {
			return book_name;
		}
		public preBookPojo(String book_name, String score, String mark, int rank) {
			this.book_name = book_name;
			this.score = score;
			this.mark = mark;
			this.rank = rank;
		}
		public void setBook_name(String book_name) {
			this.book_name = book_name;
		}
		public String getScore() {
			return score;
		}
		public void setScore(String score) {
			this.score = score;
		}
		public int getRank() {
			return rank;
		}
		public void setRank(int rank) {
			this.rank = rank;
		}
        
        
}

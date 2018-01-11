package com.log.pojo;

public class PreApiPojo {
	   private String book_id;
        public String getBook_id() {
		return book_id;
	}
	public void setBook_id(String book_id) {
		this.book_id = book_id;
	}
		private String book_name;
        private String score;
        private String mark;
        private   int rank;
		public PreApiPojo(String book_name, String score, int rank) {
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
		public PreApiPojo(String book_id,String book_name, String score, String mark, int rank) {
			this.book_id=book_id;
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

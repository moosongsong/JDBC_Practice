package vo;

public class Rating {
	private int id;
	private int comics_id;
	private String rating_date;
	private int rating_count;
	private int rating_price;
	
	public Rating() {
		// TODO Auto-generated constructor stub
	}

	public Rating(int id, int comics_id, String rating_date, int rating_count, int rating_price) {
		super();
		this.id = id;
		this.comics_id = comics_id;
		this.rating_date = rating_date;
		this.rating_count = rating_count;
		this.rating_price = rating_price;
	}

	public int getId() {
		return id;
	}

	public int getComics_id() {
		return comics_id;
	}

	public String getRating_date() {
		return rating_date;
	}

	public int getRating_count() {
		return rating_count;
	}

	public int getRating_price() {
		return rating_price;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setComics_id(int comics_id) {
		this.comics_id = comics_id;
	}

	public void setRating_date(String rating_date) {
		this.rating_date = rating_date;
	}

	public void setRating_count(int rating_count) {
		this.rating_count = rating_count;
	}

	public void setRating_price(int rating_price) {
		this.rating_price = rating_price;
	}
	
	
}

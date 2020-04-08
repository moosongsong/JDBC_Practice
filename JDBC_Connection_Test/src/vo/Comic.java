package vo;

public class Comic {
	private int id;
	private String title;
	private int price;
	private String category_code;
	private String publisher_code;
	private Category category;
	
	public Comic() {
		// TODO Auto-generated constructor stub
	}

	public Comic(int id, String title, int price, String category_code, String publisher_code, Category category) {
		super();
		this.id = id;
		this.title = title;
		this.price = price;
		this.category_code = category_code;
		this.publisher_code = publisher_code;
		this.category = category;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public int getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public int getPrice() {
		return price;
	}

	public String getCategory_code() {
		return category_code;
	}

	public String getPublisher_code() {
		return publisher_code;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public void setCategory_code(String category_code) {
		this.category_code = category_code;
	}

	public void setPublisher_code(String publisher_code) {
		this.publisher_code = publisher_code;
	}
	
	
	
	
}

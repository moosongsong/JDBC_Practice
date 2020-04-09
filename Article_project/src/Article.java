
public class Article {
	private int id;
	private String p_code;
	private String writer;
	private String title;
	private String text;
	private String regdate;
	private Part part;
	
	public Article() {
		this.part=new Part();
	}

	public Article(int id, String p_code, String writer, String title, String text, String regdate) {
		this();
		this.id = id;
		this.p_code = p_code;
		this.writer = writer;
		this.title = title;
		this.text = text;
		this.regdate = regdate;
	}

	public int getId() {
		return id;
	}

	public String getP_code() {
		return p_code;
	}

	public String getWriter() {
		return writer;
	}

	public String getTitle() {
		return title;
	}

	public String getText() {
		return text;
	}

	public String getRegdate() {
		return regdate;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setP_code(String p_code) {
		this.p_code = p_code;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setText(String text) {
		this.text = text;
	}

	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}
	
	
	
	public Part getPart() {
		return part;
	}

	public void setPart(Part part) {
		this.part = part;
	}

	@Override
	public String toString() {
		return "Article [id=" + id + ", p_code=" + p_code + ", writer=" + writer + ", title=" + title + ", text=" + text
				+ ", regdate=" + regdate + "]";
	}
	
	
}

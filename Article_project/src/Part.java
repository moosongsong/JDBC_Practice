import java.util.ArrayList;

public class Part {
	private String code;
	private String name;
	private ArrayList<Article> articles;
	
	public Part() {
		articles = new ArrayList<>();
	}

	public Part(String code, String name) {
		this.code = code;
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public String getName() {
		return name;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Part [code=" + code + ", name=" + name + "]";
	}
	
	public void setArticles(ArrayList<Article> articles){
		this.articles = articles;
	}
	public ArrayList<Article> getArticles(){
		return articles;
	}

}

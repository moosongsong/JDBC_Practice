package vo;
import java.util.ArrayList;

public class Category {
	private String code;
	private String name;
	private ArrayList<Comic> comics;
	
	public Category() {
		this.comics = new ArrayList<Comic>();
	}

	public Category(String code, String name) {
		this();
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

	public ArrayList<Comic> getComics() {
		return comics;
	}

	public void setComics(ArrayList<Comic> comics) {
		this.comics = comics;
	}
	
	public void addComic(Comic comic) {
		this.comics.add(comic);
	}
	
	public void removeComic(Comic comic) {
		this.comics.remove(comic);
	}
}

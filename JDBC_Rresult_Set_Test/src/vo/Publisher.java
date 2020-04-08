package vo;

public class Publisher {
	private String code;
	private String name;
	private String tel;
	private String fax;
	
	public Publisher() {
		this.code = null;
		this.name = null;
		this.tel = null;
		this.fax = null;
	}

	public Publisher(String code, String name, String tel, String fax) {
		this.code = code;
		this.name = name;
		this.tel = tel;
		this.fax = fax;
	}

	public String getCode() {
		return code;
	}

	public String getName() {
		return name;
	}

	public String getTel() {
		return tel;
	}

	public String getFax() {
		return fax;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}
	
}

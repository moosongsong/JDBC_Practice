
public class Member {
	private int id;
	private String email;
	private String nick;
	
	public Member() {
		
	}

	public Member(int id, String email, String nick) {
		this.id = id;
		this.email = email;
		this.nick = nick;
	}

	public int getId() {
		return id;
	}

	public String getEmail() {
		return email;
	}

	public String getNick() {
		return nick;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}
	
	
}

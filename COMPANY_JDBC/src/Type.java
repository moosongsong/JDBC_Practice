import java.util.ArrayList;

public class Type {
	private String type_code;
	private String type_name;
	private ArrayList<Employee> employees = null;
	
	public Type() {
		employees = new ArrayList<Employee>();
	}

	public Type(String type_code, String type_name) {
		this();
		this.type_code = type_code;
		this.type_name = type_name;
	}

	public String getType_code() {
		return type_code;
	}

	public String getType_name() {
		return type_name;
	}

	public void setType_code(String type_code) {
		this.type_code = type_code;
	}

	public void setType_name(String type_name) {
		this.type_name = type_name;
	}

	public ArrayList<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(ArrayList<Employee> employees) {
		this.employees = employees;
	}

	@Override
	public String toString() {
		return "Type [type_code=" + type_code + ", type_name=" + type_name + "]";
	}
	
	
}

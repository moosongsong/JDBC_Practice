
public class Employee {
	private String emp_code;
	private String emp_name;
	private String dept_code;
	private String duty_code;
	private String type_code;
	private String senior_code;
	private int income;
	private int worktime;
	
	public Employee() {
		
	}

	

	public Employee(String emp_code, String emp_name, String dept_code, String duty_code, String type_code,
			String senior_code, int income, int worktime) {
		this.emp_code = emp_code;
		this.emp_name = emp_name;
		this.dept_code = dept_code;
		this.duty_code = duty_code;
		this.type_code = type_code;
		this.senior_code = senior_code;
		this.income = income;
		this.worktime = worktime;
	}



	public String getEmp_code() {
		return emp_code;
	}

	public String getEmp_name() {
		return emp_name;
	}

	public String getDept_code() {
		return dept_code;
	}

	public String getDuty_code() {
		return duty_code;
	}

	public String getSenior_code() {
		return senior_code;
	}

	public int getIncome() {
		return income;
	}

	public int getWorktime() {
		return worktime;
	}

	public void setEmp_code(String emp_code) {
		this.emp_code = emp_code;
	}

	public void setEmp_name(String emp_name) {
		this.emp_name = emp_name;
	}

	public void setDept_code(String dept_code) {
		this.dept_code = dept_code;
	}

	public void setDuty_code(String duty_code) {
		this.duty_code = duty_code;
	}

	public void setSenior_code(String senior_code) {
		this.senior_code = senior_code;
	}

	public void setIncome(int income) {
		this.income = income;
	}

	public void setWorktime(int worktime) {
		this.worktime = worktime;
	}
	
	

	public String getType_code() {
		return type_code;
	}



	public void setType_code(String type_code) {
		this.type_code = type_code;
	}



	@Override
	public String toString() {
		return "Employee [emp_code=" + emp_code + ", emp_name=" + emp_name + ", dept_code=" + dept_code + ", duty_code="
				+ duty_code + ", senior_code=" + senior_code + ", income=" + income + ", worktime=" + worktime + "]";
	}
	
}

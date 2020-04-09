
public class SQLManager {
	public static final String SQL_INSERT_EMPLOYEE = "INSERT INTO employees VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
	public static final String SQL_DELETE_EMPLOYEE = "DELETE FROM employees WHERE emp_code = ?";
	public static final String SQL_DELETE_EMPLOYEE_FROM_DEPART = "DELETE FROM employees WHERE dept_code = ?";
	public static final String SQL_DELETE_EMPLOYEE_FROM_DUTIES = "DELETE FROM employees WHERE duties_code = ?";
	public static final String SQL_DELETE_EMPLOYEE_FROM_TYPE = "DELETE FROM employees WHERE type_code = ?";
	public static final String SQL_UPDATE_EMPLOYEE = "UPDATE employees SET emp_name = ?, dept_code = ?, duties_code = ?, type_code = ?, senior_code = ?, income = ?, worktime = ?";

	public static final String SQL_INSERT_DEPART = "INSERT INTO depart VALUES (?, ?)";
	public static final String SQL_DELETE_DEPART = "DELETE FROM depart WHERE dept_code = ?";
	public static final String SQL_UPDATE_DEPART = "UPDATE depart SET dept_name = ? WHERE dept_code = ?";
	
	public static final String SQL_INSERT_DUTIES = "INSERT INTO duties VALUES (?, ?)";
	public static final String SQL_DELETE_DUTIES = "DELETE FROM duties WHERE duties_code = ?";
	public static final String SQL_UPDATE_DUTIES = "UPDATE duties SET duties_name = ? WHERE duties_code = ?";
	
	public static final String SQL_SELECT_ALL = "SELECT * FROM types";
	public static final String SQL_SELECT_TYPE = "SELECT * FROM types WHERE type_code = ?";
	public static final String SQL_INSERT_TYPE = "INSERT INTO types VALUES (?, ?)";
	public static final String SQL_DELETE_TYPE = "DELETE FROM types WHERE type_code = ?";
	public static final String SQL_UPDATE_TYPE = "UPDATE types SET type_name = ? WHERE type_code = ?";
	
}

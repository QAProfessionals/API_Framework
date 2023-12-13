package pojosAPIStructure;

public class EmployeeAPI {

	private String name, id;

	public EmployeeAPI() {
	}

	public EmployeeAPI(String n, String i) {
		this.name = n;
		this.id = i;
	}

	public String getName() {
		return name;
	}

	public String getId() {
		return id;
	}

}

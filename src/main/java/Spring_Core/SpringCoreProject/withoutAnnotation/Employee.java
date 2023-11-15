package Spring_Core.SpringCoreProject.withoutAnnotation;

public class Employee {
	private String name;
	private Department department;

	public Employee() {

	}

	//all necessary properties on the bean have been set by the contain
	public void init() {
		System.out.println("Employee initialize");
	}
	
	//allows a bean to get a callback when the container containing it is destroyed.
	public void destroy() {
		System.out.println("Employee destroy");
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	@Override
	public String toString() {
		return "Employee [name=" + name + ", department=" + department + "]";
	}

}

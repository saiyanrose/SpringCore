package Spring_Core.SpringCoreProject.withoutAnnotation;

public class Department {

	private String name;

	public Department() {

	}

	public Department(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Department [name=" + name + "]";
	}

}

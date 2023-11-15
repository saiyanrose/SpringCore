package Spring_Core.SpringCoreProject.withoutAnnotation;

public class Office extends Employee {

	String name;

	public Office() {

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Office [name=" + name + "]";
	}

}

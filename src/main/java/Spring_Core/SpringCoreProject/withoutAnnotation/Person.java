package Spring_Core.SpringCoreProject.withoutAnnotation;

public class Person {

	private String name;
	private String address;

	public Person() {

	}

	//constructor injection
	//no partial injection
	//not suitable for large number of arguments when some properties are optional.
	//Preferred option when properties on the bean are more and immutable objects.
	public Person(String name, String address) {
		this.name = name;
		this.address = address;
	}

	public String getName() {
		return name;
	}

	public String getAddress() {
		return address;
	}

	@Override
	public String toString() {
		return "Person [name=" + name + ", address=" + address + "]";
	}

}

package Spring_Core.SpringCoreProject.withoutAnnotation;

public class Salary{

	private int salary;

	private Person person;

	public Salary() {

	}

	public Salary(int salary, Person person) {
		this.salary = salary;
		this.person = person;
	}

	public int getSalary() {
		return salary;
	}

	public Person getPerson() {
		return person;
	}

	@Override
	public String toString() {
		return "Salary [salary=" + salary + ", person=" + person + "]";
	}

}

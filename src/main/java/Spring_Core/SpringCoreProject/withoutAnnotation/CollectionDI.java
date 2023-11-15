package Spring_Core.SpringCoreProject.withoutAnnotation;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class CollectionDI {

	private List<Person> person;
	private Map<String, String> dbProperties;
	private Set<Car> car;

	public CollectionDI() {

	}

	public List<Person> getPerson() {
		return person;
	}

	//partial dependencies
	//re-configured at some later time
	public void setPerson(List<Person> person) {
		this.person = person;
	}

	public Map<String, String> getDbProperties() {
		return dbProperties;
	}

	public void setDbProperties(Map<String, String> dbProperties) {
		this.dbProperties = dbProperties;
	}

	public Set<Car> getCar() {
		return car;
	}

	public void setCar(Set<Car> car) {
		this.car = car;
	}

	@Override
	public String toString() {
		return "CollectionDI [person=" + person + ", dbProperties=" + dbProperties + ", car=" + car + "]";
	}

}

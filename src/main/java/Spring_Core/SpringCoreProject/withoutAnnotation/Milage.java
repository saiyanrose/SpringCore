package Spring_Core.SpringCoreProject.withoutAnnotation;

public class Milage {

	private Car car;
	private int milage;

	public Car getCar() {
		return car;
	}

	public void setCar(Car car) {
		this.car = car;
	}

	public int getMilage() {
		return milage;
	}

	public void setMilage(int milage) {
		this.milage = milage;
	}

	@Override
	public String toString() {
		return "Milage [car=" + car + ", milage=" + milage + "]";
	}

}

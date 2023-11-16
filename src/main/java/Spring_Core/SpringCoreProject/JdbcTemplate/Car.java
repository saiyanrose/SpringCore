package Spring_Core.SpringCoreProject.JdbcTemplate;

import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

//@Entity
//@Table(name="car")
//@Cacheable
//@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
public class Car {
	
	//@Id		
	private Integer id;
	
	private String brands;
	
	private String name;

	public Car() {

	}

	public Car(Integer id, String brands, String name) {
		this.id = id;
		this.brands = brands;
		this.name = name;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getBrands() {
		return brands;
	}

	public void setBrands(String brands) {
		this.brands = brands;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Car [id=" + id + ", brands=" + brands + ", name=" + name + "]";
	}

}

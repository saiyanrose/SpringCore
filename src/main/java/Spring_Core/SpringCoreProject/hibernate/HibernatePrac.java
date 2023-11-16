package Spring_Core.SpringCoreProject.hibernate;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import Spring_Core.SpringCoreProject.JdbcTemplate.Car;

public class HibernatePrac {

	public static void main(String[] args) {		
		ApplicationContext context=new ClassPathXmlApplicationContext("config.xml");
		CarHibernate carHibernate=(CarHibernate)context.getBean("carHibernate");	
		
		Car car=(Car) context.getBean("carJdbc2");
		car.setId(4);
		car.setName("Q4");//transient state
		car.setBrands("Bmw");//transient state
		carHibernate.saveCar(car);		
		
		//HQL
		List<Car> cars=carHibernate.allCars();
		System.out.println("total cars: "+cars.size());
		cars.forEach(System.out::println);
		
		//update
		int update=carHibernate.updateCar("i10",3);
		System.out.println(update);
		
		//HCQL
		List<Car>cars1=carHibernate.allCar();
		System.out.println("total cars: "+cars1.size());		
		
		//get()
		//method is used to retrieve a persistent object from the database.
		//method only hits the database if the object is not present in the session cache.
		//method returns null if there is no object present in the database.			
		car=carHibernate.getCarById();		
		System.out.println("get:"+car);		
		
		((ClassPathXmlApplicationContext) context).close();		
	}

}

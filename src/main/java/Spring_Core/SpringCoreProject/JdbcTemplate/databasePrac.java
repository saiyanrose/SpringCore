package Spring_Core.SpringCoreProject.JdbcTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class databasePrac {	

	public static void main(String[] args) {
		BeanFactory beanFactory=new ClassPathXmlApplicationContext("config.xml");		
		
		CarRepositoryImpl carRepositoryImpl=(CarRepositoryImpl) beanFactory.getBean("carRepo");
		
		Car car=(Car) beanFactory.getBean("carJdbc");		
		
		String car3=carRepositoryImpl.getName();
		System.out.println("Laptop brand: "+car3);
		
		Integer totalCar=carRepositoryImpl.getCarCount();
		System.out.println("total count of cars: "+totalCar);
		
		List<Car>allCars=carRepositoryImpl.allCar();
		allCars.stream()
				.forEach(System.out::println);
		
		car=carRepositoryImpl.getByBrand("Hyundai");
		System.out.println("get By brand: "+car);
		
		car=carRepositoryImpl.findCar("Hyundai");
		System.out.println("find car: "+car);		
		
		//batch update
		Car cars=(Car)beanFactory.getBean("carJdbc");
		Car cars2=(Car)beanFactory.getBean("carJdbc1");			
		
		List<Car>carList=new ArrayList<>();
		carList.add(cars);
		carList.add(cars2);			
		
		int[] count=carRepositoryImpl.batchUpdate(carList);
		IntStream countCars=Arrays.stream(count);	
		countCars.forEach(System.out::println);
		
		((ClassPathXmlApplicationContext) beanFactory).close();

	}

}

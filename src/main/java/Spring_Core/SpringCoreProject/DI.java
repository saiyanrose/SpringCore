package Spring_Core.SpringCoreProject;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import Spring_Core.SpringCoreProject.withoutAnnotation.CollectionDI;
import Spring_Core.SpringCoreProject.withoutAnnotation.Department;
import Spring_Core.SpringCoreProject.withoutAnnotation.Employee;
import Spring_Core.SpringCoreProject.withoutAnnotation.Milage;
import Spring_Core.SpringCoreProject.withoutAnnotation.Office;
import Spring_Core.SpringCoreProject.withoutAnnotation.Person;
import Spring_Core.SpringCoreProject.withoutAnnotation.Salary;

public class DI {

	public static void main(String[] args) {
		//IOC-->keeping the Java classes independent of each other
		//create,manage and configure objects and manage life cycle.
		
		//Need of DI-->through which the Spring container “injects” objects into other objects or “dependencies”.
		//class One needs the object of class Two to instantiate or operate a method
		//then class One is said to be dependent on class Two
		//this could lead to a lot of problems, including system failure.
		//Spring IOC resolves such dependencies with Dependency Injection, which makes the code easier to test and reuse.
		//Loose coupling
		
		ApplicationContext context=new ClassPathXmlApplicationContext("config.xml");
		
		//constructor injection
		Person person=(Person)context.getBean("person");
		Salary salary=(Salary)context.getBean("salary");
		
		System.out.println("Dependency Injection: "+person.getName());
		
		System.out.println("Dependency Injection: "+salary);
		
		//setter injection
		Milage milage=(Milage)context.getBean("miles");
		System.out.println("Dependency Injection: "+milage);
		
		//collections
		CollectionDI collectionDI=(CollectionDI)context.getBean("collections");
		System.out.println("Dependency Injection: "+collectionDI);
		
		//Autowire
		//automatically let Spring resolve collaborators (other beans) for your bean by inspecting the contents of the BeanFactory		
		//reduce or eliminate the need to specify properties or constructor arguments
		//if you need to add an additional dependency to a class, that dependency can be satisfied automatically without the need
		//to modify configuration.
		Employee emp=(Employee)context.getBean("emp");
		emp.setName("Tina");
		System.out.println(emp);
		
		//inheritance
		Office office=(Office)context.getBean("office");
		office.setName("Acer");
		office.setDepartment(new Department("CS"));		
		System.out.println(office.getDepartment());

		((ClassPathXmlApplicationContext)context).close();
	}

}

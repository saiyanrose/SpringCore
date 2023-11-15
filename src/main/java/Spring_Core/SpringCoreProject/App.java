package Spring_Core.SpringCoreProject;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import Spring_Core.SpringCoreProject.withoutAnnotation.Student;

public class App 
{
    public static void main( String[] args ){
    	//instantiate, configure, and manage the life cycle of beans. 
    	//BeanFactory does not support Annotation-based configuration.
    	//basic functionalities and is recommended to use for lightweight applications.
        BeanFactory beanFactory=new ClassPathXmlApplicationContext("config.xml");
        
        //Beans are Java objects that are configured at run-time by Spring IoC Container(Student).
        Student student=(Student) beanFactory.getBean("student");
        Student student1=(Student) beanFactory.getBean("student");
        
        System.out.println("bean factory: "+student.getName());
        
        System.out.println(beanFactory.isPrototype("student"));
        System.out.println(beanFactory.isSingleton("student"));
        System.out.println(beanFactory.getType("student"));
        
        //check singleton
        System.out.println(student.hashCode());
        System.out.println(student1.hashCode());
        
        ((ClassPathXmlApplicationContext)beanFactory).close();
    }
}

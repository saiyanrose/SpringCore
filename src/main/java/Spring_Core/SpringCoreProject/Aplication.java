package Spring_Core.SpringCoreProject;

import java.time.LocalDate;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import Spring_Core.SpringCoreProject.annotation.Cinema;
import Spring_Core.SpringCoreProject.annotation.Movie;

public class Aplication {

	public static void main(String[] args) {
		// it is an advanced container that extends the BeanFactory
		// it supports Annotation based configuration in Bean Autowiring.
		ApplicationContext context = new ClassPathXmlApplicationContext("annotationconfig.xml");

		Movie movie = (Movie) context.getBean("movie");
		movie.setName("The Gray Man");
		movie.setTime(LocalDate.now());

		Cinema cinema = (Cinema) context.getBean("cinema");
		cinema.setMovie(movie);
		cinema.setName("Inox");

		System.out.println(cinema);
		
		((ClassPathXmlApplicationContext)context).close();

	}

}

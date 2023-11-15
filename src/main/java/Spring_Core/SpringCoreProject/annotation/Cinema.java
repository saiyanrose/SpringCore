package Spring_Core.SpringCoreProject.annotation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class Cinema {

	private String name;

	private Movie movie;

	public Cinema() {

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Movie getMovie() {
		return movie;
	}
	
	//@Autowired(required=false)-->don't throw any exception if no suitable bean is found for autowiring.	
	@Autowired	
	@Qualifier("movie")//used to differentiate a bean among the same type of bean objects
	public void setMovie(Movie movie) {
		this.movie = movie;
	}

	@Override
	public String toString() {
		return "Cinema [name=" + name + ", movie=" + movie + "]";
	}

}

package Spring_Core.SpringCoreProject.annotation;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Required;

@SuppressWarnings("deprecation")
public class Movie {

	private String name;
	private LocalDate time;
	
	public Movie() {
		
	}

	public String getName() {
		return name;
	}

	//bean property must be populated at configuration time	
	@Required
	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getTime() {
		return time;
	}

	public void setTime(LocalDate time) {
		this.time = time;
	}

	@Override
	public String toString() {
		return "Movie [name=" + name + ", time=" + time + "]";
	}

}

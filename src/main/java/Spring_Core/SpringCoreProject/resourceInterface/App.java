package Spring_Core.SpringCoreProject.resourceInterface;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.Resource;

public class App {

	public static void main(String[] args) {
		// Resource Interface
		// abstracting access to low-level resources

		ApplicationContext context = new ClassPathXmlApplicationContext("config.xml");

		String filename = "student";
		Path path = Paths.get("D:\\" + filename + ".txt");
		String uri = path.toAbsolutePath().toString();

		Resource resource = context.getResource("file:" + uri);
		System.out.println("filename:" + resource.getFilename());
		System.out.println("file exits or not:" + resource.exists());
		System.out.println("description:" + resource.getDescription());

		try (InputStream is = resource.getInputStream();
				BufferedReader reader = new BufferedReader(new InputStreamReader(is));) {

			while (true) {
				String line = reader.readLine();
				if (line == null) {
					break;
				}
				System.out.println(line);

				// no longer needed
				line = null;
			}

		} catch (IOException e) {
			System.out.println(e.getMessage());
		}

		System.out.println("is open:" + resource.isOpen());

		// no longer needed
		// unreachable
		filename = null;
		uri = null;
		System.gc();
		System.runFinalization();

		((ClassPathXmlApplicationContext) context).close();

	}

}

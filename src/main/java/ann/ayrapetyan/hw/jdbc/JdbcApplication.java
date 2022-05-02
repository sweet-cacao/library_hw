package ann.ayrapetyan.hw.jdbc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class JdbcApplication {

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(JdbcApplication.class, args);
	}

}

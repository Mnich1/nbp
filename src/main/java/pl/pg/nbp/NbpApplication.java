package pl.pg.nbp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pl.pg.framework.Framework;

@SpringBootApplication
public class NbpApplication {

	public static void main(String[] args) {
		SpringApplication.run(NbpApplication.class, args);
		new Framework().startFramework();
	}

}

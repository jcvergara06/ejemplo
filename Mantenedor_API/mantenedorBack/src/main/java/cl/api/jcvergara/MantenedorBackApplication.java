package cl.api.jcvergara;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@EnableAutoConfiguration (exclude = { DataSourceAutoConfiguration.class })
public class MantenedorBackApplication {

	public static void main(String[] args) {
		SpringApplication.run(MantenedorBackApplication.class, args);
	}

}

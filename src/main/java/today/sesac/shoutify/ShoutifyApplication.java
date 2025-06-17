package today.sesac.shoutify;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.ClassPathResource;

@SpringBootApplication
public class ShoutifyApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShoutifyApplication.class, args);
		ClassPathResource resource = new ClassPathResource("shoutify-462603-e913da0ab150.json");
		System.out.println("@@@@@@@@@@@@@" + resource.exists());
	}

}

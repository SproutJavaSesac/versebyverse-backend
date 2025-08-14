package today.sesac.versebyverse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
public class VerseByVerseApplication {

    public static void main(String[] args) {

        SpringApplication.run(VerseByVerseApplication.class, args);
    }
}
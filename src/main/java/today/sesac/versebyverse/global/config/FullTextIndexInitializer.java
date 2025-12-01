package today.sesac.versebyverse.global.config;

import jakarta.persistence.EntityManager;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
@RequiredArgsConstructor
public class FullTextIndexInitializer implements CommandLineRunner {

    private final EntityManager entityManager;

    @Override
    @Transactional
    public void run(String... args) {

        try {
            List<?> result = entityManager.createNativeQuery(
                    "SHOW INDEX FROM profanities WHERE Key_name = 'ft_search'"
            ).getResultList();

            if (result.isEmpty()) {
                entityManager.createNativeQuery(
                                "ALTER TABLE profanities ADD FULLTEXT INDEX ft_search(original, replacement, description)")
                        .executeUpdate();
            }

        } catch (Exception e) {
            log.error("fulltextInitializer : " + e.getMessage());
        }

    }
}

package today.sesac.shoutify.profanity.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import today.sesac.shoutify.global.domain.BaseEntity;

/**
 * 비속어 Entity
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "profanities")
public class Profanity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 20, unique = true)
    private String original;
    @Column(length = 20)
    private String replacement;
    @Column(length = 500)
    private String description;

    @ColumnDefault("1")
    private int severity;

    @Column(length = 20)
    private String category;

    private Profanity(String original, String replacement, String description, int severity, String category){
        this.original = original;
        this.replacement = replacement;
        this.description = description;
        this.severity = severity;
        this.category = category;
    }

    public static Profanity of(String original, String replacement, String description, int severity, String category) {

       return new Profanity(original, replacement, description,severity, category);
    }


}

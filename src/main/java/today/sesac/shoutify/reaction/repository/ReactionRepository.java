package today.sesac.shoutify.reaction.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import today.sesac.shoutify.reaction.entity.Reaction;

public interface ReactionRepository extends JpaRepository<Reaction,Long> {
}

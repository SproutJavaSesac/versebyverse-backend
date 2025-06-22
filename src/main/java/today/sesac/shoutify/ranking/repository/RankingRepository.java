package today.sesac.shoutify.ranking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import today.sesac.shoutify.ranking.entity.Ranking;

/**
 * 순위(랭킹) 정보를 관리하는 레포지토리입니다. 기본적으로 순위 서비스에서 사용됩니다.
 */
@Repository
public interface RankingRepository extends JpaRepository<Ranking, Long> {

}

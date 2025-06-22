package today.sesac.shoutify.ranking.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import today.sesac.shoutify.ranking.entity.Ranking;
import today.sesac.shoutify.ranking.entity.RankingCategory;
import today.sesac.shoutify.ranking.repository.RankingRepository;

/**
 * 순위(랭킹) 정보를 관리하는 서비스입니다. 다른 서비스에서 순위 정보를 조회할 때 사용됩니다.
 */
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class RankingService {

  private final RankingRepository rankingRepository;

  /**
   * 순위(랭킹) 정보를 조회합니다.
   * TODO 추후 RankingResponse로 변경 예정
   *
   * @param category 조회할 순위(랭킹) 카테고리
   */
  public List<Ranking> getRankingsByCategory(RankingCategory category) {
    return List.of();
  }

  /**
   * 내 순위(랭킹) 정보를 조회합니다.
   *
   * @param memberId 회원 ID
   * @return 내 순위(랭킹) 정보
   */
  public Ranking getMyRankingByMemberId(Long memberId) {
    return null;
  }
}

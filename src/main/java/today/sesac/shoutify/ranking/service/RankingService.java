package today.sesac.shoutify.ranking.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import today.sesac.shoutify.ranking.repository.RankingRepository;

/**
 * 순위(랭킹) 정보를 관리하는 서비스입니다.
 * 다른 서비스에서 순위 정보를 조회할 때 사용됩니다.
 */
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class RankingService {
	private final RankingRepository rankingRepository;
}

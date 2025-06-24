package today.sesac.shoutify.comment.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import today.sesac.shoutify.comment.dto.CommentRequest;
import today.sesac.shoutify.comment.dto.CommentResponse;
import today.sesac.shoutify.comment.entity.Comment;
import today.sesac.shoutify.comment.repository.CommentRepository;
import today.sesac.shoutify.member.entity.Member;
import today.sesac.shoutify.member.repository.MemberRepository;
import today.sesac.shoutify.post.entity.Post;
import today.sesac.shoutify.post.repository.PostRespository;

@Service
@RequiredArgsConstructor
public class CommentService {

	private final CommentRepository commentRepository;
	private final PostRespository postRepository;
	private final MemberRepository memberRepository; //변경 예정

	/**
	 * 댓글을 생성하고 저장합니다.
	 *
	 * @param postId   댓글이 달리는 게시글 ID
	 * @param authorId 댓글 작성자 ID
	 * @param request  댓글 요청 정보 (내용, parentId 등)
	 * @return CommentResponse DTO
	 */
	@Transactional
	public CommentResponse createComment(Long postId, Long authorId, CommentRequest request) {

		// 게시글 존재 확인
		Post post = postRepository.findById(postId)
			.orElseThrow(() -> new IllegalArgumentException("게시글이 존재하지 않습니다."));

		// 실제 서비스에서는 주석 처리된 코드로 대체
		// Member author = memberService.findById(authorId);
		Member author = memberRepository.findById(authorId)
			.orElseThrow(() -> new IllegalArgumentException("회원이 존재하지 않습니다."));

		// 댓글 객체 생성
		Comment comment = Comment.create(
			request.getContent(), // beforeContent
			request.getContent(), // afterContent (AI 미처리 상태)
			post,
			author
		);

		// DB에 저장
		Comment savedComment = commentRepository.save(comment);

		// 엔티티를 DTO로 변환해서 반환
		return CommentResponse.from(savedComment);
	}

	/**
	 * 특정 게시글의 댓글 목록을 조회합니다.
	 *
	 * @param postId 댓글을 조회할 게시글 ID
	 * @return 댓글 목록
	 */
	@Transactional(readOnly = true)
	public List<CommentResponse> getCommentsByPostId(Long postId) {
		// 게시글 존재 확인
		Post post = postRepository.findById(postId)
			.orElseThrow(() -> new IllegalArgumentException("게시글이 존재하지 않습니다."));

		// 댓글 목록 조회 (삭제되지 않은 댓글만, 생성일 순으로 정렬)
		List<Comment> comments = commentRepository.findByPostId(postId);

		// DTO로 변환해서 반환
		return comments.stream()
			.map(CommentResponse::from)
			.toList();
	}
}

package today.sesac.shoutify.comment.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import today.sesac.shoutify.comment.repository.CommentRepository;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
}

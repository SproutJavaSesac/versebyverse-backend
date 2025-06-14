package today.sesac.shoutify.post.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import today.sesac.shoutify.post.repository.PostRespository;

@Service
@RequiredArgsConstructor
public final class PostService {
    private PostRespository postRespository;

}

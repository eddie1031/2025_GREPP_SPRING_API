package io.eddie.greppapi.controller;

import io.eddie.greppapi.dto.SaveRequest;
import io.eddie.greppapi.entity.Post;
import io.eddie.greppapi.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/posts")
public class PostController {

    private final PostRepository postRepository;

    @ResponseBody
    @GetMapping("/{postId}")
    public Post findById(@PathVariable Long postId) {
        Post findPost = postRepository.findById(postId);
        return findPost;
    }

    @PostMapping
    public Long save(SaveRequest request) {
        return postRepository.save(Post.of(request)).getId();
    }

}

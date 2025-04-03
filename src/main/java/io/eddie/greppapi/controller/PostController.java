package io.eddie.greppapi.controller;

import io.eddie.greppapi.dto.SaveRequest;
import io.eddie.greppapi.dto.UpdateRequest;
import io.eddie.greppapi.entity.Post;
import io.eddie.greppapi.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Slf4j
//@Controller
//@ResponseBody
@RestController
@RequiredArgsConstructor
@RequestMapping("/posts")
public class PostController {

    private final PostRepository postRepository;

    @GetMapping("/{postId}")
//    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Post> findById(@PathVariable Long postId) {
        Post findPost = postRepository.findById(postId);
//        return ResponseEntity.status(HttpStatus.OK)
//                .body(findPost);
        return ResponseEntity.ok(findPost);
    }

    @PostMapping
//    @ResponseStatus(HttpStatus.CREATED) // 201
    public ResponseEntity<Long> save(@RequestBody SaveRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(
                        postRepository.save(
                                Post.of(request)
                        ).getId()
                );
    }

    // PATCH
    @PatchMapping("/{postId}")
//    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Post> update(@PathVariable Long postId, @RequestBody UpdateRequest request) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(postRepository.update(postId, request));

    }

    // DELETE
    @DeleteMapping("/{postId}")
//    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> remove(@PathVariable Long postId) {
        postRepository.remove(postId);
//        return ResponseEntity.status(HttpStatus.NO_CONTENT)
//                .build();
        return ResponseEntity.noContent()
                .build();
    }

}

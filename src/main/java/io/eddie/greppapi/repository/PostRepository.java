package io.eddie.greppapi.repository;

import io.eddie.greppapi.dto.UpdateRequest;
import io.eddie.greppapi.entity.Post;
import lombok.Getter;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class PostRepository {

    private Map<Long, Post> posts = new HashMap<>();

    @Getter
    private Long sequence = 0L;

    public Post save(Post post) {
        sequence++;

        post.setId(sequence);
        posts.put(post.getId(), post);

        return post;
    }

    public Post findById(Long id) {
        return posts.get(id);
    }

    public void remove(Long id) {
        posts.remove(id);
    }

    public Post update(Long id, UpdateRequest request) {
        Post findPost = posts.get(id);
        findPost.update(request);
        posts.replace(findPost.getId(), findPost);
        return findPost;
    }


}

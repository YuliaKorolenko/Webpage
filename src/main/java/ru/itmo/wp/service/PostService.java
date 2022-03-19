package ru.itmo.wp.service;

import org.springframework.stereotype.Service;
import ru.itmo.wp.domain.Comment;
import ru.itmo.wp.domain.Post;
import ru.itmo.wp.domain.Tag;
import ru.itmo.wp.domain.User;
import ru.itmo.wp.form.PostCredentials;
import ru.itmo.wp.repository.PostRepository;
import ru.itmo.wp.repository.TagRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

@Service
public class PostService {
    private final PostRepository postRepository;
    private final TagRepository tagRepository;

    public PostService(PostRepository postRepository, TagRepository tagRepository) {
        this.postRepository = postRepository;
        this.tagRepository = tagRepository;
    }

    public List<Post> findAll() {
        return postRepository.findAllByOrderByCreationTimeDesc();
    }

    public Post find(Long id) {
        return id == null ? null : postRepository.findById(id).orElse(null);
    }

    public void writeComment(Post post, String text, User user) {
        Comment comment = new Comment();
        comment.setText(text);
        comment.setPost(post);
        comment.setUser(user);
        post.addComment(comment);
        postRepository.save(post);
    }

    public Post postCredentialsToPost(PostCredentials postCredentials) {
        Post post = new Post();
        String[] tagsCredentials = postCredentials.getTags().split(" ");
        Set<Tag> tags = new HashSet<Tag>() {
        };
        for (String val : tagsCredentials) {
            val = val.toLowerCase(Locale.ROOT);
            Tag tag = tagRepository.findByName(val);
            if (tag == null) {
                tagRepository.save(new Tag(val));
            }
            tag = tagRepository.findByName(val);
            tags.add(tag);
        }
        post.setTags(tags);
        post.setText(postCredentials.getText());
        post.setTitle(postCredentials.getTitle());
        return post;
    }
}

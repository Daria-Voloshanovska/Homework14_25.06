package ait.cohort5860.post.service;

import ait.cohort5860.post.dao.CommentRepository;
import ait.cohort5860.post.dao.PostRepository;
import ait.cohort5860.post.dao.TagRepository;
import ait.cohort5860.post.dto.NewCommentDto;
import ait.cohort5860.post.dto.NewPostDto;
import ait.cohort5860.post.dto.PostDto;
import ait.cohort5860.post.dto.exeption.PostNotFoundException;
import ait.cohort5860.post.model.Comment;
import ait.cohort5860.post.model.Post;
import ait.cohort5860.post.model.Tag;
import ait.cohort5860.post.service.logging.PostLogger;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;


@Service
@RequiredArgsConstructor
public class PostServiceImpl  implements PostService {

    private final PostRepository postrepository;
    private final ModelMapper modelMapper;
    private final TagRepository tagRepository;
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;


    @Override
    @Transactional
    public PostDto addNewPost(String author, NewPostDto newPostDto) {
        Post post = new Post(newPostDto.getTitle(),newPostDto.getContent(), author);

        //Handle tags
        return getPostDto(newPostDto, post, postrepository);
    }

    @Override
    public PostDto findPostById(Long id) {
        Post post = postRepository.findById(id).orElseThrow(PostNotFoundException::new);
        return modelMapper.map(post,PostDto.class);
    }

    @Override
    @Transactional
    @PostLogger
    public PostDto updatePost(Long id, NewPostDto updatedPostDto) {
        Post post = postRepository.findById(id).orElseThrow(PostNotFoundException::new);
        post.setTitle(updatedPostDto.getTitle());
        post.setContent(updatedPostDto.getContent());

        post.getTags().clear();
        return getPostDto(updatedPostDto, post, postRepository);
    }

    private PostDto getPostDto(NewPostDto updatedPostDto, Post post, PostRepository postRepository) {
        Set<String> tags = updatedPostDto.getTags();
        if(tags !=null) {
            for (String tagName : tags) {
                Tag tag = tagRepository.findById(tagName).orElseGet(() -> tagRepository.save(new Tag(tagName)));
                post.addTag(tag);
            }
        }
        post = postRepository.save(post);
        return modelMapper.map(post,PostDto.class);
    }

    @Override
    @Transactional
    public PostDto deletePost(Long id) {
        Post post = postRepository.findById(id).orElseThrow(PostNotFoundException::new);
        postRepository.delete(post);
        return modelMapper.map(post,PostDto.class);
    }

    @Override
    @Transactional
    @PostLogger
    public void addLike(Long id) {
        Post post = postRepository.findById(id).orElseThrow(PostNotFoundException::new);
        post.addLike();
    }

    @Override
    @Transactional
    public PostDto addComment(Long id, String author, NewCommentDto newCommentDto) {
        Post post = postRepository.findById(id).orElseThrow(PostNotFoundException::new);
        Comment comment = new Comment(author, newCommentDto.getMessage());
        comment.setPost(post);
        post.addComment(comment);
        commentRepository.save(comment);
        return modelMapper.map(post,PostDto.class);
    }

    @Override
    @Transactional(readOnly = true)
    public List<PostDto> findPostsByAuthor(String author) {
        return postRepository.findByAuthorIgnoreCase(author)
         .map(post -> modelMapper.map(post,PostDto.class)).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<PostDto> findPostByTags(List<String> tags) {
        return postRepository.findByTagsNameIn(tags)
        .map(post -> modelMapper.map(post, PostDto.class))
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<PostDto> findPostsByPeriod(LocalDateTime  dateFrom, LocalDateTime  dateTo) {
        return postRepository.findByDateCreatedBetween(dateFrom, dateTo)
        .map(p -> modelMapper.map(p, PostDto.class))
                .toList();
    }
}

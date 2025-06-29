package ait.cohort5860.post.service;

import ait.cohort5860.post.dto.NewCommentDto;
import ait.cohort5860.post.dto.NewPostDto;
import ait.cohort5860.post.dto.PostDto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


public interface PostService {

    PostDto addNewPost(String author, NewPostDto newPostDto);

    PostDto findPostById(Long id);

    PostDto updatePost(Long id, NewPostDto updatedPostDto);

    PostDto deletePost(Long id);

    void addLike(Long id);

    PostDto addComment(Long id, String author, NewCommentDto newCommentDto);

    List<PostDto> findPostsByAuthor(String author);

    List<PostDto> findPostByTags(List<String> tags);

    List<PostDto> findPostsByPeriod(LocalDateTime dateFrom, LocalDateTime dateTo);


}

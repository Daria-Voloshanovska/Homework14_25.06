package ait.cohort5860.post.controller;


import ait.cohort5860.post.dto.NewCommentDto;
import ait.cohort5860.post.dto.NewPostDto;
import ait.cohort5860.post.dto.PostDto;
import ait.cohort5860.post.service.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/forum")
public class PostController {

    private final PostService postService;


    @PostMapping("/post/{author}")
    @ResponseStatus(HttpStatus.CREATED)
    public PostDto addNewPost(@PathVariable String author, @RequestBody @Valid NewPostDto newPostDto) {
        return postService.addNewPost(author, newPostDto);
    }
        @GetMapping("/post/{id}")
        public PostDto findPostById(@PathVariable Long id) {
            return postService.findPostById(id);
        }

        @PutMapping("/post/{id}")
        public PostDto updatePost(@PathVariable Long id, @RequestBody NewPostDto updatedPostDto){
            return postService.updatePost(id, updatedPostDto);
        }

        @DeleteMapping("/post/{id}")
        public PostDto deletePost(@PathVariable Long id){
            return postService.deletePost(id);
        }
        @PatchMapping("/post/{id}/like")
        @ResponseStatus(HttpStatus.NO_CONTENT)
        public void addLike (@PathVariable Long id){
            postService.addLike(id);

        }

        @PatchMapping("/post/{id}/comment/{author}")
        public PostDto addComment(@PathVariable Long id, @PathVariable String author, @RequestBody @Valid NewCommentDto newCommentDto){
            return postService.addComment(id, author, newCommentDto);
        }

        @GetMapping("/posts/author/{author}")
        public List<PostDto> findPostsByAuthor(@PathVariable String author){
            return postService.findPostsByAuthor(author);
        }

        @GetMapping("/posts/tags")
        public List<PostDto> findPostByTags(@RequestParam("values") List<String> tags) {
            return postService.findPostByTags(tags);
        }

        @GetMapping("/posts/period")
        public List<PostDto> findPostsByPeriod(@RequestParam LocalDateTime dateFrom,@RequestParam LocalDateTime dateTo){
            return postService.findPostsByPeriod(dateFrom, dateTo);
        }
    }

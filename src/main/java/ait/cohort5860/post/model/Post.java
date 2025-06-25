package ait.cohort5860.post.model;


import org.springframework.data.annotation.Id;


public class Post {
    @Id
    private Long id;
    private String title;
    private String content;
}

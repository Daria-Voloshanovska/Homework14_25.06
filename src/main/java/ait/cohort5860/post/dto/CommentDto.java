package ait.cohort5860.post.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommentDto {
private String user;
private String message;
private LocalDateTime dateCreated;
private Integer likes;


}

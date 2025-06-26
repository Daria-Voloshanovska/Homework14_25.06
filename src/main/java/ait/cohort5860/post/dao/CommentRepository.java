package ait.cohort5860.post.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ait.cohort5860.post.model.Comment;

public interface CommentRepository extends JpaRepository<Comment,Long> {

}

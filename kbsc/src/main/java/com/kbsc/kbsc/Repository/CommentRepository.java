package com.kbsc.kbsc.Repository;

import com.kbsc.kbsc.Entity.Comment;
import com.kbsc.kbsc.Entity.Posts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    /*게시글 댓글 목록 가져오기*/
    List<Comment> getCommentByPostsOrderById(Posts posts);
}

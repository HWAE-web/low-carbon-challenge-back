package com.kbsc.kbsc.Application.Dto;

import com.kbsc.kbsc.Entity.Comment;
import com.kbsc.kbsc.Entity.Posts;
import com.kbsc.kbsc.Entity.User;
import lombok.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CommentDto {
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Request{
        private Long id;
        private String comment;


        private Timestamp createdDate;
        private Timestamp modifiedDate;
        private User user;
        private Posts posts;

        /* Dto -> Entity */
        public Comment toEntity(){
            Comment comments = Comment.builder()
                    .id(id)
                    .comment(comment)
                    .createdDate(createdDate)
                    .modifiedDate(modifiedDate)
                    .user(user)
                    .posts(posts)
                    .build();

            return comments;
        }
    }

    @RequiredArgsConstructor
    @Getter
    public static class Response{
        private Long id;
        private String comment;

        Long datetime = System.currentTimeMillis();
        private Timestamp createdDate;
//        private Timestamp modifiedDate = new Timestamp(datetime);
        private Timestamp modifiedDate;
        private String nickname;
        private Long userId;
        private Long postsId;

        /*Entity -> Dto*/
        public Response(Comment comment){
            this.id = comment.getId();
            this.comment = comment.getComment();
            this.createdDate = comment.getCreatedDate();
//            this.modifiedDate = Timestamp.valueOf(comment.getModifiedDate());
            this.modifiedDate = comment.getModifiedDate();
            this.nickname = comment.getUser().getNickname();
            this.userId = comment.getUser().getId();
            this.postsId = comment.getPosts().getId();
        }

    }

}

package com.kbsc.kbsc.Application.Dto;

import com.kbsc.kbsc.Entity.Posts;
import com.kbsc.kbsc.Entity.User;
import lombok.*;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

public class PostsDto {

    //게시글의 등록과 수정을 처리할 요청(Request) 클래스*/
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Request{
        private Long id;
        private String title;
        private String writer;
        private String content;
        private Timestamp createdDate, modifiedDate;
        private int view;
        private User user;
        private Long likecount;
        private Long hatecount;
        private String img;


        /* Dto -> Entity */
        public Posts toEntity(){
            Posts posts = Posts.builder()
                    .id(id)
                    .title(title)
                    .writer(writer)
                    .content(content)
                    .view(0)
                    .user(user)
                    .createdDate(createdDate)
                    .modifiedDate(modifiedDate)
                    .likecount(likecount)
                    .hatecount(hatecount)
                    .img(img)
                    .build();
            return posts;
        }
    }

    @Getter
    public static class Response{
        private final Long id;
        private final String title;
        private final String writer;
        private final String content;
        private final Timestamp createdDate, modifiedDate;
        private final int view;
        private final Long userId;
        private final List<CommentDto.Response> comments;

        /*Entity->Dto*/
        public Response(Posts posts){
            this.id = posts.getId();
            this.title = posts.getTitle();
            this.writer = posts.getWriter();
            this.content = posts.getContent();
            this.createdDate = posts.getCreatedDate();
            this.modifiedDate = posts.getModifiedDate();
            this.view = posts.getView();
            this.userId = posts.getUser().getId();
            this.comments = posts.getComments().stream().map(CommentDto.Response::new).collect(Collectors.toList());
        }
    }
}

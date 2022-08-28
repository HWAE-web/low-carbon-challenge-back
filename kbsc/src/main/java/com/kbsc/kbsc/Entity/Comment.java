package com.kbsc.kbsc.Entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.type.TimestampType;

import javax.persistence.*;
import java.sql.Timestamp;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Table(name = "comments")
@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TEXT")
    private String comment; //댓글 내용

    @Column(name = "created_date")
    private Timestamp createdDate;  //생성날짜

    @Column(name = "modified_date")
    private Timestamp modifiedDate;  //수정날짜

    @ManyToOne
    @JoinColumn(name = "posts_id")
    private Posts posts;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user; //작성자

    //댓글 수정
    public void update(String comment){
        this.comment = comment;
    }

}

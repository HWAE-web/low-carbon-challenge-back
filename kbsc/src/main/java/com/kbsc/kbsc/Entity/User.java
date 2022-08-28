package com.kbsc.kbsc.Entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String nickname;
    @Column(length = 100)
    private String password;

    @Column
    private String email;

    @Column
    private Timestamp modifiedDate;

    @Column
    private Timestamp createdDate;

    @Column
    private Long goal;

    @Column
    private Long days;

    @Column
    private Long mileage;

    //회원정보 수정
    public void modify(String nickname, String password){
        this.nickname = nickname;
        this.password = password;
    }


}

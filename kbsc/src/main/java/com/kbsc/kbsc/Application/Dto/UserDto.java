package com.kbsc.kbsc.Application.Dto;

import com.kbsc.kbsc.Entity.User;
import lombok.*;

import javax.persistence.Column;
import java.io.Serializable;
import java.sql.Time;
import java.sql.Timestamp;

public class UserDto {
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Request{
        private Long id;
        private String password;
        private String nickname;
        private String email;
        private Timestamp modifiedDate;
        private Timestamp createdDate;
        private Long goal;
        private Long days;
        private Long mileage;


        /*Dto->Entity*/
        public User toEntity(){
            User user = User.builder()
                    .id(id)
                    .password(password)
                    .nickname(nickname)
                    .email(email)
                    .modifiedDate(modifiedDate)
                    .createdDate(createdDate)
                    .goal(goal)
                    .days(days)
                    .mileage(mileage)
                    .build();
            return user;
        }
    }

    @Getter
    public static class Response implements Serializable {
        private final Long id;
        private final String nickname;
        private final Timestamp modifiedDate;
        private final Long goal;
        private final Long days;
        private final Long mileage;


        /*Entity -> Dto*/
        public Response(User user){
            this.id = user.getId();
            this.nickname = user.getNickname();
            this.modifiedDate = user.getModifiedDate();
            this.days = user.getDays();
            this.goal = user.getGoal();
            this.mileage = user.getMileage();
        }
    }



}

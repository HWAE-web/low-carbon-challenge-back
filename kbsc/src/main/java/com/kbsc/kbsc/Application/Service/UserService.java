package com.kbsc.kbsc.Application.Service;

import com.kbsc.kbsc.Application.Dto.UserDto;
import com.kbsc.kbsc.Entity.User;
import com.kbsc.kbsc.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UserService {
    @Autowired
    private final UserRepository userRepository;



    /*회원가입*/
    @Transactional
    public void userJoin(UserDto.Request dto){
        dto.setPassword(dto.getPassword());
        userRepository.save(dto.toEntity());
    }

    /*회원정보수정*/
    @Transactional
    public void modify(UserDto.Request dto){
        User user = userRepository.findById(dto.toEntity().getId()).orElseThrow(()->new IllegalArgumentException("해당 회원이 존재하지 않습니다."));
        user.modify(dto.getNickname(), dto.getPassword());
    }

}

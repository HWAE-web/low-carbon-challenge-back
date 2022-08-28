package com.kbsc.kbsc.Repository;

import com.kbsc.kbsc.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    /*User GET*/
    User findByNickname(String nickname);

}

package com.kbsc.kbsc.Application.Service;

import com.kbsc.kbsc.Application.Dto.PostsDto;
import com.kbsc.kbsc.Entity.Posts;
import com.kbsc.kbsc.Entity.User;
import com.kbsc.kbsc.Repository.PostsRepository;
import com.kbsc.kbsc.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class PostsService {

    @Autowired
    private final PostsRepository postsRepository;

    @Autowired
    private final UserRepository userRepository;

    /*글작성*/
    @Transactional
    public Long save(PostsDto.Request dto, String nickname){
        //user의 정보를 가져와 dto에 담아준다
        User user = userRepository.findByNickname(nickname);
        dto.setUser(user);
        Posts posts = dto.toEntity();
        postsRepository.save(posts);

        return posts.getId();
    }

    /*글 불러오기*/
    @Transactional(readOnly = true)
    public PostsDto.Response findById(Long id){
        Posts posts = postsRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("해당 게시글이 존재하지 않습니다."+id));

        return new PostsDto.Response(posts);
    }

    /*글 업데이트*/
    @Transactional
    public void update(Long id, PostsDto.Request dto){
        Posts posts = postsRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("해당 게시글이 존재하지 않습니다."+id));
        posts.update(dto.getTitle(), dto.getContent());
    }

    /*글 삭제*/
    @Transactional
    public void delete(Long id){
        Posts posts = postsRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("해당 게시글이 존재하지 않습니다."+id));
        postsRepository.delete(posts);
    }

    /*Views counting*/
    @Transactional
    public int updateView(Long id){
        return postsRepository.updateView(id);
    }

    /*Paging and Sort*/
    @Transactional
    public Page<Posts> pageList(Pageable pageable){
        return postsRepository.findAll(pageable);
    }

}

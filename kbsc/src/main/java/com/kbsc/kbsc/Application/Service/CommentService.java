package com.kbsc.kbsc.Application.Service;

import com.kbsc.kbsc.Application.Dto.CommentDto;
import com.kbsc.kbsc.Entity.Comment;
import com.kbsc.kbsc.Entity.Posts;
import com.kbsc.kbsc.Entity.User;
import com.kbsc.kbsc.Repository.CommentRepository;
import com.kbsc.kbsc.Repository.PostsRepository;
import com.kbsc.kbsc.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CommentService {

    @Autowired
    private final CommentRepository commentRepository;

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final PostsRepository postsRepository;


    /* 댓글작성 */
    @Transactional
    public Long save(Long id, String nickname, CommentDto.Request dto){
        User user = userRepository.findByNickname(nickname);
        Posts posts = postsRepository.findById(id).orElseThrow(()->new IllegalArgumentException("댓글 쓰기 실패: 해당 게시글이 존재하지 않습니다. "+ id));

        dto.setUser(user);
        dto.setPosts(posts);

        Comment comment = dto.toEntity();
        commentRepository.save(comment);

        return comment.getId();
    }

    /* 댓글 읽어오기 */
    @Transactional(readOnly = true)
    public List<CommentDto.Response> findAll(Long id){
        Posts posts = postsRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("해당 게시글이 존재하지 않습니다. id: " + id));

        List<Comment> comments = posts.getComments();
        return comments.stream().map(CommentDto.Response::new).collect(Collectors.toList());


    }

    /* 댓글 업데이트 */
    @Transactional
    public void update(Long id, CommentDto.Request dto){
        Comment comment = commentRepository.findById(id).orElseThrow(()->new IllegalArgumentException("해당 댓글이 존재하지 않습니다." +id));
        comment.update(dto.getComment());
    }

    /*특정 댓글 삭제*/
    @Transactional
    public void delete(Long id){
        Comment comment = commentRepository.findById(id).orElseThrow(()->new IllegalArgumentException("해당 댓글이 존재하지 않습니다. id=" + id));
        commentRepository.delete(comment);
    }
}

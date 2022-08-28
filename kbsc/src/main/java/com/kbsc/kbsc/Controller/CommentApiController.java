package com.kbsc.kbsc.Controller;

import com.kbsc.kbsc.Application.Dto.CommentDto;
import com.kbsc.kbsc.Application.Dto.PostsDto;
import com.kbsc.kbsc.Application.Dto.UserDto;
import com.kbsc.kbsc.Application.Service.CommentService;
import com.kbsc.kbsc.Application.Service.PostsService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/*")
@RestController
@Controller
public class CommentApiController {

    @Autowired
    private final CommentService commentService;

    @Autowired
    private final PostsService postsService;
    /* CREATE */
    @PostMapping("/posts/{id}/comments")
    public ResponseEntity save(@PathVariable Long id, @RequestBody CommentDto.Request dto, UserDto.Response userDto) {
        System.out.println("userDto.getId() = " + userDto.getId());
        //commentDto에서 유저관렬ㄴ된뭔가를여기서받고
        //그걸 수동응로 우리가 userDTo로 넣어주는
        System.out.println("test");
        return ResponseEntity.ok(commentService.save(id, userDto.getNickname(), dto));
    }

    /* READ ok*/
    @GetMapping("/posts/{id}/comments")
    public List<CommentDto.Response> read(@PathVariable Long id) {
        return commentService.findAll(id);
    }

    /* UPDATE ok*/
    @PutMapping("/posts/{id}/comments/{id}")
    public ResponseEntity update(@PathVariable Long id, @RequestBody CommentDto.Request dto) {
        commentService.update(id, dto);
        return ResponseEntity.ok(id);
    }

    /* DELETE ok*/
    @DeleteMapping("/posts/{id}/comments/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        commentService.delete(id);
        return ResponseEntity.ok(id);
    }
}

package com.kbsc.kbsc.Controller;

import com.kbsc.kbsc.Application.Dto.UserDto;
import com.kbsc.kbsc.Application.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/api/*")
@RestController
@Controller
public class UserApiController {

    private final UserService userService;

}

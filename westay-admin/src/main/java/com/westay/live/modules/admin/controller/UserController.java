package com.westay.live.modules.admin.controller;

import com.westay.live.modules.admin.service.UserService;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/user")
@Api(tags = "user manager")
@AllArgsConstructor
public class UserController {

    private UserService userService;



}

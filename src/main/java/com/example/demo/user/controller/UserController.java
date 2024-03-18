package com.example.demo.user.controller;


import com.example.demo.user.dto.UserInfoDto;
import com.example.demo.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("user")
@CrossOrigin(origins = "http://localhost:63342")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/welcome")
    public String welcome() {
        return "Welcome to the unprotected page";
    }

    @GetMapping("/my-info")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public String getUserInfo(Model model, @AuthenticationPrincipal UserDetails userDetails) throws Exception {
        UserInfoDto userInfoDto =  userService.getUserInfo(userDetails.getUsername());
        model.addAttribute("user", userInfoDto);
        return "userProfile";
    }

    @DeleteMapping("{id}")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public void deleteUserAccount(@PathVariable String id) {
        userService.deleteUserAccount(id);
    }

    @PostMapping
    public void createUser(@RequestBody UserInfoDto userInfoDto) {
        userService.createUser(userInfoDto);
    }

    @PatchMapping
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public String updateUser(Model model, @AuthenticationPrincipal UserDetails userDetails, @RequestBody UserInfoDto userInfoDto) throws Exception {
        model.addAttribute("user", userService.updateUser(userDetails.getUsername(), userInfoDto));
        return "userProfile";
    }
}

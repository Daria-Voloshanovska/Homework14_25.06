package ait.cohort5860.accounting.controller;

import ait.cohort5860.accounting.dto.UserRegistrationDto;
import ait.cohort5860.accounting.dto.UserResponseDto;
import ait.cohort5860.accounting.dto.UserUpdateDto;
import ait.cohort5860.accounting.service.UserAccountService;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/account")
public class UserAccountController {
    private final UserAccountService userService;


    public UserAccountController(UserAccountService userService) {
        this.userService = userService;
    }
    @PostMapping("/register")
    public UserResponseDto register(@RequestBody UserRegistrationDto dto) {
        return userService.registerUser(dto);
    }
    @PostMapping
    public UserResponseDto login(){
        String login = SecurityContextHolder.getContext().getAuthentication().getName();
        return userService.getUserDetails(login);
    }

    @GetMapping("/user/{login}")
    public UserResponseDto getUser(@PathVariable String login) {
        return userService.getUserDetails(login);
    }
    @PatchMapping("/user/{login}")
    public UserResponseDto updateUser(
            @PathVariable String login,
            @RequestBody UserUpdateDto dto) {
        String currentUser = SecurityContextHolder.getContext().getAuthentication().getName();
        return userService.updateUser(login, dto, currentUser);
    }
    @DeleteMapping("/user/{login}")
    public UserResponseDto deleteUser(@PathVariable String login) {
        String currentUser = SecurityContextHolder.getContext().getAuthentication().getName();
        return userService.deleteUser(login, currentUser);
    }
    @PatchMapping("/user/{login}/role/{role}")
    public UserResponseDto addRole(
            @PathVariable String login,
            @PathVariable String role) {
        String currentUser = SecurityContextHolder.getContext().getAuthentication().getName();
        return userService.addRole(login, role, currentUser);
    }
    @DeleteMapping("/user/{login}/role/{role}")
    public UserResponseDto removeRole(
            @PathVariable String login,
            @PathVariable String role) {
        String currentUser = SecurityContextHolder.getContext().getAuthentication().getName();
        return userService.removeRole(login, role, currentUser);
    }
    @PatchMapping("/password")
    public void changePassword(@RequestHeader("X-Password") String newPassword) {
        String login = SecurityContextHolder.getContext().getAuthentication().getName();
        userService.changePassword(login, newPassword);
    }
}

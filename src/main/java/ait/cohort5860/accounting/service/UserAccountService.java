package ait.cohort5860.accounting.service;

import ait.cohort5860.accounting.dto.UserRegistrationDto;
import ait.cohort5860.accounting.dto.UserResponseDto;
import ait.cohort5860.accounting.dto.UserUpdateDto;

public interface UserAccountService {

    UserResponseDto registerUser(UserRegistrationDto registrationDto);

    UserResponseDto getUserDetails(String login);

    UserResponseDto updateUser(String login, UserUpdateDto userUpdateDto, String currentUserLogin);

    UserResponseDto deleteUser(String login, String currentUserLogin);

    UserResponseDto addRole(String login, String role, String currentUserLogin);

    UserResponseDto removeRole(String login, String role, String currentUserLogin);

    void changePassword(String login, String newPassword);
}

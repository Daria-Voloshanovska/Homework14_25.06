package ait.cohort5860.accounting.service;

import ait.cohort5860.accounting.dto.UserRegistrationDto;
import ait.cohort5860.accounting.dto.UserResponseDto;
import ait.cohort5860.accounting.dto.UserUpdateDto;
import org.springframework.stereotype.Service;

@Service
public class UserAccountServiceImpl implements UserAccountService {
    @Override
    public UserResponseDto registerUser(UserRegistrationDto registrationDto) {
        return null;
    }

    @Override
    public UserResponseDto getUserDetails(String login) {
        return null;
    }

    @Override
    public UserResponseDto updateUser(String login, UserUpdateDto userUpdateDto, String currentUserLogin) {
        return null;
    }

    @Override
    public UserResponseDto deleteUser(String login, String currentUserLogin) {
        return null;
    }

    @Override
    public UserResponseDto addRole(String login, String role, String currentUserLogin) {
        return null;
    }

    @Override
    public UserResponseDto removeRole(String login, String role, String currentUserLogin) {
        return null;
    }

    @Override
    public void changePassword(String login, String newPassword) {

    }
}

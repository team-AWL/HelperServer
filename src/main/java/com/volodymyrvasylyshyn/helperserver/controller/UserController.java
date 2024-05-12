package com.volodymyrvasylyshyn.helperserver.controller;

import com.volodymyrvasylyshyn.helperserver.dto.EmailDto;
import com.volodymyrvasylyshyn.helperserver.dto.user.UpdateEmailDto;
import com.volodymyrvasylyshyn.helperserver.dto.user.UpdateOptionalUserInfoDto;
import com.volodymyrvasylyshyn.helperserver.dto.user.UpdatePasswordDto;
import com.volodymyrvasylyshyn.helperserver.dto.user.UserDto;
import com.volodymyrvasylyshyn.helperserver.mapper.UpdateOptionalUserInfoMapper;
import com.volodymyrvasylyshyn.helperserver.model.User;
import com.volodymyrvasylyshyn.helperserver.request.ResetPasswordRequest;
import com.volodymyrvasylyshyn.helperserver.response.ApiResponse;
import com.volodymyrvasylyshyn.helperserver.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final UpdateOptionalUserInfoMapper updateOptionalUserInfoMapper;

    @GetMapping("/")
    public ResponseEntity<UserDto> getCurrentUser(Principal principal){
        UserDto userDto = userService.getCurrentUserDto(principal);
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }
    @PostMapping("/optionalInfo")
    public ResponseEntity<Object> updateOptionalInfoUser(@Valid @RequestBody UpdateOptionalUserInfoDto updateOptionalUserInfoDto, Principal principal){
        User user = userService.updateOptionalInfoUser(updateOptionalUserInfoDto,principal);
        UpdateOptionalUserInfoDto userUpdated = updateOptionalUserInfoMapper.userToUserDTO(user);
        return new ResponseEntity<>(userUpdated, HttpStatus.OK);
    }

    @PostMapping("/email")
    public ResponseEntity<Object> updateEmail(@Valid@RequestBody UpdateEmailDto updateEmailDto,Principal principal){
        String email = userService.updateEmail(updateEmailDto,principal);
        return new ResponseEntity<>(email, HttpStatus.OK);

    }
    @PostMapping("/password")
    public ResponseEntity<Object> updatePassword(@Valid@RequestBody UpdatePasswordDto updatePasswordDto,  Principal principal){
        String response = userService.updatePassword(updatePasswordDto,principal);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/forgot")
    public ResponseEntity<ApiResponse> forgotPassword(@RequestBody EmailDto emailDto){
        userService.forgotPassword(emailDto.email());
        return ResponseEntity.ok(new ApiResponse(true,"Reset url in your email"));
    }
    @PostMapping("/forgot/setNewPassword")
    public ResponseEntity<?> setNewPassword(@RequestBody ResetPasswordRequest resetPasswordRequest){
        User user = userService.getByResetToken(resetPasswordRequest.token());
        userService.updateForgotPassword(user, resetPasswordRequest.password());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

package com.volodymyrvasylyshyn.helperserver.controller;

import com.volodymyrvasylyshyn.helperserver.dto.EmailDto;
import com.volodymyrvasylyshyn.helperserver.dto.user.UpdateEmailDto;
import com.volodymyrvasylyshyn.helperserver.dto.user.UpdateOptionalUserInfoDto;
import com.volodymyrvasylyshyn.helperserver.dto.user.UpdatePasswordDto;
import com.volodymyrvasylyshyn.helperserver.dto.user.UserDto;
import com.volodymyrvasylyshyn.helperserver.facade.UpdateOptionalUserInfoFacade;
import com.volodymyrvasylyshyn.helperserver.model.User;
import com.volodymyrvasylyshyn.helperserver.request.ResetPasswordRequest;
import com.volodymyrvasylyshyn.helperserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

@RestController
@RequestMapping("api/v1/user")
public class UserController {
    private final UserService userService;
    private final UpdateOptionalUserInfoFacade updateOptionalUserInfoFacade;
    @Autowired
    public UserController(UserService userService, UpdateOptionalUserInfoFacade updateOptionalUserInfoFacade ) {
        this.userService = userService;
        this.updateOptionalUserInfoFacade = updateOptionalUserInfoFacade;
    }

    @GetMapping("/")
    public ResponseEntity<UserDto> getCurrentUser(Principal principal){
        UserDto userDto = userService.getCurrentUserDto(principal);
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }
    @PostMapping("/update/optionalInfo")
    public ResponseEntity<Object> updateOptionalInfoUser(@Valid @RequestBody UpdateOptionalUserInfoDto updateOptionalUserInfoDto, Principal principal){
        User user = userService.updateOptionalInfoUser(updateOptionalUserInfoDto,principal);
        UpdateOptionalUserInfoDto userUpdated = updateOptionalUserInfoFacade.userToUserDTO(user);
        return new ResponseEntity<>(userUpdated, HttpStatus.OK);
    }

    @PostMapping("/update/email")
    public ResponseEntity<Object> updateEmail(@Valid@RequestBody UpdateEmailDto updateEmailDto,Principal principal){
        String email = userService.updateEmail(updateEmailDto,principal);
        return new ResponseEntity<>(email, HttpStatus.OK);

    }
    @PostMapping("/update/password")
    public ResponseEntity<Object> updatePassword(@Valid@RequestBody UpdatePasswordDto updatePasswordDto,  Principal principal){
        String response = userService.updatePassword(updatePasswordDto,principal);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/forgot")
    public ResponseEntity<?> forgotPassword(@RequestBody EmailDto emailDto){
        userService.forgotPassword(emailDto.getEmail());
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PostMapping("/forgot/setNewPassword")
    public ResponseEntity<?> setNewPassword(@RequestBody ResetPasswordRequest resetPasswordRequest){
        User user = userService.getByResetToken(resetPasswordRequest.getToken());
        userService.updateForgotPassword(user, resetPasswordRequest.getPassword());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

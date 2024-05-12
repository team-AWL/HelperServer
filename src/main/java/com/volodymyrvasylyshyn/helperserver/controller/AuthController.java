package com.volodymyrvasylyshyn.helperserver.controller;

import com.volodymyrvasylyshyn.helperserver.request.LoginRequest;
import com.volodymyrvasylyshyn.helperserver.request.SignupRequest;
import com.volodymyrvasylyshyn.helperserver.response.ApiResponse;
import com.volodymyrvasylyshyn.helperserver.response.JWTTokenSuccessResponse;
import com.volodymyrvasylyshyn.helperserver.response.MessageResponse;
import com.volodymyrvasylyshyn.helperserver.security.JWTTokenProvider;
import com.volodymyrvasylyshyn.helperserver.security.SecurityConstants;
import com.volodymyrvasylyshyn.helperserver.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


@RestController
@RequestMapping("/api/v1/auths")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    private final AuthenticationManager authenticationManager;

    private final JWTTokenProvider jwtTokenProvider;

    @PostMapping("/signin")
    public ResponseEntity<Object> authenticateUser(@RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginRequest.getEmail()
                , loginRequest.getPassword()

        ));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = SecurityConstants.TOKEN_PREFIX + jwtTokenProvider.generateToken(authentication);
        return ResponseEntity.ok(new JWTTokenSuccessResponse(true, jwt));
    }

    @PostMapping("/signup")
    public ResponseEntity<ApiResponse> registerUser(@Valid @RequestBody SignupRequest signupRequest) {

        userService.createUser(signupRequest);
        return ResponseEntity.ok(new ApiResponse(true, "User registered successfully"));

    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout() {
        SecurityContextHolder.clearContext();
        return new ResponseEntity<>(HttpStatus.OK);
    }

}

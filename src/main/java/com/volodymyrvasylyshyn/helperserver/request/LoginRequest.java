package com.volodymyrvasylyshyn.helperserver.request;

import lombok.Data;

import javax.validation.constraints.NotEmpty;


@Data
public class LoginRequest {
    @NotEmpty(message = "Email or email cannot be empty")
    private String email;
    @NotEmpty(message = "Password cannot be empty")
    private String password;

}

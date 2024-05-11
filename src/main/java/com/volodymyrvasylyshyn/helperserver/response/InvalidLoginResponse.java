package com.volodymyrvasylyshyn.helperserver.response;

import lombok.Getter;

@Getter
public class InvalidLoginResponse {
    private final String email;
    private final String password;

    public InvalidLoginResponse() {
        this.email = "Invalid Email";
        this.password = "Invalid Password";
    }
}

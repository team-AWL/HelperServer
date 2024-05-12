package com.volodymyrvasylyshyn.helperserver.response;

import lombok.Data;

@Data
public class InvalidLoginResponse {
    private String email = "Invalid Email";
    private String password = "Invalid Password";
}

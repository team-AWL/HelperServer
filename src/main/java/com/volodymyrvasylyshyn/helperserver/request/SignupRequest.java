package com.volodymyrvasylyshyn.helperserver.request;


import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
public class SignupRequest {

    @Email(message = "It should have email format")
    @NotBlank(message = "User email is required")
    private String email;
    private String name;
    private Boolean isVolunteer;
    @NotEmpty(message = "Password is required")
    @Size(min = 4)
    private String password;

}

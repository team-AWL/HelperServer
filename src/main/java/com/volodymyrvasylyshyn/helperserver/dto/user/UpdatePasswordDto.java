package com.volodymyrvasylyshyn.helperserver.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdatePasswordDto {
    private String newPassword;
    private String oldPassword;
}

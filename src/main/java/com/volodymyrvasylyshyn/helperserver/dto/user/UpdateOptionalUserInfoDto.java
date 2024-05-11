package com.volodymyrvasylyshyn.helperserver.dto.user;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateOptionalUserInfoDto {
    private String phoneNumber;
    private String imageUrl;
    private String name;
}

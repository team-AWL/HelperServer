package com.volodymyrvasylyshyn.helperserver.mapper;



import com.volodymyrvasylyshyn.helperserver.dto.user.UpdateOptionalUserInfoDto;
import com.volodymyrvasylyshyn.helperserver.model.User;
import org.springframework.stereotype.Component;

@Component
public class UpdateOptionalUserInfoMapper {

    public UpdateOptionalUserInfoDto userToUserDTO(User user){
        UpdateOptionalUserInfoDto updateOptionalUserInfoDto = new UpdateOptionalUserInfoDto();
        updateOptionalUserInfoDto.setImageUrl(user.getImageUrl());
        updateOptionalUserInfoDto.setPhoneNumber(user.getPhoneNumber());
        updateOptionalUserInfoDto.setName(user.getName());
        return updateOptionalUserInfoDto;
    }
}

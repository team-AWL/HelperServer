package com.volodymyrvasylyshyn.helperserver.facade;



import com.volodymyrvasylyshyn.helperserver.dto.user.UpdateOptionalUserInfoDto;
import com.volodymyrvasylyshyn.helperserver.model.User;
import org.springframework.stereotype.Component;

@Component
public class UpdateOptionalUserInfoFacade {

    public UpdateOptionalUserInfoDto userToUserDTO(User user){
        UpdateOptionalUserInfoDto updateOptionalUserInfoDto = new UpdateOptionalUserInfoDto();
        updateOptionalUserInfoDto.setImageUrl(user.getImageUrl());
        updateOptionalUserInfoDto.setPhoneNumber(user.getPhoneNumber());
        updateOptionalUserInfoDto.setName(user.getName());
        return updateOptionalUserInfoDto;
    }
}

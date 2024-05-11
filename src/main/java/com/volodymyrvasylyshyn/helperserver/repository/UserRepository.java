package com.volodymyrvasylyshyn.helperserver.repository;


import com.volodymyrvasylyshyn.helperserver.dto.user.UserDto;
import com.volodymyrvasylyshyn.helperserver.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findUserById(Long id);
    Optional<User> findUserByEmail(String email);
    @Query("select new com.volodymyrvasylyshyn.helperserver.dto.user.UserDto(u.id, u.email, u.phoneNumber, u.imageUrl, u.name, u.isHelper) from User as u where u.email = :email")
    Optional<UserDto> findUserDtoByEmail(@Param("email") String email);


}

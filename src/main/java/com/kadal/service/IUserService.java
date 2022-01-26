package com.kadal.service;

import com.kadal.dto.UserDTO;
import com.kadal.entity.CustomUser;
import org.springframework.stereotype.Service;

import java.util.List;

public interface IUserService {
    public List<UserDTO> getUsers();

    public UserDTO getUser(Long id);

    public UserDTO createUser(CustomUser user);

    public UserDTO updateUser(CustomUser user);

    public Long deleteUser(Long id);
}

package com.kadal.service;

import com.kadal.dto.UserDTO;
import com.kadal.entity.ApplicationUser;

import java.util.List;

public interface IUserService {
    public List<UserDTO> getUsers();

    public UserDTO getUser(Long id);

    public UserDTO createUser(ApplicationUser user);

    public UserDTO updateUser(ApplicationUser user);

    public Long deleteUser(Long id);
}

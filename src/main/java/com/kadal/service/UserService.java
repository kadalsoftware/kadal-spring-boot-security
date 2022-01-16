package com.kadal.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kadal.dto.UserDTO;
import com.kadal.entity.ApplicationUser;
import com.kadal.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class UserService implements IUserService{

    private final UserRepository userRepository;
    final ModelMapper modelMapper = new ModelMapper();

    @Override
    public List<UserDTO> getUsers(){
       List<ApplicationUser> users = userRepository.findAll();
            // user here is a prepopulated User instance
        List<UserDTO> listUserDto = users
                .stream()
                .map(user -> modelMapper.map(user, UserDTO.class))
                .collect(Collectors.toList());
        return  listUserDto;
    }

    @Override
    public UserDTO getUser(Long id) {
        ApplicationUser user =userRepository.findById(id).get();
        return modelMapper.map(user,UserDTO.class);
    }

    @Override
    public UserDTO createUser(ApplicationUser user){
        ApplicationUser responseUser = userRepository.save(user);
       return modelMapper.map(responseUser,UserDTO.class);
    }

    @Override
    public UserDTO updateUser(ApplicationUser user){
        Optional<ApplicationUser> optUser = userRepository.findById(user.getId());
        if (optUser.isPresent()) {
            ApplicationUser responseUser = userRepository.save(user);
            return modelMapper.map(responseUser,UserDTO.class);
        }
        else
            return null;

    }

    @Override
    public Long deleteUser(Long id) {
        Optional<ApplicationUser> optUser = userRepository.findById(id);
        if (optUser.isPresent()) {
            userRepository.deleteById(id);
            return id;
        }
        else
            return null;
    }
}

package com.kadal.service;

import com.kadal.dto.UserDTO;
import com.kadal.entity.CustomUser;
import com.kadal.entity.Role;
import com.kadal.repository.RoleRepository;
import com.kadal.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class UserService implements IUserService, UserDetailsService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        CustomUser user = userRepository.findByName(username).get();
        UserBuilder builder = User.withUsername(username);
        builder.password(user.getPassword());
        builder.authorities(getGrantedAuthorities(user));
        return builder.build();
    }

    private Set<SimpleGrantedAuthority> getGrantedAuthorities(CustomUser customUser) {
        Set<SimpleGrantedAuthority> permissions = new LinkedHashSet<>();
        customUser.getRoles().forEach(role -> {
            role.getPermissions().forEach(
                    permission -> {
                        permissions.add(new SimpleGrantedAuthority(permission.getPermission()));
                    }
            );
        });
        return permissions;
    }


    @Override
    public List<UserDTO> getUsers() {
        List<CustomUser> users = userRepository.findAll();
        // user here is a prepopulated User instance
        List<UserDTO> listUserDto = users
                .stream()
                .map(user -> modelMapper.map(user, UserDTO.class))
                .collect(Collectors.toList());
        return listUserDto;
    }

    @Override
    public UserDTO getUser(Long id) {
        CustomUser user = userRepository.findById(id).get();
        return modelMapper.map(user, UserDTO.class);
    }

    @Override
    public UserDTO createUser(CustomUser user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Set<Role> setRole = new LinkedHashSet<>();
        user.getRoles().forEach((Role role) -> {
            setRole.add(roleRepository.findById(role.getId()).get());
        });
        user.setRoles(setRole);
        CustomUser responseUser = userRepository.save(user);
        return modelMapper.map(responseUser, UserDTO.class);
    }

    @Override
    public UserDTO updateUser(CustomUser user) {
        Optional<CustomUser> optUser = userRepository.findById(user.getId());
        if (optUser.isPresent()) {
            CustomUser responseUser = userRepository.save(user);
            return modelMapper.map(responseUser, UserDTO.class);
        } else
            return null;

    }

    @Override
    public Long deleteUser(Long id) {
        Optional<CustomUser> optUser = userRepository.findById(id);
        if (optUser.isPresent()) {
            userRepository.deleteById(id);
            return id;
        } else
            return null;
    }
}

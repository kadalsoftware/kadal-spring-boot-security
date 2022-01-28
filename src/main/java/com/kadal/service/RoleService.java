package com.kadal.service;

import com.kadal.entity.Permission;
import com.kadal.entity.Role;
import com.kadal.repository.PermissionRepository;
import com.kadal.repository.RoleRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@AllArgsConstructor
public class RoleService implements IRoleService{

    private final RoleRepository roleRepository;
    private final PermissionRepository permissionRepository;
    private final ModelMapper modelMapper = new ModelMapper();


    @Override
    public List<Role> getRoles() {
        return roleRepository.findAll();
    }

    @Override
    public Role getRole(Long id) {
        return roleRepository.findById(id).get();
    }

    @Override
    public Role createRole(Role role) {
        Set<Permission> setPermission = new LinkedHashSet<>();
        role.getPermissions().forEach((Permission permission)->{
            setPermission.add(permissionRepository.findById(permission.getId()).get());
        });
        role.setPermissions(setPermission);
        return roleRepository.save(role);
    }

    @Override
    public Role updateRole(Role role) {
        Optional<Role> optRole = roleRepository.findById(role.getId());
        if (optRole.isPresent()) {
            return roleRepository.save(role);
        }
        else
            return null;
    }

    @Override
    public Long deleteRole(Long id) {
        Optional<Role> optRole = roleRepository.findById(id);
        if (optRole.isPresent()) {
            roleRepository.deleteById(id);
            return id;
        }
        else
            return null;
    }
}


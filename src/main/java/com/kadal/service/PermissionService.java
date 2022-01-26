package com.kadal.service;

import com.kadal.dto.UserDTO;
import com.kadal.entity.CustomUser;
import com.kadal.entity.Permission;
import com.kadal.repository.PermissionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PermissionService implements IPermissionService{

    private final PermissionRepository permissionRepository;


    @Override
    public List<Permission> getPermissions() {
        return permissionRepository.findAll();
    }

    @Override
    public Permission getPermission(Long id) {
        return permissionRepository.findById(id).get();
    }

    @Override
    public Permission createPermission(Permission permission) {
        return permissionRepository.save(permission);
    }

    @Override
    public Permission updatePermission(Permission permission) {
        Optional<Permission> optPermission = permissionRepository.findById(permission.getId());
        if (optPermission.isPresent()) {
            Permission responsePermission = permissionRepository.save(permission);
            return responsePermission;
        }
        else
            return null;
    }

    @Override
    public Long deletePermission(Long id) {
        Optional<Permission> optPermission = permissionRepository.findById(id);
        if (optPermission.isPresent()) {
                permissionRepository.deleteById(id);
                return id;
            }
            else
                return null;
    }
}

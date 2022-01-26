package com.kadal.service;

import com.kadal.dto.UserDTO;
import com.kadal.entity.CustomUser;
import com.kadal.entity.Permission;

import java.util.List;

public interface IPermissionService {
    public List<Permission> getPermissions();

    public Permission getPermission(Long id);

    public Permission createPermission(Permission permission);

    public Permission updatePermission(Permission permission);

    public Long deletePermission(Long id);


}

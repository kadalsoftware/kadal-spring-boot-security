package com.kadal.service;

import com.kadal.entity.Role;

import java.util.List;

public interface IRoleService {

    public List<Role> getRoles();

    public Role getRole(Long id);

    public Role createRole(Role role);

    public Role updateRole(Role role);

    public Long deleteRole(Long id);

}

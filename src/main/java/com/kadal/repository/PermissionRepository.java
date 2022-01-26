package com.kadal.repository;

import com.kadal.entity.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissionRepository extends JpaRepository <Permission,Long> {
}

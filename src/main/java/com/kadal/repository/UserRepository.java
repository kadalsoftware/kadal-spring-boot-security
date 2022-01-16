package com.kadal.repository;

import com.kadal.entity.ApplicationUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<ApplicationUser,Long> {

}

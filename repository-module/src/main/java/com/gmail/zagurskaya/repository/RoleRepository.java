package com.gmail.zagurskaya.repository;

import com.gmail.zagurskaya.repository.model.Role;
import com.gmail.zagurskaya.repository.model.RoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(RoleEnum roleName);
}
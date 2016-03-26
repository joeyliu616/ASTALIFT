package com.aoe.astalift.account.repository;

import com.aoe.astalift.account.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by joey on 16-3-19.
 */
@Repository
public interface UserRoleRepository extends JpaRepository<UserRole,Integer>{
    UserRole findByName(String name);
}

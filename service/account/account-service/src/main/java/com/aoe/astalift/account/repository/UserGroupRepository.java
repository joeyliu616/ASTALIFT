package com.aoe.astalift.account.repository;

import com.aoe.astalift.account.entity.UserGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by joey on 16-3-19.
 */
@Repository
public interface UserGroupRepository extends JpaRepository<UserGroup, Integer> {
    UserGroup findByName(String name);
}

package com.aoe.astalift.account.repository;

import com.aoe.astalift.account.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by joey on 16-3-19.
 */
@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
}

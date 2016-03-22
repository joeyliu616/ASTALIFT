package com.aoe.astalift.account.repository;

import com.aoe.astalift.account.entity.SignIn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by joey on 16-3-19.
 */
@Repository
public interface SignInRepository extends JpaRepository<SignIn, Integer> {
    SignIn findByMobile(String mobile);
    SignIn findByEmail(String email);
    SignIn findByUserName(String userName);
}

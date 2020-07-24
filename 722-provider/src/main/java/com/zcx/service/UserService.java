package com.zcx.service;

import com.zcx.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface UserService extends JpaRepository<User,Integer>, JpaSpecificationExecutor<User> {
}

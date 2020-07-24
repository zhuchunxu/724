package com.zcx.service;

import com.zcx.entity.Like;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface LikesService extends JpaRepository<Like,Integer>, JpaSpecificationExecutor<Like> {
}

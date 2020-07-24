package com.zcx.service;

import com.zcx.entity.Dept;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface DeptService extends JpaRepository<Dept,Integer>, JpaSpecificationExecutor<Dept> {
}

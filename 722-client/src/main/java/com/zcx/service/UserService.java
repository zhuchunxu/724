package com.zcx.service;

import com.zcx.entity.*;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

// ,fallback = UserServiceFailBack.class
@FeignClient(value = "jpa-provider")
public interface UserService {

    @RequestMapping("/list")
    MyPageImpl<User> list(@RequestBody UserVo userVo);


    @RequestMapping("/del")
    public boolean del(@RequestParam("id")Integer id);

    @RequestMapping("/saveOrUpdate")
    public boolean saveOrUpdate(@RequestBody User user);

    @RequestMapping("depts")
    public List<Dept> depts();

    @RequestMapping("likes")
    public List<Like> likes();

}

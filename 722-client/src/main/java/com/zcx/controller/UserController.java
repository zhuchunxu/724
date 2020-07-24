package com.zcx.controller;

import com.zcx.entity.*;
import com.zcx.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("user")
@CrossOrigin
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping("list")
    public MyPageImpl<User> list(UserVo userVo){
        System.out.print(" 消费者参数是  " + userVo);
        MyPageImpl<User> page = userService.list(userVo);
        System.out.println(page.getNumber());
         System.out.println ("  消费者 ========== 已经获取数据了。。。。。。。。。。。。");
        page.getContent().iterator().forEachRemaining(x->{System.out.println("消费者  x is " + x);});

        return page;
    }
    @RequestMapping("del")
    public boolean del(Integer id){
        return  userService.del(id);
    }

    @RequestMapping("saveOrUpdate")
    public boolean saveOrUpdate(@RequestBody User user){
        System.out.println(user);
        return  userService.saveOrUpdate(user);
    }

    @RequestMapping("depts")
    public List<Dept> depts(){
        return userService.depts();
    }
    @RequestMapping("likes")
    public List<Like> likes(){
        return userService.likes();
    }
}

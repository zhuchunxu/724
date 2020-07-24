package com.zcx.controller;

import com.zcx.entity.*;
import com.zcx.service.DeptService;
import com.zcx.service.LikesService;
import com.zcx.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    DeptService deptService;
    @Autowired
    LikesService likeService;
    //条件查询+分页
    @RequestMapping("/list")
    public MyPageImpl<User> list(@RequestBody UserVo userVo) {

        Specification userVoSpecification = new Specification<UserVo>() {
            @Override
            public Predicate toPredicate(Root root, CriteriaQuery criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> list =new ArrayList();
                if(userVo.getUsername()!=null && !userVo.getUsername().equals("")){
                    Predicate predicate = criteriaBuilder.like(root.get("username"),"%"+ userVo.getUsername()+"%");
                    list.add(predicate);
                }

                Predicate[] objects =  list.toArray(new Predicate[list.size()]);
                Predicate all = criteriaBuilder.and(objects);
                return all;
            }
        };


        // 生成分页的对象
        Pageable pageable = PageRequest.of(userVo.getPage()-1,userVo.getPageSize());
        Page page = userService.findAll(userVoSpecification,pageable);

        System.err.println(page.getNumber());
        return new MyPageImpl(page);
    }

    @RequestMapping("del")
    public boolean del(@RequestParam("id")Integer id){
        userService.deleteById(id);
        return true;
    }

    @RequestMapping("saveOrUpdate")
    public boolean saveOrUpdate(@RequestBody User user){
        System.out.println(user);
        userService.saveAndFlush(user);
        return true;
    }

    @RequestMapping("depts")
    public List<Dept> depts(){
        return deptService.findAll();
    }
    @RequestMapping("likes")
    public List<Like> likes(){
        return likeService.findAll();
    }
}

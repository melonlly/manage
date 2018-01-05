package com.manage.controller;

import com.manage.user.User;
import com.manage.user.dao.UserDao;
import org.hibernate.HibernateError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLDataException;
import java.sql.SQLException;

@Controller
public class UserController {

    @Autowired
    public UserDao userDao;

    @RequestMapping("/addUser")
    public String addUser(){
       return "addUser";
    }

    @Modifying
    @RequestMapping("saveUser")
    @ResponseBody
    public String saveUser(User user){
        String result = "";
        try{
            userDao.save(user);
            result = "success!";
        }catch(HibernateError e){
            result = "failed!";
            e.printStackTrace();
        }
         return result;
    }

    @RequestMapping("/updateUser")
    public String updateFrontUser(){
        return "updateUser";
    }

    @Modifying
    @RequestMapping("doUpdateUser")
    @ResponseBody
    public String updateUser(User user){
        String result = "";
        User userEntity = userDao.findAllById(user.getId());
        if(userEntity != null){
            userEntity.setName(user.getName());
            try{
                userDao.save(userEntity);
                result =  "数据修改成功";
            }catch (HibernateError e){
                result = "操作失败";
                e.printStackTrace();
            }
        }
        return result;
    }
}

package com.manage.user.dao;

import com.manage.user.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserDao extends CrudRepository<User,Integer> {

    //根据id查找用户的信息
    User findAllById(int id);

    //查找所有用户的信息
    List<User> findAll();
}

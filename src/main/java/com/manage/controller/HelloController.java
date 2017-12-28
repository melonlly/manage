package com.manage.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.manage.user.User;
import com.manage.user.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by melon on 2017/12/20.
 */
@RestController
public class HelloController {

    //自动注入
    @Autowired
    public UserDao userDao;

	@RequestMapping(value = "/hello/{name}", method = RequestMethod.GET)
	public String hello(String name){
        String json = "";
        //根据id获取数据
        //List<User> list= userDao.findAllById(1);
        //获取数据库user表所有数据
        List<User> list= userDao.findAll();
        if(list != null && list.size() > 0){
            json =  JSON.toJSONString(list);
        }
	    return json;
	}

	@RequestMapping(value = "/json", method = RequestMethod.GET)
	public Map<String,Object> json(){
	    Map<String,Object> map = new HashMap<String,Object>();
	    map.put("name","dsdas111");
	    map.put("das","dasdas222");
	    map.put("dsada","dsada333");
	    return map;
    }
}

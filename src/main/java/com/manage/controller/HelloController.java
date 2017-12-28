package com.manage.controller.controller;

import com.manage.controller.user.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by melon on 2017/12/20.
 */
@RestController
@RequestMapping("user")
public class HelloController {

    @Autowired
    public UserDao userDao;

	@RequestMapping(value = "/hello/{name}", method = RequestMethod.GET)
	public String hello(String name){
	    return "";
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

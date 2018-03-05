package com.manage.controller;

import com.manage.entity.Result;
import com.manage.entity.User;
import com.manage.repository.UserRepository;
import com.manage.service.UserService;
import com.manage.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/user")
public class UserController {

	@Resource
	UserRepository userRepository;
	@Autowired
	UserService userService;

	@RequestMapping("/list")
	@ResponseBody
	public Result findUsers(@RequestBody Map<String, Object> req) {
		int page = Integer.parseInt(req.get("page").toString());
		int size = Integer.parseInt(req.get("size").toString());
		String name = req.get("name").toString();
		String relation = req.get("relation").toString();
		String is_admin = req.get("is_admin").toString();
		String status = req.get("status").toString();
		String role = req.get("role").toString();
		User user = new User();
		if(name != null && name != ""){
			user.setName(name);
		}
		if(relation != null && relation != ""){
			user.setRelation(relation);
		}
		if(is_admin != null && is_admin != ""){
			user.setIs_admin(Integer.parseInt(is_admin));
		}
		if(status != null && status != ""){
			user.setStatus(Integer.parseInt(status));
		}
		if(role != null && role != ""){
			user.setRole(Integer.parseInt(role));
		}

		System.out.println(page + " " + size);
		System.out.println(user.toString());

		Result result = new Result();
		try {
			// 分页默认从0页开始
			Page<User> users = userService.findUsers(page - 1, size, user);
			List<User> list = users.getContent();
			List<Map> resultList = new ArrayList<Map>();
			// 去除敏感字段 - 密码
			for (User u : list){
				Map map = new HashMap();
				map.put("id", u.getId());
				map.put("name", u.getName());
				map.put("create_time", u.getCreate_time());
				map.put("is_admin", u.getIs_admin());
				map.put("status", u.getStatus());
				map.put("role", u.getRole());
				map.put("relation", u.getRelation());
				map.put("phone", u.getPhone());
				resultList.add(map);
			}
			result.setCode(Constants.SUCCESS);
			result.setData(resultList);
			result.setTotal((int)users.getTotalElements());
			result.setPages(users.getTotalPages());
			result.setPage(page);
			result.setSize(size);
		} catch (Exception e) {
			result.setCode(Constants.FAIL);
			result.setError(e.getMessage());
		}
		return result;
	}

	@Modifying
	@RequestMapping("/add")
	@ResponseBody
	public Result saveUser(@RequestBody Map<String, Object> req) {
		Result result = new Result();
		String name = req.get("name").toString();
		User m_user = new User();
		m_user.setName(name);
		Boolean exist = userRepository.exists(Example.of(m_user));
		if (exist){
			result.setCode(Constants.FAIL);
			result.setError("该用户已经存在！");
			return result;
		}
		String password = req.get("password").toString();
		String relation = req.get("relation").toString();
		String phone = req.get("phone").toString();
		String create_time = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date());
		String is_admin = req.get("is_admin").toString();
		String status = req.get("status").toString();
		String role = req.get("role").toString();
		User user = new User();
		user.setName(name);
		user.setPassword(password);
		user.setRelation(relation);
		user.setPhone(phone);
		user.setCreate_time(create_time);
		user.setIs_admin(Integer.parseInt(is_admin));
		user.setStatus(Integer.parseInt(status));
		user.setRole(Integer.parseInt(role));
		try {
			userRepository.save(user);
			result.setCode(Constants.SUCCESS);
		} catch (Exception e) {
			result.setCode(Constants.FAIL);
			result.setError(e.getMessage());
		}
		return result;
	}

	@Modifying
	@RequestMapping("/update")
	@ResponseBody
	public Result updateUser(@RequestBody Map<String, Object> req) {
		Result result = new Result();
		Integer id = Integer.parseInt(req.get("id").toString());
		String name = req.get("name").toString();
		String password = req.get("password").toString();
		String relation = req.get("relation").toString();
		String phone = req.get("phone").toString();
		String is_admin = req.get("is_admin").toString();
		String status = req.get("status").toString();
		String role = req.get("role").toString();
		User m_user = new User();
		m_user.setId(id);
		User userEntity = userRepository.findOne(Example.of(m_user));
		if (userEntity != null) {
			try {
				User user = new User();
				user.setId(id);
				user.setName(name);
				user.setCreate_time(userEntity.getCreate_time());
				user.setPassword(password);
				user.setRelation(relation);
				user.setPhone(phone);
				user.setIs_admin(Integer.parseInt(is_admin));
				user.setStatus(Integer.parseInt(status));
				user.setRole(Integer.parseInt(role));
				userRepository.save(user);
				result.setCode(Constants.SUCCESS);
			} catch (Exception e) {
				result.setCode(Constants.FAIL);
				result.setError(e.getMessage());
			}
		}else {
			result.setCode(Constants.FAIL);
			result.setError("该用户不存在！");
		}
		return result;
	}

	@Modifying
	@RequestMapping("/remove")
	@ResponseBody
	public Result removeUser(@RequestBody Map<String, Object> req) {
		Result result = new Result();
		User user = new User();
		user.setId(Integer.parseInt(req.get("id").toString()));
		try {
			userRepository.delete(user);
			result.setCode(Constants.SUCCESS);
		} catch (Exception e) {
			result.setCode(Constants.FAIL);
			result.setError(e.getMessage());
		}
		return result;
	}

}

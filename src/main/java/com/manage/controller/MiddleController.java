package com.manage.controller;

import com.manage.entity.Middle;
import com.manage.entity.Result;
import com.manage.entity.Week;
import com.manage.repository.MiddleRepository;
import com.manage.repository.WeekRepository;
import com.manage.service.MiddleService;
import com.manage.service.WeekService;
import com.manage.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Map;

@Controller
@RequestMapping("/middle")
public class MiddleController {

	@Resource
	MiddleRepository middleRepository;
	@Autowired
	MiddleService middleService;

	@RequestMapping("/list")
	@ResponseBody
	public Result findAll(@RequestBody Map<String, Object> req) {
		int page = Integer.parseInt(req.get("page").toString());
		int size = Integer.parseInt(req.get("size").toString());
		Middle middle = new Middle();
		middle.setName(req.get("name").toString());
		Result result = new Result();
		try {
			// 分页默认从0页开始
			Page<Middle> scores = middleService.findMiddle(page - 1, size, middle);
			result.setCode(Constants.SUCCESS);
			result.setData(scores.getContent());
			result.setTotal((int)scores.getTotalElements());
			result.setPages(scores.getTotalPages());
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
	public Result save(@RequestBody Map<String, Object> req) {
		Result result = new Result();
		try {
			Middle middle = new Middle();
			middle.setName(req.get("name").toString());
			middle.setEnglish(req.get("english").toString());
			middle.setOral(req.get("oral").toString());
			middle.setSocial(req.get("social").toString());
			middle.setScience(req.get("science").toString());
			middle.setChinese(req.get("chinese").toString());
			middle.setMaths(req.get("maths").toString());
			middle.setPhysical(req.get("physical").toString());
			middleRepository.save(middle);
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
	public Result update(@RequestBody Map<String, Object> req) {
		Result result = new Result();
		Integer id = Integer.parseInt(req.get("id").toString());
		Middle matcher = new Middle();
		matcher.setId(id);
		Middle scoreEntity = middleRepository.findOne(Example.of(matcher));
		if (scoreEntity != null) {
			try {
				Middle middle = new Middle();
				middle.setId(id);
				middle.setName(scoreEntity.getName());
				middle.setEnglish(req.get("english").toString());
				middle.setOral(req.get("oral").toString());
				middle.setSocial(req.get("social").toString());
				middle.setScience(req.get("science").toString());
				middle.setChinese(req.get("chinese").toString());
				middle.setMaths(req.get("maths").toString());
				middle.setPhysical(req.get("physical").toString());
				middleRepository.save(middle);
				result.setCode(Constants.SUCCESS);
			} catch (Exception e) {
				result.setCode(Constants.FAIL);
				result.setError(e.getMessage());
			}
		}else {
			result.setCode(Constants.FAIL);
			result.setError("该记录不存在！");
		}
		return result;
	}

	@Modifying
	@RequestMapping("/remove")
	@ResponseBody
	public Result remove(@RequestBody Map<String, Object> req) {
		Result result = new Result();
		Middle middle = new Middle();
		middle.setId(Integer.parseInt(req.get("id").toString()));
		try {
			middleRepository.delete(middle);
			result.setCode(Constants.SUCCESS);
		} catch (Exception e) {
			result.setCode(Constants.FAIL);
			result.setError(e.getMessage());
		}
		return result;
	}
}

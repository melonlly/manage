package com.manage.controller;

import com.manage.entity.Result;
import com.manage.entity.Week;
import com.manage.repository.WeekRepository;
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
@RequestMapping("/week")
public class WeekController {

	@Resource
	WeekRepository weekRepository;
	@Autowired
	WeekService weekService;

	@RequestMapping("/list")
	@ResponseBody
	public Result findAll(@RequestBody Map<String, Object> req) {
		int page = Integer.parseInt(req.get("page").toString());
		int size = Integer.parseInt(req.get("size").toString());
		Week week = new Week();
		week.setName(req.get("name").toString());
		Result result = new Result();
		try {
			// 分页默认从0页开始
			Page<Week> scores = weekService.findWeek(page - 1, size, week);
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
			Week week = new Week();
			week.setName(req.get("name").toString());
			week.setWeek1(req.get("week1").toString());
			week.setWeek2(req.get("week2").toString());
			week.setWeek3(req.get("week3").toString());
			week.setWeek4(req.get("week4").toString());
			week.setWeek5(req.get("week5").toString());
			week.setWeek6(req.get("week6").toString());
			week.setWeek7(req.get("week7").toString());
			week.setWeek8(req.get("week8").toString());
			week.setWeek9(req.get("week9").toString());
			week.setWeek10(req.get("week10").toString());
			week.setWeek11(req.get("week11").toString());
			week.setWeek12(req.get("week12").toString());
			week.setWeek13(req.get("week13").toString());
			week.setWeek14(req.get("week14").toString());
			weekRepository.save(week);
			result.setCode(Constants.SUCCESS);
		} catch (Exception e) {
			System.out.println(e);
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
		Week matcher = new Week();
		matcher.setId(id);
		Week scoreEntity = weekRepository.findOne(Example.of(matcher));
		if (scoreEntity != null) {
			try {
				Week week = new Week();
				week.setId(id);
				week.setName(scoreEntity.getName());
				week.setWeek1(req.get("week1").toString());
				week.setWeek2(req.get("week2").toString());
				week.setWeek3(req.get("week3").toString());
				week.setWeek4(req.get("week4").toString());
				week.setWeek5(req.get("week5").toString());
				week.setWeek6(req.get("week6").toString());
				week.setWeek7(req.get("week7").toString());
				week.setWeek8(req.get("week8").toString());
				week.setWeek9(req.get("week9").toString());
				week.setWeek10(req.get("week10").toString());
				week.setWeek11(req.get("week11").toString());
				week.setWeek12(req.get("week12").toString());
				week.setWeek13(req.get("week13").toString());
				week.setWeek14(req.get("week14").toString());
				weekRepository.save(week);
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
		Week week = new Week();
		week.setId(Integer.parseInt(req.get("id").toString()));
		try {
			weekRepository.delete(week);
			result.setCode(Constants.SUCCESS);
		} catch (Exception e) {
			result.setCode(Constants.FAIL);
			result.setError(e.getMessage());
		}
		return result;
	}
}

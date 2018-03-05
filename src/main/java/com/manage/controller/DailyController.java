package com.manage.controller;

import com.manage.entity.Daily;
import com.manage.entity.Result;
import com.manage.repository.DailyRepository;
import com.manage.service.DailyService;
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
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

@Controller
@RequestMapping("/daily")
public class DailyController {

	@Resource
	DailyRepository dailyRepository;
	@Autowired
	DailyService dailyService;

	@RequestMapping("/list")
	@ResponseBody
	public Result findDailys(@RequestBody Map<String, Object> req) {
		int page = Integer.parseInt(req.get("page").toString());
		int size = Integer.parseInt(req.get("size").toString());
		String start = req.get("start").toString();
		String end = req.get("end").toString();
		String lesson = req.get("lesson").toString();
		String phone = req.get("phone").toString();
		String homework = req.get("homework").toString();
		String dailys = req.get("daily").toString();
		Daily daily = new Daily();
		daily.setName(req.get("name").toString());
		if(lesson != null && lesson != ""){
			daily.setLesson(Integer.parseInt(lesson));
		}
		if(phone != null && phone != ""){
			daily.setPhone(Integer.parseInt(phone));
		}
		if(homework != null && homework != ""){
			daily.setHomework(Integer.parseInt(homework));
		}
		if(dailys != null && dailys != ""){
			daily.setDaily(Integer.parseInt(dailys));
		}

		System.out.println(page + " " + size + " " + start + " " + end);
		System.out.println(daily.toString());

		Result result = new Result();
		try {
			// 分页默认从0页开始
			Page<Daily> dailies = dailyService.findDailys(page - 1, size, start, end, daily);
			result.setCode(Constants.SUCCESS);
			result.setData(dailies.getContent());
			result.setTotal((int)dailies.getTotalElements());
			result.setPages(dailies.getTotalPages());
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
	public Result saveDaily(@RequestBody Map<String, Object> req) {
		Result result = new Result();
		Daily daily = new Daily();
		daily.setName(req.get("name").toString());
		daily.setLesson(Integer.parseInt(req.get("lesson").toString()));
		daily.setPhone(Integer.parseInt(req.get("phone").toString()));
		daily.setHomework(Integer.parseInt(req.get("homework").toString()));
		daily.setDaily(Integer.parseInt(req.get("daily").toString()));
		daily.setClean(Integer.parseInt(req.get("clean").toString()));
		daily.setDate(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
		try {
			dailyRepository.save(daily);
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
	public Result updateDaily(@RequestBody Map<String, Object> req) {
		Result result = new Result();
		Integer id = Integer.parseInt(req.get("id").toString());
		Daily m_daily = new Daily();
		m_daily.setId(id);
		Daily dailyEntity = dailyRepository.findOne(Example.of(m_daily));
		if (dailyEntity != null) {
			try {
				Daily daily = new Daily();
				daily.setId(id);
				daily.setName(req.get("name").toString());
				daily.setLesson(Integer.parseInt(req.get("lesson").toString()));
				daily.setPhone(Integer.parseInt(req.get("phone").toString()));
				daily.setHomework(Integer.parseInt(req.get("homework").toString()));
				daily.setDaily(Integer.parseInt(req.get("daily").toString()));
				daily.setClean(Integer.parseInt(req.get("clean").toString()));
				daily.setDate(dailyEntity.getDate());
				dailyRepository.save(daily);
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
	public Result removeDaily(@RequestBody Map<String, Object> req) {
		Result result = new Result();
		Daily daily = new Daily();
		daily.setId(Integer.parseInt(req.get("id").toString()));
		try {
			dailyRepository.delete(daily);
			result.setCode(Constants.SUCCESS);
		} catch (Exception e) {
			result.setCode(Constants.FAIL);
			result.setError(e.getMessage());
		}
		return result;
	}
}

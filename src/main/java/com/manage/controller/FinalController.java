package com.manage.controller;

import com.manage.entity.Final;
import com.manage.entity.Result;
import com.manage.repository.FinalRepository;
import com.manage.service.FinalService;
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
@RequestMapping("/final")
public class FinalController {

	@Resource
	FinalRepository finalRepository;
	@Autowired
	FinalService finalService;

	@RequestMapping("/list")
	@ResponseBody
	public Result findAll(@RequestBody Map<String, Object> req) {
		int page = Integer.parseInt(req.get("page").toString());
		int size = Integer.parseInt(req.get("size").toString());
		Final _final = new Final();
		_final.setName(req.get("name").toString());
		Result result = new Result();
		try {
			// 分页默认从0页开始
			Page<Final> scores = finalService.findFinal(page - 1, size, _final);
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
			Final _final = new Final();
			_final.setName(req.get("name").toString());
			_final.setEnglish(req.get("english").toString());
			_final.setOral(req.get("oral").toString());
			_final.setSocial(req.get("social").toString());
			_final.setScience(req.get("science").toString());
			_final.setChinese(req.get("chinese").toString());
			_final.setMaths(req.get("maths").toString());
			_final.setPhysical(req.get("physical").toString());
			_final.setMaths1(req.get("maths1").toString());
			_final.setPhysical1(req.get("physical1").toString());
			_final.setGeography(req.get("geography").toString());
			_final.setMusic(req.get("music").toString());
			finalRepository.save(_final);
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
		Final matcher = new Final();
		matcher.setId(id);
		Final scoreEntity = finalRepository.findOne(Example.of(matcher));
		if (scoreEntity != null) {
			try {
				Final _final = new Final();
				_final.setId(id);
				_final.setName(scoreEntity.getName());
				_final.setEnglish(req.get("english").toString());
				_final.setOral(req.get("oral").toString());
				_final.setSocial(req.get("social").toString());
				_final.setScience(req.get("science").toString());
				_final.setChinese(req.get("chinese").toString());
				_final.setMaths(req.get("maths").toString());
				_final.setPhysical(req.get("physical").toString());
				_final.setMaths1(req.get("maths1").toString());
				_final.setPhysical1(req.get("physical1").toString());
				_final.setGeography(req.get("geography").toString());
				_final.setMusic(req.get("music").toString());
				finalRepository.save(_final);
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
		Final _final = new Final();
		_final.setId(Integer.parseInt(req.get("id").toString()));
		try {
			finalRepository.delete(_final);
			result.setCode(Constants.SUCCESS);
		} catch (Exception e) {
			result.setCode(Constants.FAIL);
			result.setError(e.getMessage());
		}
		return result;
	}
}

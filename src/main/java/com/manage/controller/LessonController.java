package com.manage.controller;

import com.manage.entity.Lesson;
import com.manage.entity.Result;
import com.manage.repository.LessonRepository;
import com.manage.service.LessonService;
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
@RequestMapping("/lesson")
public class LessonController {

	@Resource
	LessonRepository lessonRepository;
	@Autowired
	LessonService lessonService;

	@RequestMapping("/list")
	@ResponseBody
	public Result findLesson(@RequestBody Map<String, Object> req) {
		int page = Integer.parseInt(req.get("page").toString());
		int size = Integer.parseInt(req.get("size").toString());
		Lesson lesson = new Lesson();
		lesson.setSemester(req.get("semester").toString());
		Result result = new Result();
		try {
			// 分页默认从0页开始
			Page<Lesson> scores = lessonService.findlesson(page - 1, size, lesson);
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
	public Result saveLesson(@RequestBody Map<String, Object> req) {
		Result result = new Result();
		try {
			Lesson lesson = new Lesson();
			lesson.setSemester(req.get("semester").toString());
			lesson.setPeriod(req.get("period").toString());
			lesson.setTime(req.get("time").toString());
			lesson.setDay1(req.get("day1").toString());
			lesson.setDay2(req.get("day2").toString());
			lesson.setDay3(req.get("day3").toString());
			lesson.setDay4(req.get("day4").toString());
			lesson.setDay5(req.get("day5").toString());
			lessonRepository.save(lesson);
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
	public Result updateLesson(@RequestBody Map<String, Object> req) {
		Result result = new Result();
		Integer id = Integer.parseInt(req.get("id").toString());
		Lesson m_lesson = new Lesson();
		m_lesson.setId(id);
		Lesson lessonEntity = lessonRepository.findOne(Example.of(m_lesson));
		if (lessonEntity != null) {
			try {
				Lesson lesson = new Lesson();
				lesson.setId(id);
				lesson.setSemester(req.get("semester").toString());
				lesson.setPeriod(req.get("period").toString());
				lesson.setTime(req.get("time").toString());
				lesson.setDay1(req.get("day1").toString());
				lesson.setDay2(req.get("day2").toString());
				lesson.setDay3(req.get("day3").toString());
				lesson.setDay4(req.get("day4").toString());
				lesson.setDay5(req.get("day5").toString());
				lessonRepository.save(lesson);
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
	public Result removeLesson(@RequestBody Map<String, Object> req) {
		Result result = new Result();
		Lesson lesson = new Lesson();
		lesson.setId(Integer.parseInt(req.get("id").toString()));
		try {
			lessonRepository.delete(lesson);
			result.setCode(Constants.SUCCESS);
		} catch (Exception e) {
			result.setCode(Constants.FAIL);
			result.setError(e.getMessage());
		}
		return result;
	}
}

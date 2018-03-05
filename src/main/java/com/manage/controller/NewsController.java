package com.manage.controller;

import com.manage.entity.News;
import com.manage.entity.Result;
import com.manage.repository.NewsRepository;
import com.manage.service.NewsService;
import com.manage.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

@Controller
@RequestMapping("/news")
public class NewsController {

	@Resource
	NewsRepository newsRepository;
	@Autowired
	NewsService newsService;

	@RequestMapping("/list")
	@ResponseBody
	public Result findNews(@RequestBody Map<String, Object> req) {
		int page = Integer.parseInt(req.get("page").toString());
		int size = Integer.parseInt(req.get("size").toString());
		String start = req.get("start").toString();
		String end = req.get("end").toString();
		News news = new News();
		news.setName(req.get("name").toString());
		news.setPublisher(req.get("publisher").toString());
		news.setUpdater(req.get("updater").toString());

		System.out.println(page + " " + size + " " + start + " " + end);
		System.out.println(news.toString());

		Result result = new Result();
		try {
			// 分页默认从0页开始
			Page<News> newss = newsService.findNews(page - 1, size, start, end, news);
			result.setCode(Constants.SUCCESS);
			result.setData(newss.getContent());
			result.setTotal((int)newss.getTotalElements());
			result.setPages(newss.getTotalPages());
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
	public Result saveNews(@RequestBody Map<String, Object> req) {
		Result result = new Result();
		try {
			News news = new News();
			news.setName(req.get("name").toString());
			news.setCreate_time(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()));
			news.setPublisher(req.get("publisher").toString());
			news.setContent(req.get("content").toString());
			newsRepository.save(news);
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
	public Result updateNews(@RequestBody Map<String, Object> req) {
		Result result = new Result();
		Integer id = Integer.parseInt(req.get("id").toString());
		News m_news = new News();
		m_news.setId(id);
		News newEntity = newsRepository.findOne(Example.of(m_news));
		if (newEntity != null) {
			try {
				News news = new News();
				news.setId(id);
				news.setName(req.get("name").toString());
				news.setCreate_time(newEntity.getCreate_time());
				news.setPublisher(newEntity.getPublisher());
				news.setUpdater(req.get("updater").toString());
				news.setUpdate_time(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()));
				news.setContent(req.get("content").toString());
				newsRepository.save(news);
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
	public Result removeNews(@RequestBody Map<String, Object> req) {
		Result result = new Result();
		News news = new News();
		news.setId(Integer.parseInt(req.get("id").toString()));
		try {
			newsRepository.delete(news);
			result.setCode(Constants.SUCCESS);
		} catch (Exception e) {
			result.setCode(Constants.FAIL);
			result.setError(e.getMessage());
		}
		return result;
	}
}

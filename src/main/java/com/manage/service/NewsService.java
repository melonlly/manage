package com.manage.service;

import com.manage.entity.News;
import org.springframework.data.domain.Page;

public interface NewsService {

	// 查找新闻信息
	Page<News> findNews(Integer page, Integer size, String start, String end, News news);

}

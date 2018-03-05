package com.manage.service;

import com.manage.entity.Lesson;
import org.springframework.data.domain.Page;

public interface LessonService {

	// 查找新闻信息
	Page<Lesson> findlesson(Integer page, Integer size, Lesson lesson);

}

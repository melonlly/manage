package com.manage.service.impl;

import com.manage.entity.Lesson;
import com.manage.repository.LessonRepository;
import com.manage.service.LessonService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

@Service(value="lessonService")
public class LessonServiceImpl implements LessonService {

	@Resource
	LessonRepository lessonRepository;

	@Override
	public Page<Lesson> findlesson(Integer page, Integer size, Lesson lesson){
		Pageable pageable = new PageRequest(page, size, Sort.Direction.ASC, "id");
		Page<Lesson> scores = lessonRepository.findAll(new Specification<Lesson>(){
			@Override
			public Predicate toPredicate(Root<Lesson> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
				if(lesson != null){
					if(lesson.getSemester() != null && lesson.getSemester() != ""){
						criteriaQuery.where(criteriaBuilder.and(criteriaBuilder.like(root.get("semester").as(String.class), lesson.getSemester())));
					}
				}
				return criteriaQuery.getRestriction();
			}
		}, pageable);
		return scores;
	}

}

package com.manage.service.impl;

import com.manage.entity.News;
import com.manage.repository.NewsRepository;
import com.manage.service.NewsService;
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

@Service(value="newsService")
public class NewsServiceImpl implements NewsService {

	@Resource
	NewsRepository newsRepository;

	@Override
	public Page<News> findNews(Integer page, Integer size, String start, String end, News news){
		Pageable pageable = new PageRequest(page, size, Sort.Direction.ASC, "id");
		Page<News> newss = newsRepository.findAll(new Specification<News>(){
			@Override
			public Predicate toPredicate(Root<News> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
				if(news != null){
					if(news.getName() != null && news.getName() != ""){
						criteriaQuery.where(criteriaBuilder.and(criteriaBuilder.equal(root.get("name").as(String.class), news.getName())));
					}
					if(news.getPublisher() != null && news.getPublisher() != ""){
						criteriaQuery.where(criteriaBuilder.and(criteriaBuilder.equal(root.get("publisher").as(String.class), news.getPublisher())));
					}
					if(news.getUpdater() != null && news.getUpdater() != ""){
						criteriaQuery.where(criteriaBuilder.and(criteriaBuilder.equal(root.get("updater").as(String.class), news.getUpdater())));
					}
					if(start != null && start != ""){
						criteriaQuery.where(criteriaBuilder.and(criteriaBuilder.greaterThanOrEqualTo(root.get("create_time").as(String.class), start)));
					}
					if(end != null && end != ""){
						criteriaQuery.where(criteriaBuilder.and(criteriaBuilder.lessThanOrEqualTo(root.get("create_time").as(String.class), end)));
					}
				}
				return criteriaQuery.getRestriction();
			}
		}, pageable);
		return newss;
	}

}

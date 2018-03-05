package com.manage.service.impl;

import com.manage.entity.Daily;
import com.manage.repository.DailyRepository;
import com.manage.service.DailyService;
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

@Service(value="dailyService")
public class DailyServiceImpl implements DailyService {

	@Resource
	DailyRepository dailyRepository;

	@Override
	public Page<Daily> findDailys(Integer page, Integer size, String start, String end, Daily daily){
		Pageable pageable = new PageRequest(page, size, Sort.Direction.ASC, "id");
		Page<Daily> dailys = dailyRepository.findAll(new Specification<Daily>(){
			@Override
			public Predicate toPredicate(Root<Daily> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
				if(daily != null){
					if(daily.getName() != null && daily.getName() != ""){
						criteriaQuery.where(criteriaBuilder.and(criteriaBuilder.equal(root.get("name").as(String.class), daily.getName())));
					}
					if(daily.getLesson() != null){
						criteriaQuery.where(criteriaBuilder.and(criteriaBuilder.equal(root.get("lesson").as(Integer.class), daily.getLesson())));
					}
					if(daily.getPhone() != null){
						criteriaQuery.where(criteriaBuilder.and(criteriaBuilder.equal(root.get("phone").as(Integer.class), daily.getPhone())));
					}
					if(daily.getHomework() != null){
						criteriaQuery.where(criteriaBuilder.and(criteriaBuilder.equal(root.get("homework").as(Integer.class), daily.getHomework())));
					}
					if(daily.getDaily() != null){
						criteriaQuery.where(criteriaBuilder.and(criteriaBuilder.equal(root.get("daily").as(Integer.class), daily.getDaily())));
					}
					if(start != null && start != ""){
						criteriaQuery.where(criteriaBuilder.and(criteriaBuilder.greaterThanOrEqualTo(root.get("date").as(String.class), start)));
					}
					if(end != null && end != ""){
						criteriaQuery.where(criteriaBuilder.and(criteriaBuilder.lessThanOrEqualTo(root.get("date").as(String.class), end)));
					}
				}
				return criteriaQuery.getRestriction();
			}
		}, pageable);
		return dailys;
	}

}

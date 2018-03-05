package com.manage.service.impl;

import com.manage.entity.Week;
import com.manage.repository.WeekRepository;
import com.manage.service.WeekService;
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

@Service(value="weekService")
public class WeekServiceImpl implements WeekService {

	@Resource
	WeekRepository weekRepository;

	@Override
	public Page<Week> findWeek(Integer page, Integer size, Week week){
		Pageable pageable = new PageRequest(page, size, Sort.Direction.ASC, "id");
		Page<Week> scores = weekRepository.findAll(new Specification<Week>(){
			@Override
			public Predicate toPredicate(Root<Week> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
				if(week != null){
					if(week.getName() != null && week.getName() != ""){
						criteriaQuery.where(criteriaBuilder.and(criteriaBuilder.equal(root.get("name").as(String.class), week.getName())));
					}
				}
				return criteriaQuery.getRestriction();
			}
		}, pageable);
		return scores;
	}

}

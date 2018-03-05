package com.manage.service.impl;

import com.manage.entity.Middle;
import com.manage.entity.Week;
import com.manage.repository.MiddleRepository;
import com.manage.repository.WeekRepository;
import com.manage.service.MiddleService;
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

@Service(value="middleService")
public class MiddleServiceImpl implements MiddleService {

	@Resource
	MiddleRepository middleRepository;

	@Override
	public Page<Middle> findMiddle(Integer page, Integer size, Middle middle){
		Pageable pageable = new PageRequest(page, size, Sort.Direction.ASC, "id");
		Page<Middle> scores = middleRepository.findAll(new Specification<Middle>(){
			@Override
			public Predicate toPredicate(Root<Middle> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
				if(middle != null){
					if(middle.getName() != null && middle.getName() != ""){
						criteriaQuery.where(criteriaBuilder.and(criteriaBuilder.equal(root.get("name").as(String.class), middle.getName())));
					}
				}
				return criteriaQuery.getRestriction();
			}
		}, pageable);
		return scores;
	}

}

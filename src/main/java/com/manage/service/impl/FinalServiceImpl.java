package com.manage.service.impl;

import com.manage.entity.Final;
import com.manage.entity.Middle;
import com.manage.repository.FinalRepository;
import com.manage.repository.MiddleRepository;
import com.manage.service.FinalService;
import com.manage.service.MiddleService;
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

@Service(value="finalService")
public class FinalServiceImpl implements FinalService {

	@Resource
	FinalRepository finalRepository;

	@Override
	public Page<Final> findFinal(Integer page, Integer size, Final _final){
		Pageable pageable = new PageRequest(page, size, Sort.Direction.ASC, "id");
		Page<Final> scores = finalRepository.findAll(new Specification<Final>(){
			@Override
			public Predicate toPredicate(Root<Final> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
				if(_final != null){
					if(_final.getName() != null && _final.getName() != ""){
						criteriaQuery.where(criteriaBuilder.and(criteriaBuilder.equal(root.get("name").as(String.class), _final.getName())));
					}
				}
				return criteriaQuery.getRestriction();
			}
		}, pageable);
		return scores;
	}

}

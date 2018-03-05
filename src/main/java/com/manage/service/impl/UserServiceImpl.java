package com.manage.service.impl;

import com.manage.entity.User;
import com.manage.repository.UserRepository;
import com.manage.service.UserService;
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

@Service(value="userService")
public class UserServiceImpl implements UserService {

	@Resource
	UserRepository userRepository;

	@Override
	public Page<User> findUsers(Integer page, Integer size, User user){
		Pageable pageable = new PageRequest(page, size, Sort.Direction.ASC, "id");
		Page<User> users = userRepository.findAll(new Specification<User>(){
			@Override
			public Predicate toPredicate(Root<User> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
				if(user != null){
					if(user.getName() != null && user.getName() != ""){
						criteriaQuery.where(criteriaBuilder.and(criteriaBuilder.equal(root.get("name").as(String.class), user.getName())));
					}
					if(user.getRelation() != null && user.getRelation() != ""){
						criteriaQuery.where(criteriaBuilder.and(criteriaBuilder.equal(root.get("relation").as(String.class), user.getRelation())));
					}
					if(user.getIs_admin() != null){
						criteriaQuery.where(criteriaBuilder.and(criteriaBuilder.equal(root.get("is_admin").as(Integer.class), user.getIs_admin())));
					}
					if(user.getStatus() != null){
						criteriaQuery.where(criteriaBuilder.and(criteriaBuilder.equal(root.get("status").as(Integer.class), user.getStatus())));
					}
					if(user.getRole() != null){
						criteriaQuery.where(criteriaBuilder.and(criteriaBuilder.equal(root.get("role").as(Integer.class), user.getRole())));
					}
				}
				return criteriaQuery.getRestriction();
			}
		}, pageable);
		return users;
	}

	@Override
	public User Login(String name, String password) {
		return userRepository.findOne(new Specification<User>() {
			@Override
			public Predicate toPredicate(Root<User> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
				Predicate p_name = criteriaBuilder.equal(root.get("name").as(String.class), name);
				Predicate p_password =  criteriaBuilder.equal(root.get("password").as(String.class), password);
				criteriaQuery.where(criteriaBuilder.and(p_name, p_password));
				return criteriaQuery.getRestriction();
			}
		});
	}

}

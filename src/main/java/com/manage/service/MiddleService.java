package com.manage.service;

import com.manage.entity.Middle;
import com.manage.entity.Week;
import org.springframework.data.domain.Page;

public interface MiddleService {

	Page<Middle> findMiddle(Integer page, Integer size, Middle middle);

}

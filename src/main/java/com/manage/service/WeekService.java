package com.manage.service;

import com.manage.entity.Week;
import org.springframework.data.domain.Page;

public interface WeekService {

	Page<Week> findWeek(Integer page, Integer size, Week week);

}

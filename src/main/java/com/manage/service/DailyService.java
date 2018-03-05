package com.manage.service;

import com.manage.entity.Daily;
import org.springframework.data.domain.Page;

public interface DailyService {

	// 查找考勤信息
	Page<Daily> findDailys(Integer page, Integer size, String start, String end, Daily daily);

}

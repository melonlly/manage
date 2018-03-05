package com.manage.service;

import com.manage.entity.Final;
import com.manage.entity.Middle;
import org.springframework.data.domain.Page;

public interface FinalService {

	Page<Final> findFinal(Integer page, Integer size, Final _final);

}

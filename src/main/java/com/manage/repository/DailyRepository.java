package com.manage.repository;

import com.manage.entity.Daily;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository("dailyRepository")
public interface DailyRepository extends JpaRepository<Daily, Long>, JpaSpecificationExecutor<Daily> {}

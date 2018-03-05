package com.manage.repository;

import com.manage.entity.Week;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository("weekRepository")
public interface WeekRepository extends JpaRepository<Week, Long>, JpaSpecificationExecutor<Week> {}

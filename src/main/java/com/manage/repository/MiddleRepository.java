package com.manage.repository;

import com.manage.entity.Middle;
import com.manage.entity.Week;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository("middleRepository")
public interface MiddleRepository extends JpaRepository<Middle, Long>, JpaSpecificationExecutor<Middle> {}

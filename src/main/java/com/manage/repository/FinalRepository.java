package com.manage.repository;

import com.manage.entity.Final;
import com.manage.entity.Middle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository("finalRepository")
public interface FinalRepository extends JpaRepository<Final, Long>, JpaSpecificationExecutor<Final> {}

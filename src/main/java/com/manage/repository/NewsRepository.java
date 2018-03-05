package com.manage.repository;

import com.manage.entity.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository("newsRepository")
public interface NewsRepository extends JpaRepository<News, Long>, JpaSpecificationExecutor<News> {}

package com.manage.repository;

import com.manage.entity.Catalog;
import com.manage.entity.Daily;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("catalogRepository")
public interface CatalogRepository extends JpaRepository<Catalog, Long> {}

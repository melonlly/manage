package com.manage.repository;

import com.manage.entity.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository("lessonRepository")
public interface LessonRepository extends JpaRepository<Lesson, Long>, JpaSpecificationExecutor<Lesson> {}

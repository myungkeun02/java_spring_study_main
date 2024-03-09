package org.myungkeun.blog_study.repository;

import org.myungkeun.blog_study.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}

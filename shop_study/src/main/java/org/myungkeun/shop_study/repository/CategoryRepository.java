package org.myungkeun.shop_study.repository;

import org.myungkeun.shop_study.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<CategoryEntity, Number> {

}

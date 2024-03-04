package org.myungkeun.blog_study.repository;

import org.myungkeun.blog_study.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<ProductEntity, String> {

}

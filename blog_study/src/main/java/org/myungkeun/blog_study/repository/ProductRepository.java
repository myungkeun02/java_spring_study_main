package org.myungkeun.blog_study.repository;

import org.myungkeun.blog_study.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

}

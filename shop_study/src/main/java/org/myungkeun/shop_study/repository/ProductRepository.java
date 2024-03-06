package org.myungkeun.shop_study.repository;

import org.myungkeun.shop_study.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<ProductEntity, String> {

}

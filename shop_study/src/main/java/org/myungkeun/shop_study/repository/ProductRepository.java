package org.myungkeun.shop_study.repository;

import org.myungkeun.shop_study.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Number> {

}

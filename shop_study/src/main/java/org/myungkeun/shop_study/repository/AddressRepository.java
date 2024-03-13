package org.myungkeun.shop_study.repository;

import org.myungkeun.shop_study.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Number> {
}

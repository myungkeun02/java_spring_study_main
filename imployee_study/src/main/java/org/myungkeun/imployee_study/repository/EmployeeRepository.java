package org.myungkeun.imployee_study.repository;

import org.myungkeun.imployee_study.entity.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<EmployeeEntity, String> {
}

package com.company.repositories;

import com.company.domain.models.DevelopmentDepartment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DevelopmentDepartmentsRepository extends JpaRepository <DevelopmentDepartment, Long>{
}

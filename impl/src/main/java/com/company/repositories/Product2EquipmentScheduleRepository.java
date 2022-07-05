package com.company.repositories;

import com.company.domain.models.Product2EquipmentSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Product2EquipmentScheduleRepository extends JpaRepository <Product2EquipmentSchedule, Long> {
}

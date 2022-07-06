package com.company.repositories;

import com.company.domain.models.EquipmentSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EquipmentScheduleRepository extends JpaRepository <EquipmentSchedule, Long> {
}

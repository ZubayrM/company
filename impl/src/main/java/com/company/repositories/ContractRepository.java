package com.company.repositories;

import com.company.domain.models.Contract;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContractRepository extends JpaRepository <Contract, Long> {
}

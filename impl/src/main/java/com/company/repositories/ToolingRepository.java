package com.company.repositories;

import com.company.domain.models.Tooling;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ToolingRepository extends JpaRepository <Tooling, Long>{
}

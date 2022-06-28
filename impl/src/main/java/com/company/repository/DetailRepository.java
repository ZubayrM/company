package com.company.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.xml.soap.Detail;

@Repository
public interface DetailRepository extends JpaRepository <Detail, Long> {
}

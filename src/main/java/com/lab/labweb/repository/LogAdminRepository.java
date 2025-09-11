package com.lab.labweb.repository;

import com.lab.labweb.model.LogAdmin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogAdminRepository extends JpaRepository<LogAdmin, Long> {
}

package com.bechir.departement.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bechir.departement.College;

public interface CollegeRepository extends JpaRepository<College, Long> {

// College getCollege(Long id);

	

}



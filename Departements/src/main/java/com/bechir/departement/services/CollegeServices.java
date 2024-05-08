package com.bechir.departement.services;

import java.util.List;

import org.springframework.data.domain.Page;

import com.bechir.departement.College;

public interface CollegeServices {

	
	Page<College> getAllCollegeParPage(int page, int size);

	List<College>getAllCollege();
	
	College saveCollege(College c);
	
	void deleteCollegeById(Long id);
	
	College updateCollege(College c);
	
	College getCollege(Long id);
	
	

}

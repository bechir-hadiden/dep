package com.bechir.departement.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.bechir.departement.College;
import com.bechir.departement.repos.CollegeRepository;
@Service
public class CollegeServiceImpl implements CollegeServices{
	
	@Autowired 
	CollegeRepository  collegeRepository ;
	@Override
	public College saveCollege(College c) {
		// TODO Auto-generated method stub
		return  collegeRepository.save(c);
	}

	@Override
	public Page<College> getAllCollegeParPage(int page, int size) {
		// TODO Auto-generated method stub
		return collegeRepository.findAll(PageRequest.of(page, size));
	}


	@Override
	public void deleteCollegeById(Long id) {
		collegeRepository.deleteById(id);
		
	}

	@Override
	public College updateCollege(College c) {
		// TODO Auto-generated method stub
		return collegeRepository.save(c);
	}

	@Override
	public List<College> getAllCollege() {
		// TODO Auto-generated method stub
		return collegeRepository.findAll();
	}

	@Override
	public College getCollege(Long id) {
		// TODO Auto-generated method stub
		return collegeRepository.findById(id).get();
		
	}




	


}

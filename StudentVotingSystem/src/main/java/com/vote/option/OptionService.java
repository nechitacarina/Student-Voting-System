package com.vote.option;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public class OptionService {

	@Autowired
	private OptionRepository optionRepo;
	
	public Page<Option> listAll(int pageNumber, String keyword){
		Pageable pageable = PageRequest.of(pageNumber - 1, 5);
		if(keyword != null) {
			return  optionRepo.findAll(keyword, pageable);
		}
		return optionRepo.findAll(pageable);
	}
	
	public List<Option> listAll(){
		return (List<Option>) optionRepo.findAll();
	}
	
	public List<Option> findOptionByElection(Integer id_chestionar){
		return (List<Option>) optionRepo.findOptionByElection(id_chestionar);
	}
	
	public void save(Option option) {
		optionRepo.save(option);
	}
	
	public Option get(Integer id_optiune){
		Optional<Option> result = optionRepo.findById(id_optiune);
		return result.get();
	}
	
	public void delete(Integer id_optiune) {		
		optionRepo.deleteById(id_optiune);
	}
}

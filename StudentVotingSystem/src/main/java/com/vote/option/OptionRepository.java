package com.vote.option;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface OptionRepository extends PagingAndSortingRepository<Option, Integer> {

	@Query(
			  value = "SELECT * FROM optiune,chestionar WHERE optiune.id_chestionar = chestionar.id_chestionar AND chestionar.id_chestionar= :id_chestionar",
			  nativeQuery = true)

	List<Option> findOptionByElection(@Param("id_chestionar") Integer id_chestionar);
	
	@Query(
			  value = "SELECT id_chestionar FROM optiune WHERE optiune.id_optiune=:id_optiune ",
			  nativeQuery = true)

	public Integer findElectionByOption(@Param("id_optiune") Integer id_optiune);
	
	@Query("SELECT o FROM Option o WHERE o.denumire LIKE %?1%")
	public Page<Option> findAll(String keyword, Pageable pageable);
}

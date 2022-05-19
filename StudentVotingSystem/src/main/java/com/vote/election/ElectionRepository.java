package com.vote.election;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;


public interface ElectionRepository extends PagingAndSortingRepository<Election, Integer> {

	@Query(
			  value = "SELECT DISTINCT chestionar.* FROM chestionar, grup_chestionar, grup_student WHERE grup_student.id_student = :id_student "
			  		+ "AND grup_chestionar.id_grup=grup_student.id_grup "
			  		+ "AND grup_chestionar.id_chestionar = chestionar.id_chestionar "
			  		+ "AND chestionar.data_final>CURRENT_DATE ",
			  nativeQuery = true)

	List<Election> findElectionByStudent(@Param("id_student") Integer id_student);
	
	@Query("SELECT e FROM Election e WHERE e.denumire LIKE %?1%")
	public Page<Election> findAll(String keyword, Pageable pageable);
}

package org.sid.DAO;


import org.sid.ENTITIES.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface PatientRepository extends JpaRepository<Patient, Long>{
	public Page<Patient>findByNameContains(String name,Pageable pageable);
	@Query("select p from Patient p where p.name like :x and p.malade = :y")
	public Page<Patient>chercher(
			@Param("x")String mc,
			@Param("y")Boolean bool,Pageable pageable);
			
	

}

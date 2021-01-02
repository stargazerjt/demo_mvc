package com.stargate.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ModuleRepository extends JpaRepository<ModuleTbl, Long> {
	
	@Query(value="SELECT form_output FROM module_tbl WHERE module=:module and major_type=:majorType and main_type=:mainType",nativeQuery = true)
	public String getFormOutputFromDb(@Param("module")String module, 
			@Param("majorType")String majorType,
			@Param("mainType")String mainType);

}

package com.cyient.dao;



import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.cyient.model.Site;
import com.cyient.model.Site_Access;
import com.cyient.model.User;


// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface SiteAccessRepository extends CrudRepository<Site_Access, Long> {
	
    @Query("SELECT s FROM Site_Access s where s.siteid = :siteid") 
    Site_Access fetchDataBySiteID(@Param("siteid") Site siteid);
	
	

}
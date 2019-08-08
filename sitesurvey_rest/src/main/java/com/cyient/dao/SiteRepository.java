package com.cyient.dao;



import java.util.List;

import org.hibernate.Criteria;
import org.springframework.data.repository.CrudRepository;

import com.cyient.model.Site;
import com.cyient.model.User;



// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface SiteRepository extends CrudRepository<Site, Long> {
	
	

}
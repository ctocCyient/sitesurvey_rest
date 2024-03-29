package com.cyient.dao;



import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.cyient.model.Site;
import com.cyient.model.Site_Access;
import com.cyient.model.User;
import com.google.gson.JsonElement;


// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface UserRepository extends CrudRepository<User, String> {
	
    @Query("SELECT u FROM User u where u.username = :username and u.password = :password") 
    User findByCredentials(@Param("username") String username,@Param("password") String password);


}
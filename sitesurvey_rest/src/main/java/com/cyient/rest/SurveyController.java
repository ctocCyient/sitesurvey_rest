package com.cyient.rest;
import java.util.Optional;

import javax.validation.Valid;

import org.apache.catalina.core.ApplicationContext;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;  
import org.springframework.web.bind.annotation.RestController;

import com.cyient.dao.SiteAccessRepository;
import com.cyient.dao.SiteRepository;
import com.cyient.dao.TechnicianRepository;
import com.cyient.dao.UserRepository;
import com.cyient.model.Rest_Response;
import com.cyient.model.Site;
import com.cyient.model.Site_Access;
import com.cyient.model.Site_Access_json;
import com.cyient.model.User;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/sitesurvey_rest")
public class SurveyController {

	@Autowired
	private SiteRepository siteRepo;

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private SiteAccessRepository siteaccessRepo;
	
	@Autowired
	private TechnicianRepository technicianrepositoryRepo;
	
	@Autowired
	private Rest_Response response;
	
	@Autowired
	private User UserObj;
	

	/*@Autowired
	private SurveyDAO Dao;*/

	Gson gson = new Gson();
	@GetMapping(path="/getUsers", produces = "application/json")  
	public Iterable<User> hello(){ 
		return userRepo.findAll();	    	
	}  

	@PostMapping(path="/addUsers",consumes = "application/json")
	public String createUser(@Valid @RequestBody User userobj) {
		//Rest_Response response = new Rest_Response();

		if(userRepo.save(userobj)!= null)	
		{
			response.setStatus("User Added Successfully");
		}
		else
		{
			response.setStatus("Failed to Add User");
		}
		return gson.toJson(response);
	}
	
	
	@PostMapping(path="/UpdateUser/{username}",consumes = "application/json")
	public String UpdateUser(@Valid @RequestBody User user,@PathVariable("username") String username) {
		//Rest_Response response = new Rest_Response();
		
		Optional<User> User_fetch = userRepo.findById(username);
        
        if(User_fetch.isPresent())
        {
            User UpdateEntity = User_fetch.get();
            UpdateEntity.setCity(user.getCity());
            UpdateEntity.setCreatedDate(user.getCreatedDate());
            UpdateEntity.setEmailId(user.getEmailId());
            UpdateEntity.setMobileNumber(user.getMobileNumber());
            UpdateEntity.setName(user.getName());
            UpdateEntity.setPassword(user.getPassword());
            UpdateEntity.setRegion(user.getRegion());
            UpdateEntity.setRole(user.getRole());

    		if(userRepo.save(UpdateEntity)!= null)	
    		{
    			response.setStatus("User Updated Successfully");
    		}
    		else
    		{
    			response.setStatus("Failed to Update User");
    		}		                          
    }	
        return gson.toJson(response); 
	}
	
	
	
	@GetMapping(path="/getUser/{username}", produces = "application/json")  
	public Optional<User> getUser(@PathVariable("username") String username){ 
		return userRepo.findById(username);	    	
	}  
	
	@GetMapping(path="/authenticate/{username}/{password}", produces = "application/json")  
	public String getauthenticate(@PathVariable("username") String username,@PathVariable("password") String password){ 
		int o = 0;
		try {
			User userobj = new User();
			userobj = userRepo.findByCredentials(username, password);
			//System.out.println(userobj.getUsername());	
			
			if(userobj.getUsername().equals(username)) {
				//System.out.println(gson.toJson(technicianrepositoryRepo.findByTechnicianId(username)));
				return gson.toJson(technicianrepositoryRepo.findByTechnicianId(username));
			}
			else
			{
				response.setStatus("Authentication Failed");
				return gson.toJson(response);
			}
		}
		catch(Exception e)
		{
			response.setStatus("Authentication Failed");
			return gson.toJson(response);
		}
	}  
	
	
	@GetMapping(path="/getSiteAccess/{siteId}", produces = "application/json")  
	public Site_Access_json getSiteAccess(@PathVariable("siteId") String siteId){ 
		Site s = new Site();
		s.setSiteid(siteId);
		Site_Access obj = siteaccessRepo.findBySiteid(s);
		Site_Access_json o =  new Site_Access_json();
		o.setAccessType(obj.getAccessType());
		o.setSiteid(obj.getSiteid().getSiteid());
		
		return o;	    	
	} 
	
	
	@GetMapping(path="/test", produces = "application/json")  
	public String getSites(){ 
		/*JsonObject obj = new JsonObject();
		Site s = new Site();
		s.setSiteid("IND001");
		JsonArray a = new JsonArray();
		a.add(gson.toJson(siteaccessRepo.findBySiteid(s)));
		obj.add("Site",a);
		    	return gson.toJson(obj);
	}
	*/
	 return gson.toJson(technicianrepositoryRepo.findByTechnicianId("kiran"));
		//return "Hello";
	}
}  


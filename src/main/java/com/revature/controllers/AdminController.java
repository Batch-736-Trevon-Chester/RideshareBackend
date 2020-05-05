package com.revature.controllers;

import java.util.List;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.beans.Admin;
import com.revature.services.AdminService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * AdminController takes care of handling our requests to /admins.
 * It provides methods that can perform tasks like all admins, admin by id, add admin, update admin, and
 * delete admin by id.
 * 
 * @author Adonis Cabreja
 *
 */

@RestController
@RequestMapping("/admins")
@CrossOrigin
@Api(tags= {"Admin"})
public class AdminController {
	
	private static final Logger logger = LogManager.getLogger(AdminController.class);
	
	@Autowired
	private AdminService as;
	
	/**
	 * HTTP GET method (/users)
	 * 
	 * @return A list of all the admins.
	 */
	
	@ApiOperation(value="Returns all admins", tags= {"Admin"})
	@GetMapping
	public List<Admin> getAdmins() {
		
		return as.getAdmins();
	}
	
	/**
	 * HTTP GET method (/users/{id})
	 * 
	 * @param id represents the admin's id.
	 * @return An admin that matches the id.
	 */
	
	@ApiOperation(value="Returns admin by id", tags= {"Admin"})
	@GetMapping("/{id}")
	public Admin getAdminById(@PathVariable("id")int id) {
		
		return as.getAdminById(id);
	}
	
	/**
	 * HTTP POST method (/users)
	 * 
	 * @param admin represents the new Admin object being sent.
	 * @return The newly created object with a 201 code.
	 */
	
	@ApiOperation(value="Adds a new admin", tags= {"Admin"})
	@PostMapping
	public ResponseEntity<Admin> createAdmin(@Valid @RequestBody Admin admin) {
		logger.debug("Request Body: "+admin.getAdminId());
		return new ResponseEntity<>(as.createAdmin(admin), HttpStatus.CREATED);
	}
	
	/**
	 * HTTP PUT method (/users)
	 * 
	 * @param admin represents the updated Admin object being sent.
	 * @return The newly updated object.
	 */
	
	@ApiOperation(value="Updates admin by id", tags= {"Admin"})
	@PutMapping("/{id}")
	public Admin updateAdmin(@Valid @RequestBody Admin admin) {
		logger.debug("Request Body: "+admin.getAdminId());
		return as.updateAdmin(admin);
	}
	
	/**
	 * HTTP DELETE method (/users/{id})
	 * 
	 * @param id represents the admin's id.
	 * @return A string that says which admin was deleted.
	 */
	
	@ApiOperation(value="Deletes an admin by id", tags= {"Admin"})
	@DeleteMapping("/{id}")
	public String deleteAdmin(@PathVariable("id")int id) {
		return as.deleteAdminById(id);
	}
}

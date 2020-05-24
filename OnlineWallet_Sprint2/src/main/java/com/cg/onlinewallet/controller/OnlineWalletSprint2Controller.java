package com.cg.onlinewallet.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.RestController;


import com.cg.onlinewallet.service.*;
@CrossOrigin(origins="*")
@RestController
public class OnlineWalletSprint2Controller {

	@Autowired
	private OnlineWalletSprint2Service onlineWalletSprint2Service;

	public OnlineWalletSprint2Controller() {
		// TODO Auto-generated constructor stub
	}
    
	
	/*********************************************************************************************************************
	* Method:loginAdmin
	* 
	* Description:For admin to login into the application
	* 
	* @param email:Admin's email
	* 
	* @param password:Admin's password
	* 
	* @returns Entity:After login,it will return the userId .
	* 
	* Created By-Abhishek Sharma
		***********************************************************************************************************************/
    
	@GetMapping("/admin")
	public ResponseEntity<Integer> loginAdmin(String email, String password)
	{
		Integer userId = onlineWalletSprint2Service.loginAdmin(email, password);
		return new ResponseEntity<Integer>(userId, HttpStatus.OK);
	}
	/*********************************************************************************************************************
	* Method:getUserList
	* Description:To fetch the userlist
	* @param userId:User's Id
	* @param userStatus:User's account status
	* @returns Entity:it will return the list of users having active or inactive accounts
	* Created By-Abhishek Sharma
	***********************************************************************************************************************/

	@GetMapping("/admin/{adminId}/userlist")
	public ResponseEntity<List<String>> getUserList(@PathVariable("adminId") Integer userId, String userStatus)
	{
		List<String> userList = onlineWalletSprint2Service.getUserList(userId, userStatus);
		return new ResponseEntity<List<String>>(userList, HttpStatus.OK);
	}
	/*********************************************************************************************************************
	* Method:changeUserStatus
	* Description:To change the status of user's account to live or blocked
	* @param userId:Admin's Id
	* @param email:User's email
	* @param userStatus: User's account status
	* @returns Entity:it will change the user's account status and return that information
	* Created By-Anurag Kumar
	***********************************************************************************************************************/

	@GetMapping("/admin/{adminId}/changestatus")
	public ResponseEntity<String> changeUserStatus(@PathVariable("adminId") Integer adminId, String email,
			String userStatus) 
	{
		String returnEmail=onlineWalletSprint2Service.changeUserStatus(adminId, email, userStatus);
//		System.out.prinStln(returnEmail);
		return new ResponseEntity<String>(returnEmail, HttpStatus.OK);
	}
}

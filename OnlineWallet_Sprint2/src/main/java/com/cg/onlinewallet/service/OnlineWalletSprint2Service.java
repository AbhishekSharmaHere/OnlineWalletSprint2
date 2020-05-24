package com.cg.onlinewallet.service;

import java.util.List;



public interface OnlineWalletSprint2Service {
	
	Integer loginAdmin(String loginName, String password);

	List<String> getUserList(Integer userId, String userStatus);

	String changeUserStatus(Integer adminId, String loginName, String userStatus);
	
	}



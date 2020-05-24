package com.cg.onlinewallet.dao;

import java.util.List;


import com.cg.onlinewallet.entities.WalletAccount;

import com.cg.onlinewallet.entities.WalletUser;

public interface OnlineWalletSprint2Dao {
	void saveUser(WalletUser user);

	WalletUser getUser(Integer userId);

	WalletAccount getAccount(Integer accountId);

	

	void saveAccount(WalletAccount account);

	WalletUser getUserByEmail(String email);

	boolean checkUserByEmail(String email);

	

	List<String> getActiveUserList();

	List<String> getNonActiveUserList();


}

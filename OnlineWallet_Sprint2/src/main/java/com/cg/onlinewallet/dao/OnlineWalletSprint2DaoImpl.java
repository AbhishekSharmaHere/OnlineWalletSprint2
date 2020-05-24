package com.cg.onlinewallet.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.cg.onlinewallet.entities.*;
import com.cg.onlinewallet.entities.WalletAccount.status;
import com.cg.onlinewallet.exceptions.UnauthorizedAccessException;

@Repository
public class OnlineWalletSprint2DaoImpl implements OnlineWalletSprint2Dao{
	
	@PersistenceContext
    private EntityManager entityManager;

public OnlineWalletSprint2DaoImpl() {
	super();
	// TODO Auto-generated constructor stub
}

	
	@Override
	public void saveUser(WalletUser user) {
		// TODO Auto-generated method stub
		entityManager.persist(user);
	}

	@Override
	public void saveAccount(WalletAccount account) {
		entityManager.persist(account);
	}

	
	/*********************************************************************************************************************
	* Method: checkUserByEmail
	* 
	* Description: To check that whether a user is present with given email or not
	* 
	* @param email:User's email
	* 
	* @returns Boolean: true if the user is present with entered email else false
	* 
	* Created By -Vinay Kumar Singh
	***********************************************************************************************************************/
	
	@Override
	public boolean checkUserByEmail(String email) 
	{ 
		String qry = "SELECT user.email FROM WalletUser user WHERE user.email= :email";
		TypedQuery<String> query = entityManager.createQuery(qry, String.class).setParameter("email", email);
		try {
			query.getSingleResult();
		} catch (Exception ex) {
			return false;
		}
		return true;
	}
	/*********************************************************************************************************************
	* Method: getUserByEmail
	* 
	* Description: To access the user with the given email
	* 
	* @param email:User's email
	* 
	* @returns user: It will return the user present with entered email
	* 
	* Created By - Vinay Kumar Singh 
	***********************************************************************************************************************/
	@Override
	public WalletUser getUserByEmail(String email)
	{
		String qry = "SELECT user FROM WalletUser user WHERE user.email= :email";
		TypedQuery<WalletUser> query = entityManager.createQuery(qry, WalletUser.class).setParameter("email",
				email);
		return query.getSingleResult();
	}

	/*********************************************************************************************************************
	* Method: getActiveUserList
	* 
	* Description: To access the list of users whose account is active 
	* 
	* @returns userList: It will return the users whose accounts are active
	* 
	* Created By -Abhishek Sharma
	***********************************************************************************************************************/
	@Override
	public List<String> getActiveUserList() 
	{
		String qry = "SELECT user.email FROM WalletUser user JOIN user.accountDetail account WHERE account.userStatus= :userStatus";
		TypedQuery<String> query = entityManager.createQuery(qry, String.class).setParameter("userStatus",
				status.active);
		List<String> userList;
		try
		{
			userList = query.getResultList();
		} catch (Exception exception)
		{
			throw new UnauthorizedAccessException("No user Exist for given criteria");
		}
		return userList;
	}

	/*********************************************************************************************************************
	 * * Method: getNonActiveUserList
	 * 
	   * Description: To access the list of users whose account is inactive 
	   * 
	   * @returns userList: It will return the users whose accounts are inactive
	   * 
	   * Created By - Abhishek Sharma
	   **********************************************************************************************************************/
	@Override
	public List<String> getNonActiveUserList() 
	{
		String qry = "SELECT user.email FROM WalletUser user JOIN user.accountDetail account WHERE account.userStatus= :userStatus";
		TypedQuery<String> query = entityManager.createQuery(qry, String.class).setParameter("userStatus",
				status.non_active);
		List<String> userList;
		try {
			userList = query.getResultList();
		} catch (Exception exception) {
			throw new UnauthorizedAccessException("No user Exist for given criteria");
		}
		return userList;
	}

	@Override
	public WalletUser getUser(Integer userId)
	{
		WalletUser user = entityManager.find(WalletUser.class, userId);
		return user;
	}

	@Override
	public WalletAccount getAccount(Integer accountId)
	{
		WalletAccount account = entityManager.find(WalletAccount.class, accountId);
		return account;
	}

	
}






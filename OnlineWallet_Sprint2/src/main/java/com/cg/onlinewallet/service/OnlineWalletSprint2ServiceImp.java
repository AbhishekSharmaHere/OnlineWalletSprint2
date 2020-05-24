package com.cg.onlinewallet.service;


import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cg.onlinewallet.dao.*;
import com.cg.onlinewallet.entities.*;
import com.cg.onlinewallet.entities.WalletAccount.status;
import com.cg.onlinewallet.entities.WalletUser.type;
import com.cg.onlinewallet.exceptions.*;

@Transactional
@Service
public class OnlineWalletSprint2ServiceImp implements OnlineWalletSprint2Service {

	public OnlineWalletSprint2ServiceImp() {
		// TODO Auto-generated constructor stub
	}

	@Autowired
	private OnlineWalletSprint2Dao onlineWalletSprint2Dao;

	

	/*********************************************************************************************************************
	 * Method: loginAdmin Description: To verify the admin data for adminlogin 
	 * 
	 * @param email:Admin's LoginName
	 *            
	 * @param password:   Admin's password
	 *         
	 * @returns Integer: userId associated with the loginName provided if no exceptions occurs
	 * 
	 * @throws UnauthorizedAccessException:Raised if the account with loginName is not an admin type
	 * 
	 * @throws ValidationException:If the password doesn't matches with the user's stored password
     *
     * Created By -Abhishek Sharma 
	 * 
	 ***********************************************************************************************************************/
	@Override
	public Integer loginAdmin(String email, String password) 
	{
		if(!onlineWalletSprint2Dao.checkUserByEmail(email))
		{
			throw new UnauthorizedAccessException("No Admin exist for this email address. Kindly get yourself registered as Admin");
		}
			
		WalletUser user = onlineWalletSprint2Dao.getUserByEmail(email);
			if (user.getUserType() == type.user)
			{
				throw new UnauthorizedAccessException("You are not authorized to login from here");
			}
		if (user.getPassword().equals(password) == false)
			{
			throw new ValidationException("The LoginName and password Combination does not match");
			}
		return user.getUserID();
	}

	/*********************************************************************************************************************
	 * Method: getUserList Description: Returns the list of users according to their status
	 * 
	 * @param adminId: Admin's userId
	 * 
	 * @param userstatus: user status
	 * 
	 * @returns List<String>: List containing the loginNames of the user with userStatus i.e. either active or non-active
	 * 
	 * @throws UnauthorizedAccessException:If the account associated with adminType is not admin 
	 * 
	 * @throws WrongValueException:If the UserStatus is other than active and non-active
	 * 
	 *              Created By -Abhishek Sharma
	 * 
	 ***********************************************************************************************************************/
	@Override
	public List<String> getUserList(Integer adminId, String userStatus)
	{

		WalletUser admin = onlineWalletSprint2Dao.getUser(adminId);
		if (admin.getUserType() == type.user)
			{
			throw new UnauthorizedAccessException("Only admin can perform this task");
			}
		if (userStatus.equalsIgnoreCase(new String("non_active")))
		{
			return onlineWalletSprint2Dao.getNonActiveUserList();
		}
		else if (userStatus.equalsIgnoreCase(new String("active")))
			{
			return onlineWalletSprint2Dao.getActiveUserList();
			}
		throw new WrongValueException("Wrong Input Format");
	}

	/*********************************************************************************************************************
	 * Method: changeUserStatus Description: Changes User Status from Active to Non and vice versa
	 * 
	 * @param adminId:Admin's userId
	 * 
	 * @param email: User's email whose status has to be changed
	 * 
	 * @param userstatus:user status
	 * 
	 * @throws UnauthorizedAccessException:If the user associated with adminId is not a admin type
	 *             
	 * @throws InvalidException:If there is no user with the login name provided 
	 * 
	 * @throws UnauthorizedAccessException:It is raised if the user associated with the login name is admin .
	 * 
	 * @throws WrongValueException: If the variable userStatus is other than the active and non_active 
	 *             
	 *             Created By - Anurag Kumar
	 * 
	 ***********************************************************************************************************************/
	@Override
	public String changeUserStatus(Integer adminId, String email, String userStatus)
	{

		WalletUser admin = onlineWalletSprint2Dao.getUser(adminId);
		if (admin.getUserType() == type.user)
		{
			throw new UnauthorizedAccessException("You are Unauthorized to perform this task");
		}
		if (onlineWalletSprint2Dao.checkUserByEmail(email) == false)
		{
			throw new InvalidException("No user with this LoginName. Please Enter a valid LoginName");
		}
		
		
		WalletUser user = onlineWalletSprint2Dao.getUserByEmail(email);
		if (user.getUserType() == type.admin)
			{
			throw new UnauthorizedAccessException("Can't perform Task, Unauthorized Access");
			}
		if (userStatus.equals(new String("non_active")))
			{
			user.getAccountDetail().setUserStatus(status.non_active);
			}
		else if (userStatus.equals(new String("active"))) 
		{
			user.getAccountDetail().setUserStatus(status.active);
		} else
			{
			throw new WrongValueException("The Status code does not exist");
			}
		
		return user.getEmail();
	}

	

}

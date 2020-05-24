package com.cg.onlinewallet;

import java.time.LocalDateTime;


import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.cg.onlinewallet.entities.*;
import com.cg.onlinewallet.entities.WalletAccount.status;
import com.cg.onlinewallet.entities.WalletUser.type;

@Transactional
@SpringBootApplication
public class OnlineWalletSprint2Application implements CommandLineRunner {
	@Autowired
	EntityManager em;

	public static void main(String[] args) {
		SpringApplication.run(OnlineWalletSprint2Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		
		
		WalletTransactions wat1 = new WalletTransactions("amount debited", LocalDateTime.now(), 500.0, 500.0);
		WalletTransactions wat2 = new WalletTransactions("amount credited", LocalDateTime.now(), 500.0, 1000.0);
		WalletTransactions wat3 = new WalletTransactions("amount credited", LocalDateTime.now(), 500.0, 1500.0);
		WalletTransactions wat4 = new WalletTransactions("amount debited", LocalDateTime.now(), 500.0, 1000.0);

	//	List<WalletTransactions> list1 = new ArrayList<WalletTransactions>();
	//	List<WalletTransactions> list2 = new ArrayList<WalletTransactions>();

	/*	list1.add(wat1);
		list1.add(wat2);
		list2.add(wat3);
		list2.add(wat4);*/
		WalletAccount wa1 = new WalletAccount(1000.00, status.active);
		WalletAccount wa2 = new WalletAccount(1000.00, status.non_active);
		WalletAccount wa3 = new WalletAccount(0.0, status.active);
		WalletAccount wa4 = new WalletAccount(0.0,  status.non_active);
		WalletAccount wa5 = new WalletAccount(0.0, status.non_active);
		WalletAccount wa6 = new WalletAccount(0.0, status.non_active);
		

		WalletUser wu1 = new WalletUser("Harshit Singh", "Harshit@123", "9897446350", "harshit@gmail.com",
				type.user, wa1);
		WalletUser wu2 = new WalletUser("Vinay Singh Parmar", "Parmar@123", "9876543210", "parmar@gmail.com",
				type.user, wa2);
		WalletUser wu3 = new WalletUser("Admin", "Admin@123", "1234567890", "admin@wallet.com", type.admin, wa3);
		WalletUser wu4 = new WalletUser("Abhishek Sharma", "Abhi@123", "7894561230", "abhishek@gmail.com", type.user, wa4);
		WalletUser wu5 = new WalletUser("Anurag Kumar", "Babu@123", "7894561230", "smartanu7@gmail.com", type.user, wa5);
		WalletUser wu6 = new WalletUser("Vinay Kumar Singh", "Kumar@123", "9942566730", "kumar@gmail.com", type.user, wa6);
		

		em.persist(wu1);
		em.persist(wu2);
		em.persist(wu3);
		em.persist(wu4);
		em.persist(wu5);
		em.persist(wu6);

		em.persist(wa1);
		em.persist(wa2);
		em.persist(wa3);
		em.persist(wa4);
		em.persist(wa5);
		em.persist(wa6);

		em.persist(wat1);
		em.persist(wat2);
		em.persist(wat3);
		em.persist(wat4);
		
		
	}
    
}
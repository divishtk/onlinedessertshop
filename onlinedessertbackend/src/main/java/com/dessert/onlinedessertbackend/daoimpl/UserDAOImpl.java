package com.dessert.onlinedessertbackend.daoimpl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.dessert.onlinedessertbackend.dao.UserDAO;
import com.dessert.onlinnedessertbackend.dto.Address;

import com.dessert.onlinnedessertbackend.dto.User;



@Repository("userDAO")
@Transactional
public class UserDAOImpl implements UserDAO


{
	@Autowired
	private SessionFactory sessionFactory;


	
	
	@Override
	public boolean addUser(User user) 
	
	{
		
		try {			
			sessionFactory.getCurrentSession().persist(user);			
			return true;
		}
		catch(Exception ex) 
		{
			return false;
		}
	}

	@Override
	public boolean addAddress(Address address) 
	
	{

		
		
		try {			
			// will look for this code later and why we need to change it
			sessionFactory.getCurrentSession().persist(address);			
			return true;
		}
		catch(Exception ex) {
			return false;
		}
		
	}


	@Override
	public User getByEmail(String email) 
	{
//	  String selectQuery="FROM User WHERE email= :email";	
//		
//	  try
//	  {
//		  return sessionFactory
//					.getCurrentSession()
//						.createQuery(selectQuery,User.class)
//							.setParameter("email",email)
//								.getSingleResult();
//		  
//	  }
//	  catch(Exception ex) 
//	  
//	  {
//		  ex.printStackTrace();
//		  return null;
//		  
//		  
//	  }
		
		String selectQuery = "FROM User WHERE email = :email";
		try {
		return sessionFactory
				.getCurrentSession()
					.createQuery(selectQuery,User.class)
						.setParameter("email",email)
							.getSingleResult();
		}
		catch(Exception ex) {
			return null;
		}
	  
	}
/*
	@Override
	public Address getBillingAddress(User user)
	{
	String selectQuery="FROM Address where user = :user AND billing = :billing";
		
	try
	{
		return sessionFactory
				.getCurrentSession()
					.createQuery(selectQuery,Address.class)
						.setParameter("user",user)
						.setParameter("billing", true)
							.getSingleResult();
		
	}
	catch(Exception ex) {
		
		 ex.printStackTrace();
		  return null;
		
	}
	
		
	}*/

	
	
	/*@Override
	public List<Address> listShipingAddresses(User user) 
	{
		

		String selectQuery="FROM Address where user = :user AND shipping = :shipping";
		
		try
		{
			return sessionFactory
					.getCurrentSession()
						.createQuery(selectQuery,Address.class)
							.setParameter("user",user)
							.setParameter("shipping", true)
								.getResultList();
			
		}
		catch(Exception ex) {
			
			 ex.printStackTrace();
			  return null;
			
		}
	}*/

	@Override
	public Address getBillingAddress(int userId) 
	
	{
		
		String selectQuery="FROM Address where userId = :user AND billing = :billing";
		
		try
		{
			return sessionFactory
					.getCurrentSession()
						.createQuery(selectQuery,Address.class)
							.setParameter("userId",userId)
							.setParameter("billing", true)
								.getSingleResult();
			
		}
		catch(Exception ex) 
		{
			
			 
			  return null;
	}
	}
	@Override
	public List<Address> listShipingAddresses(int userId) 
	{
		
		String selectQuery = "FROM Address WHERE userId = :userId AND shipping = :isShipping ORDER BY id DESC";
		return sessionFactory
				.getCurrentSession()
					.createQuery(selectQuery,Address.class)
						.setParameter("userId", userId)
						.setParameter("isShipping", true)
							.getResultList();
		
	}

	@Override
	public Address getAddress(int addressId) 
	
	{
		try {			
			return sessionFactory.getCurrentSession().get(Address.class, addressId);			
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
			return null;
		}
	}

	@Override
	public User get(int id) {
		try {			
			return sessionFactory.getCurrentSession().get(User.class, id);			
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
			return null;
		}
	}
	}



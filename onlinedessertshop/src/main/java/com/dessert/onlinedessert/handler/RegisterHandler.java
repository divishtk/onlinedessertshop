package com.dessert.onlinedessert.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dessert.onlinedessert.model.RegisterModel;
import com.dessert.onlinedessertbackend.dao.UserDAO;
import com.dessert.onlinnedessertbackend.dto.Address;
import com.dessert.onlinnedessertbackend.dto.Cart;
import com.dessert.onlinnedessertbackend.dto.User;

@Component
public class RegisterHandler
{
	
	@Autowired
	private UserDAO userDAO;

	
	public RegisterModel init() 
     {
		
		return new RegisterModel();
		
     }
	
	
	public void addUser(RegisterModel registerModel,User user)
	{
		
		registerModel.setUser(user);
		
		
	}
	
	public void addBilling(RegisterModel registerModel,Address billing)
	{
		
		registerModel.setBilling(billing);
		
		
	}
	
	
	
	public String saveAll(RegisterModel model)
	{
		
		String transitionValue ="sucess";
		
		//fetching user
		
		User user=model.getUser();
		
		if(user.getRole().equals("USER"))
		{
			Cart cart=new Cart();
			cart.setUser(user);
			user.setCart(cart);
			
			
		}
		
		//saving user
		
		userDAO.addUser(user);
		
		
		//getting address
		Address billing =model.getBilling();
		
		billing.setUserId(user.getId());
		billing.setBilling(true);
		
		
		//saving address
		userDAO.addAddress(billing);
		

		return transitionValue;
		
	}
	
}

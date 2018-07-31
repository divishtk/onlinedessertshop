package com.dessert.onlinedessert.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.binding.message.MessageBuilder;
import org.springframework.binding.message.MessageContext;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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

	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	
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
	
	//checking is password matches confirm password
	public String validateUser(User user,MessageContext error)
	{
		String transitionValue="sucess";
		
		if(!(user.getPassword().equals(user.getConfirmPassword())))
		{
			
			error.addMessage(new MessageBuilder().error().source("confirmPassword").defaultText("Password doesnot match").build());
			
			transitionValue="failure";
		}
	
		
	
	
		//checking uniqueness of email id
		if(userDAO.getByEmail(user.getEmail())!=null)
		{
			

			error.addMessage(new MessageBuilder().error().source("email").defaultText("Email already exists").build());
			
			transitionValue ="failure";
		}
		
	/*	if(userDAO.getByEmail(user.getEmail())!=null) {
		    error.addMessage(new MessageBuilder().error().source(
		      "email").defaultText("Email address is already taken!").build());
		    transitionValue = "failure";
		   }
		
		*/
		
		return transitionValue;
		
		
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
		
		
		
		//encode password
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		
		
		
		
		
		
		
		
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

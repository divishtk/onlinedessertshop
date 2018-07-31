package com.dessert.onlinedessertshop.controller;

import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.dessert.onlinedessert.model.UserModel;
import com.dessert.onlinedessertbackend.dao.UserDAO;
import com.dessert.onlinnedessertbackend.dto.User;


@ControllerAdvice
public class GlobalController 
{
	
	
	@Autowired
	private HttpSession session;
	
	@Autowired
	private UserDAO userDAO;
	private UserModel userModel = null;

	
	@ModelAttribute("userModel")
	public UserModel getUserModel()
	{
		
		if(session.getAttribute("userModel")==null)
		{
			
			//adding user model
			Authentication authentication =SecurityContextHolder.getContext().getAuthentication();
			
			User user=userDAO.getByEmail(authentication.getName());
			if(user!=null)
			{
				
				//create a new usermodel obj to  pass user details
				
				userModel = new UserModel();
				// set the name and the id
				userModel.setId(user.getId());
				userModel.setEmail(user.getEmail());
				userModel.setRole(user.getRole());

				userModel.setFullName(user.getFirstName() + " " + user.getLastName());
				
				if(userModel.getRole().equals("USER")) 
				{
					userModel.setCart(user.getCart());					
				}				

				session.setAttribute("userModel", userModel);
    				return userModel;
				
			}
			
			
		}
		
		
		return (UserModel) session.getAttribute("userModel");
		
		
	}
	
	
}


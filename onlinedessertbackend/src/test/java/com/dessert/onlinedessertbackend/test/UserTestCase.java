package com.dessert.onlinedessertbackend.test;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.dessert.onlinedessertbackend.dao.UserDAO;
import com.dessert.onlinnedessertbackend.dto.Address;
import com.dessert.onlinnedessertbackend.dto.Cart;
import com.dessert.onlinnedessertbackend.dto.User;

public class UserTestCase


{
	
	private static AnnotationConfigApplicationContext context;
	private static UserDAO userDAO;
	private User user = null;
	private Cart cart = null;
	private Address address = null;
	
	
	@BeforeClass
	public static void init() {
		context = new AnnotationConfigApplicationContext();
		context.scan("com.dessert");
		context.refresh();
		
		userDAO = (UserDAO) context.getBean("userDAO");
	}

	
//	@Test
//	
//	public void testAdd() 
//	
//	{
//		user = new User() ;
//		user.setFirstName("Hrithikss");
//		user.setLastName("Roshansss");
//		user.setEmail("hhhrr@gmail.com");
//		user.setContactNumber("1234512345");
//		user.setRole("USER");
//		
//		user.setPassword("123457");
//		
//		//add user
//		
//		assertEquals("Failed to add user!",true,userDAO.addUser(user));
//		
//		
//		address = new Address();
//		address.setAddressLineOne("1041/B Jadooooo Society, Krissh Nagar");
//		address.setAddressLineTwo("Near Kaabil Store");
//		address.setCity("Mumbai");
//		address.setState("Maharashtra");
//		address.setCountry("India");
//		address.setPostalCode("400001");
//		address.setBilling(true);
//		
//		//linking user with address using userid
//		address.setUserId(user.getId());
//		
//		
//		
//		assertEquals("Failed to add address!",true,userDAO.addAddress(address));
//		
//			if(user.getRole().equals("USER"))
//			{
//				
//				
//				//create a cart for this user
//				cart =new Cart();
//				cart.setUser(user);
//				
//				
//				//adding the cart
//				assertEquals("Failed to add cart!",true,userDAO.addCart(cart));
//
//				
//				//add shipping address for this user
//				// add the shipping address
//				address = new Address();
//				address.setAddressLineOne("201/B Jadoo Society, Kishan Kanhaiya Nagar");
//				address.setAddressLineTwo("Near Kudrat Store");
//				address.setCity("Mumbai");
//				address.setState("Maharashtra");
//				address.setCountry("India");
//				address.setPostalCode("400001");
//				
//				//set shipping to true
//				address.setShipping(true);
//				
//				
//				//linking it with user
//				address.setUserId(user.getId());
//				
//				
//				assertEquals("Failed to add the shipping address!", true, userDAO.addAddress(address));
//				
//				
//				
//				
//			}
//		
//	}
//	
	
//	
//	@Test
//	
//	public void testAdd() 
//	
//	{
//		user = new User() ;
//		user.setFirstName("Hrithikss");
//		user.setLastName("Roshansss");
//		user.setEmail("hhhrr@gmail.com");
//		user.setContactNumber("1234512345");
//		user.setRole("USER");
//		
//		user.setPassword("123457");
//		
//			if(user.getRole().equals("USER"))
//			{
//				
//				
//				//create a cart for this user
//				cart =new Cart();
//				cart.setUser(user);
//				
//				//attach cart with user
//				user.setCart(cart);
//				
//				
//			}
//			//add user
//		 	assertEquals("Failed to add user!",true,userDAO.addUser(user));
//		
//	}
	
	
//	@Test
//	public void testUpdateCart() 
//	{
//		
//		
//		//fetch user by its email
//		user=userDAO.getByEmail("hhhrr@gmail.com");
//		
//		//get the cart of user
//		cart=user.getCart();
//		
//		cart.setGrandTotal(555);
//		
//		cart.setCartLines(2);
//		
//		
//		assertEquals("Failed to update cart!",true,userDAO.updateCart(cart));
//		
//	}
//	
	
	/*
	@Test
	public void testAddAddress() 
	{
		user = new User() ;
		user.setFirstName("Hrit");
		user.setLastName("Roshan");
		user.setEmail("h@gmail.com");
		user.setContactNumber("123451");
		user.setRole("USER");
		user.setPassword("1237");
		
		//add user
		assertEquals("Failed to add user!",true,userDAO.addUser(user));
		
		
		//adding address
		address = new Address();
		address.setAddressLineOne("200/C  Society, Krissh Nagar");
		address.setAddressLineTwo("Near l Store");
		address.setCity("Mumbai");
		address.setState("Maharashtra");
		address.setCountry("India");
		address.setPostalCode("400001");
		address.setBilling(true);
		
		//attached the user with address
		
		address.setUser(user);
		assertEquals("Failed to add address!",true,userDAO.addAddress(address));
		
		
		
		
		
		
		// add the shipping address
		address = new Address();
		address.setAddressLineOne("201/C Jadoo Society, Kishan Kanhaiya Nagar");
		address.setAddressLineTwo("Neart Store");
		address.setCity("Mumbai");
		address.setState("Maharas");
		address.setCountry("India");
		address.setPostalCode("400001");
	
		//set shipping to true
	address.setShipping(true);
	
	
	//attached the user with address
	
			address.setUser(user);
			assertEquals("Failed to add shipping address!",true,userDAO.addAddress(address));
			
			
			
			

		
	}
	*/
	/*@Test
	public void testAddAddress() 
	
	{
		
		user=userDAO.getByEmail("hhhrr@gmail.com");
		
		address = new Address();
		address.setAddressLineOne("201/B Jadoo Society, Kishan Kanhaiya Nagar");
		address.setAddressLineTwo("Near Kudrat Store");
		address.setCity("Banglore");
		address.setState("Karnataka");
		address.setCountry("India");
		address.setPostalCode("400001");
		//set shipping to true
		address.setShipping(true);
		
		
		//attached the user with address
		
				address.setUser(user);
				assertEquals("Failed to add shipping address!",true,userDAO.addAddress(address));
				
				
				
		
	}
	
	*/
	
	/*@Test
	public void testGetAddress()
	{
		
		user=userDAO.getByEmail("hhhrr@gmail.com");
		assertEquals("Failed to fetch list of address and size doestnot match!",2,userDAO.listShipingAddresses(int userId).size());
		assertEquals("Failed to fetch billing address and size doestnot match!","Mumbai",userDAO.getBillingAddress(int userId).getCity());

		

		
	}
	
	
	
	
	*/
	
	
	
}

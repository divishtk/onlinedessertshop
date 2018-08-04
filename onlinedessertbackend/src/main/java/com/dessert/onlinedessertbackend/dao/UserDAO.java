package com.dessert.onlinedessertbackend.dao;

import java.util.List;

import com.dessert.onlinnedessertbackend.dto.Address;
import com.dessert.onlinnedessertbackend.dto.Cart;
import com.dessert.onlinnedessertbackend.dto.User;

public interface UserDAO 

{
	
	//adding user
	boolean addUser(User user);
	User get(int id);

	
	User getByEmail(String email);
	
	//adding an address
	boolean addAddress(Address address);
	
	
	//alternative
	Address getAddress(int addressId);

	Address getBillingAddress(int userId);
	List<Address> listShipingAddresses(int userId);
//	
	
//	
//	Address getBillingAddress(User user);
//	List<Address> listShipingAddresses(User user);
	
	//updating cart
	
	

}

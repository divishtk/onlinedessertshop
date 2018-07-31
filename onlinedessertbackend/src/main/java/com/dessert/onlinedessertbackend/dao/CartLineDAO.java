package com.dessert.onlinedessertbackend.dao;

import java.util.List;

import com.dessert.onlinnedessertbackend.dto.Cart;
import com.dessert.onlinnedessertbackend.dto.CartLine;

public interface CartLineDAO 

{
		//the commons methods from perviously coded one
	public List<CartLine> list(int cartId);
	public CartLine get(int id);	
	public boolean add(CartLine cartLine);
	public boolean update(CartLine cartLine);
	public boolean remove(CartLine cartLine);
	
	
	//business methods
	
	// fetch the CartLine based on cartId and productId
		public CartLine getByCartAndProduct(int cartId, int productId);		
			
//		// updating the cart
		boolean updateCart(Cart cart);
		
		// list of available cartLine
		public List<CartLine> listAvailable(int cartId);
		
//		// adding order details
//		boolean addOrderDetail(OrderDetail orderDetail);
	
}

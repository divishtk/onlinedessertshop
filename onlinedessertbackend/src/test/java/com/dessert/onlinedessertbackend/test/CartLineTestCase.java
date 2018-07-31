package com.dessert.onlinedessertbackend.test;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.dessert.onlinedessertbackend.dao.CartLineDAO;
import com.dessert.onlinedessertbackend.dao.ProductDAO;
import com.dessert.onlinedessertbackend.dao.UserDAO;
import com.dessert.onlinnedessertbackend.dto.Cart;
import com.dessert.onlinnedessertbackend.dto.CartLine;
import com.dessert.onlinnedessertbackend.dto.Product;
import com.dessert.onlinnedessertbackend.dto.User;

public class CartLineTestCase 

{
	
private static AnnotationConfigApplicationContext context;
	
	
	private static CartLineDAO cartLineDAO=null;
	private static ProductDAO productDAO=null;
	private static UserDAO userDAO=null;
	
	
	private CartLine cartLine = null;
	private Product product=null;
	private Cart cart=null;
	private User user=null;
	
	
	@BeforeClass
	public static void init() 
	{
		context = new AnnotationConfigApplicationContext();
		context.scan("com");
		context.refresh();
		productDAO = (ProductDAO)context.getBean("productDAO");
		userDAO = (UserDAO)context.getBean("userDAO");
		cartLineDAO = (CartLineDAO)context.getBean("cartLineDAO");


}
	
	
	@Test
	public void testAddCartLine() {
		
		// fetch the user and then cart of that user
		User user = userDAO.getByEmail("divisht@gmail.com");		
		Cart cart = user.getCart();
		
		// fetch the product 
		Product product = productDAO.get(1);
		
		// Create a new CartLine
		cartLine = new CartLine();
		cartLine.setBuyingPrice(product.getUnitPrice());
		cartLine.setProductCount(cartLine.getProductCount() + 1);
		cartLine.setTotal(cartLine.getProductCount() * product.getUnitPrice());

		
		cartLine.setAvailable(true);		
		
		cartLine.setCartId(cart.getId());
		cartLine.setProduct(product);
		
		assertEquals("Failed to add the CartLine!",true, cartLineDAO.add(cartLine));

		
		//update the cart
		cart.setGrandTotal(cart.getGrandTotal() + cartLine.getTotal());
		cart.setCartLines(cart.getCartLines() + 1);
		
		assertEquals("Failed to update the CartLine!",true, cartLineDAO.updateCart(cart));

	}
	
	
	
}
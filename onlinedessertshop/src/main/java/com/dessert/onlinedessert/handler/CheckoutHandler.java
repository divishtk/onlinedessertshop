package com.dessert.onlinedessert.handler;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dessert.onlinedessert.model.CheckoutModel;
import com.dessert.onlinedessert.model.UserModel;
import com.dessert.onlinedessertbackend.dao.CartLineDAO;
import com.dessert.onlinedessertbackend.dao.ProductDAO;
import com.dessert.onlinedessertbackend.dao.UserDAO;
import com.dessert.onlinnedessertbackend.dto.Address;
import com.dessert.onlinnedessertbackend.dto.Cart;
import com.dessert.onlinnedessertbackend.dto.CartLine;
import com.dessert.onlinnedessertbackend.dto.OrderDetail;
import com.dessert.onlinnedessertbackend.dto.OrderItem;
import com.dessert.onlinnedessertbackend.dto.Product;
import com.dessert.onlinnedessertbackend.dto.User;

@Component
public class CheckoutHandler 

{
private static final Logger logger = LoggerFactory.getLogger(CheckoutHandler.class);
	
	@Autowired
	private UserDAO userDAO;
	
	@Autowired
	private ProductDAO productDAO;

	@Autowired
	private CartLineDAO cartLineDAO;
	
	@Autowired
	private HttpSession session;
	
	
	public CheckoutModel init(String name) throws Exception{

		User user = userDAO.getByEmail(name);
		CheckoutModel checkoutModel = null;	

		if(user!=null) {
			checkoutModel = new CheckoutModel();
			
			double checkoutTotal = 0.0;
			List<CartLine> cartLines = cartLineDAO.listAvailable(user.getCart().getId());

			if(cartLines.size() == 0 ) {
				throw new Exception("There are no products available for checkout now!");
			}
			
			for(CartLine cartLine: cartLines) {
				checkoutTotal += cartLine.getTotal();
			}
			
			checkoutModel.setCartLines(cartLines);
			checkoutModel.setCheckoutTotal(checkoutTotal);			
		}			
		
		return checkoutModel;
	}
	
	public List<Address> getShippingAddresses(CheckoutModel model) {
		
		List<Address> addresses = userDAO.listShipingAddresses(model.getUser().getId());
		
		if(addresses.size() == 0) 
		{
			addresses = new ArrayList<>();
		}

		addresses.add(addresses.size(), userDAO.getBillingAddress(model.getUser().getId()));			
		
		return addresses;
		
	}
	
	
	
	
	public String saveAddressSelection(CheckoutModel checkoutModel, int shippingId) {

		String transitionValue = "sucess";
		
		//logger.info(String.valueOf(shippingId));
		
		Address shipping = userDAO.getAddress(shippingId);		
		
		checkoutModel.setShipping(shipping);
		
		return transitionValue;
		
	}
	
	
	
	public String saveAddress(CheckoutModel checkoutModel, Address shipping) {

		String transitionValue = "sucess";
		
		// set the user id
		// set shipping as true
		shipping.setUserId(checkoutModel.getUser().getId());
		shipping.setShipping(true);
		userDAO.addAddress(shipping);
		
		// set the shipping id to flowScope object
		checkoutModel.setShipping(shipping);
		
		return transitionValue;
		
	}
		
	
	public String saveOrder(CheckoutModel checkoutModel) {
		String transitionValue = "sucess";	
		
		// create a new order object
		OrderDetail orderDetail = new OrderDetail();
				
		// attach the user 
		orderDetail.setUser(checkoutModel.getUser());
				
		// attach the shipping address
		orderDetail.setShipping(checkoutModel.getShipping());
		
		// fetch the billing address
		Address billing = userDAO.getBillingAddress(checkoutModel.getUser().getId());		
		orderDetail.setBilling(billing);

		List<CartLine> cartLines = checkoutModel.getCartLines();
		OrderItem orderItem = null;
		
		double orderTotal = 0.0;
		int orderCount = 0;
		Product product = null;
		
		for(CartLine cartLine : cartLines) {
			
			orderItem = new OrderItem();
			
			orderItem.setBuyingPrice(cartLine.getBuyingPrice());
			orderItem.setProduct(cartLine.getProduct());
			orderItem.setProductCount(cartLine.getProductCount());
			orderItem.setTotal(cartLine.getTotal());
			
			orderItem.setOrderDetail(orderDetail);
			orderDetail.getOrderItems().add(orderItem);
			
			orderTotal += orderItem.getTotal();
			orderCount++;
			
			// update the product
			// reduce the quantity of product
			product = cartLine.getProduct();
			product.setQuantity(product.getQuantity() - cartLine.getProductCount());
			product.setPurchases(product.getPurchases() + cartLine.getProductCount());
			productDAO.update(product);
			
			// delete the cartLine
			cartLineDAO.remove(cartLine);
			

			
		}
		
		orderDetail.setOrderTotal(orderTotal);
		orderDetail.setOrderCount(orderCount);
		orderDetail.setOrderDate(new Date());
		
		// save the order
		cartLineDAO.addOrderDetail(orderDetail);

		// set it to the orderDetails of checkoutModel
		checkoutModel.setOrderDetail(orderDetail);

		
		// update the cart
		Cart cart = checkoutModel.getCart();
		cart.setGrandTotal(cart.getGrandTotal() - orderTotal);
		cart.setCartLines(cart.getCartLines() - orderCount);
		cartLineDAO.updateCart(cart);
		
		// update the cart if its in the session
		UserModel userModel = (UserModel) session.getAttribute("userModel");
		if(userModel!=null) {
			userModel.setCart(cart);
		}
		
				
		return transitionValue;		
	}

	
	public OrderDetail getOrderDetail(CheckoutModel checkoutModel) {
		return checkoutModel.getOrderDetail();
	}
	
	
	
	
	
	
	
}

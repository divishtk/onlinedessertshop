package com.dessert.onlinedessertshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.dessert.onlinedessert.service.CartService;

@Controller
@RequestMapping("/cart")
public class CartController 
{
	
	@Autowired
	private CartService cartService;
	
	

	@RequestMapping("/show")
	public ModelAndView showCart(@RequestParam(name="result",required=false)String result) 
	{
		
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("title", "Shopping Cart");
		mv.addObject("userClickShowCart", true);
		
		if(result!=null)
		{
			
			switch(result) {
			
			case "error":
				mv.addObject("message", "Something went wrong!");
				break;
			case "unavailable":
				mv.addObject("message", "Product Quantity is not available!");
				break;
				
			case "updated":
				mv.addObject("message", "Cart has been updated successfully!");
				cartService.validateCartLine();

				break;
			case "added":
				mv.addObject("message", "CartLine has been addded successfully!");	
				cartService.validateCartLine();
				break;
			case "deleted":
				mv.addObject("message", "Cartline has been removed successfully!");
				break;
			case "maximum":
				mv.addObject("message", "Cartline has been reached to maximum count!");
				break;
			case "modified":
				mv.addObject("message", "One or more items inside cart has been modified!");
				break;
			

		}
		}
		else
		{
			String response = cartService.validateCartLine();
			if(response.equals("result=modified")) {
				mv.addObject("message", "One or more items inside cart has been modified!");
			}
			
		}
		
		
		
		
		
	
		
		mv.addObject("cartLines",cartService.getCartLines());
		
		
		return mv;
		
	
}
	
	@RequestMapping("/{cartLineId}/update")
	public String udpateCart(@PathVariable int cartLineId, @RequestParam int count) 
	{
		String response = cartService.manageCartLine(cartLineId, count);		
		return "redirect:/cart/show?"+response;		
	}
	
	

	@RequestMapping("/{cartLineId}/delete")
	public String udpateCart(@PathVariable int cartLineId) 
	{
		String response = cartService.deleteCartLine(cartLineId);		
		return "redirect:/cart/show?"+response;		
	}
	
	
	
	
	@RequestMapping("/add/{productId}/product")
	public String addCart(@PathVariable int productId) 
	{
		String response = cartService.addCartLine(productId);		
		return "redirect:/cart/show?"+response;		
	}
	
	@RequestMapping("/validate")
	public String validateCart() {	
		String response = cartService.validateCartLine();
		if(!response.equals("result=success")) {
			return "redirect:/cart/show?"+response;
		}
		else {
			return "redirect:/cart/checkout";
		}
	}	
	
	
	
	
}

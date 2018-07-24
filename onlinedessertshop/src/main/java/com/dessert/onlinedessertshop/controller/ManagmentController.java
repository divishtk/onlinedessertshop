package com.dessert.onlinedessertshop.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.dessert.onlinedessert.validator.ProductValidator;
import com.dessert.onlinedessertbackend.dao.CategoryDAO;
import com.dessert.onlinedessertbackend.dao.ProductDAO;
import com.dessert.onlinedessertshop.utility.FileUploadUtility;
import com.dessert.onlinnedessertbackend.dto.Category;
import com.dessert.onlinnedessertbackend.dto.Product;

@Controller
@RequestMapping("/manage")
public class ManagmentController 

{
	
	
	@Autowired
	private CategoryDAO categoryDAO;	
	
	
	@Autowired
	private ProductDAO productDAO;
	
	
	private static final Logger logger=LoggerFactory.getLogger(ManagmentController.class);
	
	
	@RequestMapping(value="/products", method=RequestMethod.GET)
	public ModelAndView showmanageProducts(@RequestParam(name="operation",required=false)String operation)
	{		
			
		ModelAndView mv = new ModelAndView("page");	
		mv.addObject("title","Manage Products");		
		mv.addObject("userClickManageProduct",true);
		
			Product nProduct = new Product();
		
		// assuming that the user is ADMIN
		
		nProduct.setSupplierId(1);
		nProduct.setActive(true);

		mv.addObject("product", nProduct);
		
		if(operation!=null)
		{
			
			if(operation.equals("product"))
			{
				
				mv.addObject("message","Product Added Successully");
				
			}
			else if(operation.equals("category"))
					{
				
				mv.addObject("message","Category Added Successully");
				
					}
			
			
		}
		
		
		
		
		return mv;
	}
	
	
	
	
	@RequestMapping(value="/products", method=RequestMethod.POST)
	
	public String handlerProductSubmission(@Valid @ModelAttribute("product") Product mProduct,BindingResult results,Model model,HttpServletRequest request) 
	
	{
		//handle image validation for new products
		
		if(mProduct.getId()==0) 
		{
		new ProductValidator().validate(mProduct,results);
		}
		else
		{
			
			if(!mProduct.getFile().getOriginalFilename().equals(""))
			{
				
				new ProductValidator().validate(mProduct,results);	
			}
			
		}
		
		
		//chck if there are errors
		if(results.hasErrors())
		{
			
			model.addAttribute("userClickManageProduct",true);
			model.addAttribute("title", "Manage Products");
			model.addAttribute("message", "Validation Failed For Product Submission");
			
			return "page";
			
		}
		
		
		logger.info(mProduct.toString());
		
		
		
		if(mProduct.getId()==0)
		{
			//create a new product record if id is 0
		productDAO.add(mProduct);
		}
		else
		{
			
			
			//update product if id is  noot 0
			productDAO.update(mProduct);
		}
		
		
		
		
		if(!mProduct.getFile().getOriginalFilename().equals(""))
		{
			
			FileUploadUtility.uploadFile(request,mProduct.getFile(),mProduct.getCode());
		}
		
		
		
		return "redirect:/manage/products?operation=product";
		
		
	}
	
	
	@RequestMapping(value="/product/{id}/activation", method=RequestMethod.POST)
	@ResponseBody
	public String handleProductActivation(@PathVariable int id)
	{
		
		
		//is going to fetch the product from database
		Product product=productDAO.get(id);
		boolean isActive=product.isActive();
		
		
		//activating and deactivating product based on value of active field
		product.setActive(!product.isActive());
		//updating product
		productDAO.update(product);
		
		
		
		return (isActive)?"You have successfully deactivated the product with id " +product.getId()
							:"You have successfully activated the product with id " +product.getId();
	}	
	
	
	
	
		
	
	@RequestMapping(value="/{id}/product", method=RequestMethod.GET)

	
	public ModelAndView showEditProduct(@PathVariable int id) {		

		ModelAndView mv = new ModelAndView("page");	
		mv.addObject("title","Product Management");		
		mv.addObject("userClickManageProduct",true);
		
		
		//fetch product from database
		 Product nProduct = productDAO.get(id);	
		 
		 
		 //set product fetch from database
		mv.addObject("product", nProduct);

			
		return mv;
		
		
	}
	
	
	
	//to handle category submission
	
	@RequestMapping(value="/category",method=RequestMethod.POST)
	
	public String handleCategorySubmission(@ModelAttribute Category category)
	{
		
		categoryDAO.add(category);
		
		
		return "redirect:/manage/products?operation=category";
		
		
		
		
		
	}
	
	
	
	
	
	@ModelAttribute("category")
	public Category getCategory()
	
	
	
	{
		
		return new Category();
		
	}
	
	
	
	
	
	
	
//returning categories for all request mapping
	@ModelAttribute("categories") 
	public List<Category> getCategories() 
	{
		return categoryDAO.list();
	}
	
	
	
	
}

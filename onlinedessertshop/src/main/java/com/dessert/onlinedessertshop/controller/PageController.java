package com.dessert.onlinedessertshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.dessert.onlinedessertbackend.dao.CategoryDAO;
import com.dessert.onlinnedessertbackend.dto.Category;

@Controller
public class PageController 
{
	
	@Autowired
	private CategoryDAO categoryDAO;

	@RequestMapping(value= {"/","/home","/index"})
	public ModelAndView index()
	{
		ModelAndView mv=new ModelAndView("page");
		mv.addObject("title","Home");
		mv.addObject("userClickHome",true );
	
		//passing the list of categories
		mv.addObject("categories", categoryDAO.list());
		
		return mv;
		
	}
	
	
	
	
	@RequestMapping(value="/About")
	public ModelAndView about()
	{
		ModelAndView mv=new ModelAndView("page");
		mv.addObject("title","About Us");
		mv.addObject("userClickAbout",true );
	
		return mv;
		
	}
	
	
	
	@RequestMapping(value="/Contact")
	public ModelAndView contact()
	{
		ModelAndView mv=new ModelAndView("page");
		mv.addObject("title","Contact Us");
		mv.addObject("userClickContact",true );
	
		return mv;
		
	}
	
	
	
	

		/*
		 * Methods to load all products and based on category
		 */
	@RequestMapping(value= "/show/all/products")
	public ModelAndView showAllProducts()
	{
		ModelAndView mv=new ModelAndView("page");
		mv.addObject("title","All Products");
		mv.addObject("userClickAllProducts",true );
	
		//passing the list of categories
		mv.addObject("categories", categoryDAO.list());
		
		return mv;
		
	}
	
	
	
	
	
	@RequestMapping(value= "/show/category/{id}/products")
	public ModelAndView showCategoryProducts(@PathVariable("id") int id)
	{
		ModelAndView mv=new ModelAndView("page");
		
		
		
		//categoryDAO to fetch a single category
		Category category=null;
		
		category=categoryDAO.get(id);
		
		mv.addObject("title",category.getName());
		mv.addObject("userClickCategoryProducts", true );
		
		//passing single category obj
		mv.addObject("category",category);
		
		
		//passing the list of categories
		mv.addObject("categories", categoryDAO.list());
		
		return mv;
		
	}
	
	
	
}

package com.dessert.onlinedessertbackend.test;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.dessert.onlinedessertbackend.dao.CategoryDAO;
import com.dessert.onlinnedessertbackend.dto.Category;

public class CategoryTestCase 

{

	private static AnnotationConfigApplicationContext context;
	
	private static CategoryDAO categoryDAO;
	
	private Category category;
	
	
	@BeforeClass
	public static void init()
	{
		
		context=new AnnotationConfigApplicationContext();
		context.scan("com");
		context.refresh();
		
		
		categoryDAO =(CategoryDAO)context.getBean("categoryDAO");
		
	}
	
	/*@Test
	public void testAddCategory()
	{
		
		category=new Category();
		category.setName("CreamCakes");
		category.setDescription("Delicious");
		category.setImageURL("1.png");
		assertEquals("Successfully added a category inside the table",true,categoryDAO.add(category));
		
		
	}
	*/
	/*
	@Test
	public void testGetCategory() 
	
	{
		category=categoryDAO.get(2);
		assertEquals("Successfully fetched a single category from the table","CreamCakes",category.getName());
		
		
		
		
		
		
	}
	*/
	
	
	/*
	@Test
	public void testUpdateCategory() 
	
	{
		category=categoryDAO.get(2);
		category.setName("Cakes");
		assertEquals("Successfully updated a single category in the table",true,categoryDAO.update(category));
		
		
		
		
		
		
	}
	*/
	
	/*@Test
	public void testDeleteCategory() 
	
	{
		category=categoryDAO.get(2);

		assertEquals("Successfully deleted a single category in the table",true,categoryDAO.delete(category));
		
		
		
		
		
		
	}
	*/
	
	
	/*@Test
	public void testListCategory() 
	
	{
		category=categoryDAO.get(2);

		assertEquals("Successfully fetched a list of category from the table",1,categoryDAO.list().size());
		
		
		
		
		
		
	}
	*/
	@Test
	public void testCRUDCategory()
	{
		
		
		category=new Category();
		category.setName("Pastries");
		category.setDescription("Delicious Pastries");
		category.setImageURL("11.png");
		assertEquals("Successfully added a category inside the table",true,categoryDAO.add(category));
		
		
		category=new Category();
		category.setName("Rolls");
		category.setDescription("Delicious");
		category.setImageURL("112.png");
		assertEquals("Successfully added a category inside the table",true,categoryDAO.add(category));
		
		//fetching and updating category
		category=categoryDAO.get(2);
		category.setName("Cakes");
		assertEquals("Successfully updated a single category in the table",true,categoryDAO.update(category));
		
		
		
		//delete the category
		assertEquals("Successfully deleted a single category in the table",true,categoryDAO.delete(category));
		
		
		//fetching list
		assertEquals("Successfully fetched a list of category from the table",1,categoryDAO.list().size());
		
		
		
		
		
	}
	
	
	
}

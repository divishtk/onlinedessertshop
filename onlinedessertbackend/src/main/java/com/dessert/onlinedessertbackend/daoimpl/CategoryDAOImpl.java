package com.dessert.onlinedessertbackend.daoimpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.dessert.onlinedessertbackend.dao.CategoryDAO;
import com.dessert.onlinnedessertbackend.dto.Category;




@Repository("categoryDAO")
public class CategoryDAOImpl implements CategoryDAO 
{

	private static List<Category> categories=new ArrayList<>();
	
	static 
	{
		Category category=new Category();
		//adding first category
		
		category.setId(1);
		category.setName("Pastries");
		category.setDescription("Delicious");
		category.setImageURL("1.png");
		
		categories.add(category);
		
		
		//adding second category
				category=new Category();
				category.setId(2);
				category.setName("Desserts");
				category.setDescription("Delicious");
				category.setImageURL("2.png");	
				categories.add(category);
				
				
				//adding third category
				category=new Category();
				category.setId(3);
				category.setName("MilkShakes");
				category.setDescription("Delicious");
				category.setImageURL("3.png");
				
				categories.add(category);	
		

				//adding third category
				category=new Category();
				category.setId(4);
				category.setName("Indian Special");
				category.setDescription("Delicious");
				category.setImageURL("3.png");
				
				categories.add(category);	
		
	}

	
	@Override
	public List<Category> list() 
	{
		
		return categories;
	}


	@Override
	public Category get(int id) {
		
		//enhanced for  loop
		
		for(Category category : categories)
		{
			
			
			if(category.getId() == id) return category;
			
			
		}
		
		return null;
	}

}

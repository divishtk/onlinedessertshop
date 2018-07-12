package com.dessert.onlinedessertbackend.daoimpl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.dessert.onlinedessertbackend.dao.CategoryDAO;
import com.dessert.onlinnedessertbackend.dto.Category;




@Repository("categoryDAO")
@Transactional
public class CategoryDAOImpl implements CategoryDAO 
{
	
	
	
	@Autowired
	private SessionFactory sessionFactory;
	
	
	
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
		
		String selectActiveCategory="From Category WHERE active= :active";
		
		Query query =sessionFactory.getCurrentSession().createQuery(selectActiveCategory);
		
		query.setParameter("active", true);
		return query.getResultList();
	}


	
	/*
Getting single cat based on ID
	 */
	@Override
	public Category get(int id) {
		

		return sessionFactory.getCurrentSession().get(Category.class, Integer.valueOf(id));
		
	}


	@Override

	public boolean add(Category category) 
	{
		try
		{
			
			//add the category to DB table
			sessionFactory.getCurrentSession().persist(category);
			
			return true;
			
			
		}
		catch(Exception ex)
		{
			
			ex.printStackTrace();
			return false;
		}
		
		
	}


	
	
	
	
	/*updateing a single category */
	@Override
	public boolean update(Category category) 
	
	{
	
		try
		{
			
			//update the category to DB table
			sessionFactory.getCurrentSession().update(category);
			
			return true;
			
			
		}
		catch(Exception ex)
		{
			
			ex.printStackTrace();
			return false;
		}
	}


	
	
	
	
	
	@Override
	public boolean delete(Category category) 
	
	{
		category.setActive(false);
		try
		{
			
			//add the category to DB table
			sessionFactory.getCurrentSession().update(category);
			
			return true;
			
			
		}
		catch(Exception ex)
		{
			
			ex.printStackTrace();
			return false;
		}	
	}

}

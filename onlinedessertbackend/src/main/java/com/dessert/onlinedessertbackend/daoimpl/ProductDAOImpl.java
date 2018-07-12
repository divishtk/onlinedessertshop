package com.dessert.onlinedessertbackend.daoimpl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.dessert.onlinedessertbackend.dao.ProductDAO;
import com.dessert.onlinnedessertbackend.dto.Product;


@Repository("productDAO")
@Transactional
public class ProductDAOImpl implements ProductDAO

{

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public Product get(int productId)

	{
		try {
			return sessionFactory.getCurrentSession().get(Product.class, Integer.valueOf(productId));
		}
		catch (Exception ex) 
		{
			ex.printStackTrace();
		}

		return null;
	}

	@Override
	public List<Product> list()

	{
		return sessionFactory.getCurrentSession().createQuery("FROM Product", Product.class).getResultList();

	}

	@Override
	public boolean add(Product product)

	{

		try {
			sessionFactory.getCurrentSession().persist(product);
			return true;
		} 
		catch (Exception ex) 
		{
			ex.printStackTrace();
		}
		return false;
	}

	
	
	
	@Override
	public boolean update(Product product)

	{

		try {
			sessionFactory.getCurrentSession().update(product);
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean delete(Product product)

	{

		try {

			product.setActive(false);
			// call the update method
			return this.update(product);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return false;
	}

	@Override
	public List<Product> listActiveProducts()

	{
		String selectActiveProducts="From Product WHERE active= :active";
		return sessionFactory.getCurrentSession().createQuery(selectActiveProducts,Product.class).setParameter("active",true).getResultList();

		
	}

	@Override
	public List<Product> listActiveProductsByCategory(int categoryId)

	{

		String selectActiveProductsByCategory="From Product WHERE active= :active AND categoryId= :categoryId";
		return sessionFactory.getCurrentSession().createQuery(selectActiveProductsByCategory,Product.class).setParameter("active",true)
				.setParameter("categoryId", categoryId)
				.getResultList();

	}

	@Override
	public List<Product> getLatestActiveProducts(int count)

	{
		return sessionFactory
				.getCurrentSession()
					.createQuery("FROM Product WHERE active = :active ORDER BY id", Product.class)
						.setParameter("active", true)
							.setFirstResult(0)
							.setMaxResults(count)
								.getResultList();	
	}

}

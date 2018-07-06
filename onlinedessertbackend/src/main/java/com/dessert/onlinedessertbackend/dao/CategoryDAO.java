package com.dessert.onlinedessertbackend.dao;

import java.util.List;

import com.dessert.onlinnedessertbackend.dto.Category;

public interface CategoryDAO 
{
List<Category> list();
Category get(int id);
}

package com.fl.mapper;


import com.fl.domain.Category;

import java.util.List;

@MyBatisRepository
public interface CategoryMapper {

	List<Category> getCategories();
}

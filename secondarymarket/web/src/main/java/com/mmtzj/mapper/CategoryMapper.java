package com.mmtzj.mapper;


import com.mmtzj.domain.Category;

import java.util.List;

@MyBatisRepository
public interface CategoryMapper {

	List<Category> getCategories();
}

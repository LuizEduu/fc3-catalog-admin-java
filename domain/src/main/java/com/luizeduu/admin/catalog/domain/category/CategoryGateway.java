package com.luizeduu.admin.catalog.domain.category;

import com.luizeduu.admin.catalog.domain.pagination.Pagination;

import java.util.List;
import java.util.Optional;

public interface CategoryGateway {
	Category create(Category aCategory);

	void deleteById(CategoryId anId);

	Optional<Category> findById(CategoryId anId);

	Category update(Category aCategory);

	Pagination<Category> findAll(CategorySearchQuery aQuery);
}

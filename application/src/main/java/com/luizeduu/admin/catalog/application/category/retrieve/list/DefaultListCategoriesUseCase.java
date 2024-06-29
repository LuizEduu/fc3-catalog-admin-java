package com.luizeduu.admin.catalog.application.category.retrieve.list;

import com.luizeduu.admin.catalog.domain.category.CategoryGateway;
import com.luizeduu.admin.catalog.domain.category.CategorySearchQuery;
import com.luizeduu.admin.catalog.domain.pagination.Pagination;

import java.util.Objects;

public class DefaultListCategoriesUseCase extends ListCategoriesUseCase {

	private  final CategoryGateway categoryGateway;

	public DefaultListCategoriesUseCase(final CategoryGateway categoryGateway) {
		this.categoryGateway = Objects.requireNonNull(categoryGateway);
	}

	@Override
	public Pagination<CategoryListOutput> execute(final CategorySearchQuery anIn) {
		return this.categoryGateway.findAll(anIn).map(CategoryListOutput::from);
	}
}

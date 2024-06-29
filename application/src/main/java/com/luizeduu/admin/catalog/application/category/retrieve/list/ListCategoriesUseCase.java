package com.luizeduu.admin.catalog.application.category.retrieve.list;

import com.luizeduu.admin.catalog.application.UseCase;
import com.luizeduu.admin.catalog.domain.category.CategorySearchQuery;
import com.luizeduu.admin.catalog.domain.pagination.Pagination;

public abstract class ListCategoriesUseCase extends UseCase<CategorySearchQuery, Pagination<CategoryListOutput>> {
}

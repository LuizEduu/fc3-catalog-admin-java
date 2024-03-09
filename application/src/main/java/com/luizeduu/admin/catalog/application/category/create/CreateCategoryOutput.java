package com.luizeduu.admin.catalog.application.category.create;

import com.luizeduu.admin.catalog.domain.category.Category;
import com.luizeduu.admin.catalog.domain.category.CategoryId;

public record CreateCategoryOutput(
	CategoryId id
) {
	public static CreateCategoryOutput from(final Category aCategory) {
			return new CreateCategoryOutput(aCategory.getId());
	}
}

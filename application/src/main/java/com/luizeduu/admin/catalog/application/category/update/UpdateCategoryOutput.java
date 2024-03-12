package com.luizeduu.admin.catalog.application.category.update;

import com.luizeduu.admin.catalog.domain.category.Category;
import com.luizeduu.admin.catalog.domain.category.CategoryId;

public record UpdateCategoryOutput(
	CategoryId id
) {

	public static UpdateCategoryOutput from(final Category aCategory) {
		return  new UpdateCategoryOutput(aCategory.getId());
	}
}

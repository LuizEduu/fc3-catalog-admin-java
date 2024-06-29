package com.luizeduu.admin.catalog.application.category.delete;
import com.luizeduu.admin.catalog.domain.category.CategoryGateway;
import com.luizeduu.admin.catalog.domain.category.CategoryID;

public  class DefaultDeleteCategoryUseCase extends DeleteCategoryUseCase {

	private final CategoryGateway categoryGateway;

	public DefaultDeleteCategoryUseCase(final CategoryGateway categoryGateway) {
		this.categoryGateway = categoryGateway;
	}

	@Override
	public void execute(final String id) {
		this.categoryGateway.deleteById(CategoryID.from(id));
	}
}

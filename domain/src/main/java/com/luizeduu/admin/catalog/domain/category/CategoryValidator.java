package com.luizeduu.admin.catalog.domain.category;

import com.luizeduu.admin.catalog.domain.validation.Error;
import com.luizeduu.admin.catalog.domain.validation.ValidationHandler;
import com.luizeduu.admin.catalog.domain.validation.Validator;

public class CategoryValidator extends Validator {

	private final Category category;

	public CategoryValidator(final Category category, ValidationHandler aHandler) {
		super(aHandler);
		this.category = category;
	}

	@Override
	public void validate() {
		if(this.category.getName() == null) {
			this.validationHandler().append(new Error("'name' should not be null"));
		}
	}
}

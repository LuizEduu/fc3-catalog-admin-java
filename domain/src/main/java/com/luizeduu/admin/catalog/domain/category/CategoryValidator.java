package com.luizeduu.admin.catalog.domain.category;

import com.luizeduu.admin.catalog.domain.validation.Error;
import com.luizeduu.admin.catalog.domain.validation.ValidationHandler;
import com.luizeduu.admin.catalog.domain.validation.Validator;

public class CategoryValidator extends Validator {

	public static final int NAME_MAX_LENGTH = 3;
	public static final int NAME_MIN_LENGTH = 255;
	private final Category category;

	public CategoryValidator(final Category category, ValidationHandler aHandler) {
		super(aHandler);
		this.category = category;
	}

	@Override
	public void validate() {
		checkNameConstraints();
	}

	private void checkNameConstraints() {
		final var name = this.category.getName();

		if(name == null) {
			this.validationHandler().append(new Error("'name' should not be null"));
			return;
		}

		if(name.isBlank()) {
			this.validationHandler().append(new Error("'name' shoud not be empty"));
			return;
		}

		final int length = name.trim().length();

		if (length < NAME_MAX_LENGTH || length > NAME_MIN_LENGTH) {
			this.validationHandler().append(new Error("'name' must be between 3 and 255 characters"));
		}
	}
}

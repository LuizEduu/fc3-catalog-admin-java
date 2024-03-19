package com.luizeduu.admin.catalog.application.category.update;

import com.luizeduu.admin.catalog.domain.category.Category;
import com.luizeduu.admin.catalog.domain.category.CategoryGateway;
import com.luizeduu.admin.catalog.domain.category.CategoryID;
import com.luizeduu.admin.catalog.domain.exceptions.DomainException;
import com.luizeduu.admin.catalog.domain.validation.Error;
import com.luizeduu.admin.catalog.domain.validation.handler.Notification;
import io.vavr.control.Either;

import java.util.Objects;

import static io.vavr.API.Left;
import static io.vavr.API.Try;

public class DefaultUpdateCategoryUseCase extends UpdateCategoryUseCase{

	private final CategoryGateway categoryGateway;

	public DefaultUpdateCategoryUseCase(CategoryGateway categoryGateway) {
		this.categoryGateway = Objects.requireNonNull(categoryGateway);
	}

	@Override
	public Either<Notification, UpdateCategoryOutput> execute(UpdateCategoryCommand aCommand) {
		final var anId = CategoryID.from(aCommand.id());
		String aName = aCommand.name();
		String aDescription = aCommand.description();
		boolean isActive = aCommand.isActive();

		Category aCategory = this.categoryGateway.findById(anId)
			.orElseThrow(() -> notFound(anId));

		final var notification = Notification.create();

		aCategory.update(aName, aDescription, isActive)
			.validate(notification);

		return notification.hasError() ? Left(notification) : update(aCategory);

	}

	private Either<Notification, UpdateCategoryOutput> update(Category aCategory) {
		return Try(() -> this.categoryGateway.update(aCategory))
			.toEither()
			.bimap(Notification::create, UpdateCategoryOutput::from);
	}

	private static DomainException notFound(final CategoryID anId) {
		return DomainException.with(new Error("Category with ID %s was not found".formatted(anId.getValue())));
	}
}

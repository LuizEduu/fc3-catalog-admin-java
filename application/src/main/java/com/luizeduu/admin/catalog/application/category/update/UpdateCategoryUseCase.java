package com.luizeduu.admin.catalog.application.category.update;

import com.luizeduu.admin.catalog.application.UseCase;
import com.luizeduu.admin.catalog.domain.category.Category;
import com.luizeduu.admin.catalog.domain.validation.handler.Notification;
import io.vavr.control.Either;

public abstract class UpdateCategoryUseCase extends UseCase<UpdateCategoryCommand, Either<Notification, UpdateCategoryOutput>> {}

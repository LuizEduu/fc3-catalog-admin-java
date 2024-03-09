package com.luizeduu.admin.catalog.application.category.create;


import com.luizeduu.admin.catalog.application.UseCase;
import com.luizeduu.admin.catalog.domain.validation.handler.Notification;
import io.vavr.control.Either;

public abstract class CreateCategoryUseCase extends UseCase<CreateCategoryCommand, Either<Notification, CreateCategoryOutput>> {}

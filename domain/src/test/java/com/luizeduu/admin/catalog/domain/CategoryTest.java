package com.luizeduu.admin.catalog.domain;

import com.luizeduu.admin.catalog.domain.category.Category;
import com.luizeduu.admin.catalog.domain.exceptions.DomainException;
import com.luizeduu.admin.catalog.domain.validation.handler.ThrowsValidationHandler;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.Instant;

public class CategoryTest {
	@Test
	@DisplayName("shoud be able to return a instance of category")
	public void givenAValidParams_whenCallNewCategory_thenInstantiateACategory() {
		final var expectedName = "filmes";
		final var expectedDescription = "A categoria mais assistida";
		final var expectedIsActive = true;

		var createdCategory = Category.newCategory(expectedName, expectedDescription, expectedIsActive);

		Assertions.assertNotNull(createdCategory);
		Assertions.assertNotNull(createdCategory.getId());
		Assertions.assertEquals(expectedName, createdCategory.getName());
		Assertions.assertEquals(expectedDescription, createdCategory.getDescription());
		Assertions.assertEquals(expectedIsActive, createdCategory.isActive());
		Assertions.assertNotNull(createdCategory.getCreatedAt());
		Assertions.assertNotNull(createdCategory.getUpdatedAt());
		Assertions.assertNull(createdCategory.getDeletedAt());
	}

	@Test
	@DisplayName("shoud be able to return error when name is invalid")
	public void givenAndInvalidNullName_whenCallNewCategoryAndValidate_thenShouldRecieveError() {
		final var expectedErrorMessage = "'name' should not be null";
		final var expectedErrorCount = 1;
		final var expectedDescription = "A categoria mais assistida";
		final var expectedIsActive = true;

		var createdCategory = Category.newCategory(null, expectedDescription, expectedIsActive);

		final var actualException = Assertions.assertThrows(DomainException.class, () -> createdCategory.validate(new ThrowsValidationHandler()));

		Assertions.assertEquals(expectedErrorCount, actualException.getErrors().size());
		Assertions.assertEquals(expectedErrorMessage, actualException.getErrors().get(0).message());
	}

	@Test
	@DisplayName("shoud be able to return error when name is empty")
	public void givenAnInvalidNullName_whenCallNewCategoryAndValidate_thenShoudRecieveError() {
			final var expectedErrorMessage = "'name' shoud not be empty";
			final var expectedErrorCount = 1;
			final var expectedDescription = "A categoria mais buscada";
			final var expectedIsActive = true;
			final var expectedName = " ";

			var createdCategory = Category.newCategory(expectedName, expectedDescription, expectedIsActive);

			final var exception = Assertions.assertThrows(DomainException.class, () -> createdCategory.validate(new ThrowsValidationHandler()));

			Assertions.assertEquals(expectedErrorCount, exception.getErrors().size());
			Assertions.assertEquals(expectedErrorMessage, exception.getErrors().get(0).message());
	}

	@Test
	@DisplayName("shoud be able to return error when name length less 3 characters")
	public void givenAnInvalidNameLengthLessThan3_whenCallNewCategoryAndValidate_thenShoudRecieveError() {
		final var expectedErrorMessage = "'name' must be between 3 and 255 characters";
		final var expectedErrorCount = 1;
		final var expectedDescription = "A categoria mais buscada";
		final var expectedIsActive = true;
		final var expectedName = "Fo ";

		var createdCategory = Category.newCategory(expectedName, expectedDescription, expectedIsActive);

		final var exception = Assertions.assertThrows(DomainException.class, () -> createdCategory.validate(new ThrowsValidationHandler()));

		Assertions.assertEquals(expectedErrorCount, exception.getErrors().size());
		Assertions.assertEquals(expectedErrorMessage, exception.getErrors().get(0).message());
	}

	@Test
	@DisplayName("shoud be able to return error when name length more 255 characters")
	public void givenAnInvalidNameLengthMoreThan255_whenCallNewCategoryAndValidate_thenShoudRecieveError() {
		final var expectedErrorMessage = "'name' must be between 3 and 255 characters";
		final var expectedErrorCount = 1;
		final var expectedDescription = "A categoria mais buscada";
		final var expectedIsActive = true;
		final var expectedName = "Foo".repeat(255);

		var createdCategory = Category.newCategory(expectedName, expectedDescription, expectedIsActive);

		final var exception = Assertions.assertThrows(DomainException.class, () -> createdCategory.validate(new ThrowsValidationHandler()));

		Assertions.assertEquals(expectedErrorCount, exception.getErrors().size());
		Assertions.assertEquals(expectedErrorMessage, exception.getErrors().get(0).message());
	}

	@Test
	@DisplayName("shoud be able to return a new category with empty description")
	public void givenAValidEmptyDescription_whenCallNewCategoryAndValidate_thenShouldReceiveOK() {
		final var expectedDescription = " ";
		final var expectedIsActive = true;
		final var expectedName = "Filmes";

		var createdCategory = Category.newCategory(expectedName, expectedDescription, expectedIsActive);

		Assertions.assertDoesNotThrow(() ->  createdCategory.validate(new ThrowsValidationHandler()));
		Assertions.assertNotNull(createdCategory);
		Assertions.assertNotNull(createdCategory.getId());
		Assertions.assertEquals(expectedName, createdCategory.getName());
		Assertions.assertEquals(expectedDescription, createdCategory.getDescription());
		Assertions.assertEquals(expectedIsActive, createdCategory.isActive());
		Assertions.assertNotNull(createdCategory.getCreatedAt());
		Assertions.assertNotNull(createdCategory.getUpdatedAt());
		Assertions.assertNull(createdCategory.getDeletedAt());
	}

	@Test
	@DisplayName("shoud be able to create a new category with isActive false")
	public void givenAValidFalseIsActive_whenCallNewCategoryAndValidate_thenShouldReceiveOK() {
		final var expectedName = "Filmes";
		final var expectedDescription = "A categoria mais assistida";
		final var expectedIsActive = false;

		final var actualCategory =
			Category.newCategory(expectedName, expectedDescription, expectedIsActive);

		Assertions.assertDoesNotThrow(() -> actualCategory.validate(new ThrowsValidationHandler()));

		Assertions.assertNotNull(actualCategory);
		Assertions.assertNotNull(actualCategory.getId());
		Assertions.assertEquals(expectedName, actualCategory.getName());
		Assertions.assertEquals(expectedDescription, actualCategory.getDescription());
		Assertions.assertEquals(expectedIsActive, actualCategory.isActive());
		Assertions.assertNotNull(actualCategory.getCreatedAt());
		Assertions.assertNotNull(actualCategory.getUpdatedAt());
		Assertions.assertNotNull(actualCategory.getDeletedAt());
	}

	@Test
	public void givenAValidActiveCategory_whenCallDeactivate_thenReturnCategoryInactivate(){
		final var expectedName = "Filmes";
		final var expectedDescription = "A categoria mais assistida";
		final var expectedIsActive = false;

		final var aCategory =
			Category.newCategory(expectedName, expectedDescription, true);

		Assertions.assertDoesNotThrow(() -> aCategory.validate(new ThrowsValidationHandler()));

		final var createdAt = aCategory.getCreatedAt();
		final var updatedAt = aCategory.getUpdatedAt();

		Assertions.assertNull(aCategory.getDeletedAt());
		Assertions.assertTrue(aCategory.isActive());

		final var actualCategory = aCategory.deactivate();

		Assertions.assertDoesNotThrow(() -> actualCategory.validate(new ThrowsValidationHandler()));

		Assertions.assertEquals(aCategory.getId(), actualCategory.getId());
		Assertions.assertEquals(aCategory.getName(), actualCategory.getName());
		Assertions.assertEquals(aCategory.getDescription(), actualCategory.getDescription());
		Assertions.assertEquals(createdAt, actualCategory.getCreatedAt());
		Assertions.assertTrue(actualCategory.getUpdatedAt().isAfter(updatedAt));
		Assertions.assertEquals(expectedIsActive, actualCategory.isActive());
		Assertions.assertNotNull(actualCategory.getDeletedAt());
	}

	@Test
	public void givenAValidInactiveCategory_whenCallActivate_thenReturnCategoryActivate(){
		final var expectedName = "Filmes";
		final var expectedDescription = "A categoria mais assistida";
		final var expectedIsActive = true;

		final var aCategory =
			Category.newCategory(expectedName, expectedDescription, false);

		Assertions.assertDoesNotThrow(() -> aCategory.validate(new ThrowsValidationHandler()));

		final var createdAt = aCategory.getCreatedAt();
		final var updatedAt = aCategory.getUpdatedAt();

		Assertions.assertNotNull(aCategory.getDeletedAt());
		Assertions.assertFalse(aCategory.isActive());

		final var actualCategory = aCategory.activate();

		Assertions.assertDoesNotThrow(() -> actualCategory.validate(new ThrowsValidationHandler()));

		Assertions.assertEquals(aCategory.getId(), actualCategory.getId());
		Assertions.assertEquals(aCategory.getName(), actualCategory.getName());
		Assertions.assertEquals(aCategory.getDescription(), actualCategory.getDescription());
		Assertions.assertEquals(createdAt, actualCategory.getCreatedAt());
		Assertions.assertTrue(actualCategory.getUpdatedAt().isAfter(updatedAt));
		Assertions.assertEquals(expectedIsActive, actualCategory.isActive());
		Assertions.assertNull(actualCategory.getDeletedAt());
	}

	@Test
	public void givenAValidCategory_whenCallUpdate_thenReturnCategoryUpdated() {
		final var expectedName = "Filmes";
		final var expectedDescription = "A categoria mais assistida";
		final var expectedIsActive = true;

		final var aCategory =
			Category.newCategory("Film", "A categoria", false);

		final var createdAt = aCategory.getCreatedAt();
		final var updatedAt = aCategory.getUpdatedAt();

		Assertions.assertDoesNotThrow(() -> aCategory.validate(new ThrowsValidationHandler()));

		Assertions.assertNotNull(aCategory.getDeletedAt());
		Assertions.assertFalse(aCategory.isActive());

		final var actualCategory = aCategory.update(expectedName, expectedDescription, expectedIsActive);

		Assertions.assertDoesNotThrow(() -> actualCategory.validate(new ThrowsValidationHandler()));

		Assertions.assertEquals(aCategory.getId(), actualCategory.getId());
		Assertions.assertEquals(aCategory.getName(), actualCategory.getName());
		Assertions.assertEquals(aCategory.getDescription(), actualCategory.getDescription());
		Assertions.assertEquals(createdAt, actualCategory.getCreatedAt());
		Assertions.assertTrue(actualCategory.getUpdatedAt().isAfter(updatedAt));
		Assertions.assertEquals(expectedIsActive, actualCategory.isActive());
		Assertions.assertNull(actualCategory.getDeletedAt());
	}

	@Test
	public void givenAValidCategory_whenCallUpdateToInactive_thenReturnCategoryUpdated() {
		final var expectedName = "Filmes";
		final var expectedDescription = "A categoria mais assistida";
		final var expectedIsActive = false;

		final var aCategory =
			Category.newCategory("Film", "A categoria", true);

		final var createdAt = aCategory.getCreatedAt();
		final var updatedAt = aCategory.getUpdatedAt();

		Assertions.assertDoesNotThrow(() -> aCategory.validate(new ThrowsValidationHandler()));

		Assertions.assertNull(aCategory.getDeletedAt());
		Assertions.assertTrue(aCategory.isActive());

		final var actualCategory = aCategory.update(expectedName, expectedDescription, expectedIsActive);

		Assertions.assertDoesNotThrow(() -> actualCategory.validate(new ThrowsValidationHandler()));

		Assertions.assertEquals(aCategory.getId(), actualCategory.getId());
		Assertions.assertEquals(aCategory.getName(), actualCategory.getName());
		Assertions.assertEquals(aCategory.getDescription(), actualCategory.getDescription());
		Assertions.assertEquals(createdAt, actualCategory.getCreatedAt());
		Assertions.assertTrue(actualCategory.getUpdatedAt().isAfter(updatedAt));
		Assertions.assertEquals(expectedIsActive, actualCategory.isActive());
		Assertions.assertNotNull(actualCategory.getDeletedAt());
		Assertions.assertFalse(actualCategory.isActive());
	}

	@Test
	public void givenAValidCategory_whenCallUpdateWithInvalidParams_thenReturnCategoryUpdated() {
		final String expectedName = null;
		final var expectedDescription = "A categoria mais assistida";
		final var expectedIsActive = true;

		final var aCategory =
			Category.newCategory("Filmes", "A categoria mais assistida", true);

		final var createdAt = aCategory.getCreatedAt();
		final var updatedAt = aCategory.getUpdatedAt();

		Assertions.assertDoesNotThrow(() -> aCategory.validate(new ThrowsValidationHandler()));

		final var actualCategory = aCategory.update(expectedName, expectedDescription, expectedIsActive);

		Assertions.assertEquals(aCategory.getId(), actualCategory.getId());
		Assertions.assertEquals(aCategory.getName(), actualCategory.getName());
		Assertions.assertEquals(aCategory.getDescription(), actualCategory.getDescription());
		Assertions.assertEquals(createdAt, actualCategory.getCreatedAt());
		Assertions.assertTrue(actualCategory.getUpdatedAt().isAfter(updatedAt));
		Assertions.assertEquals(expectedIsActive, actualCategory.isActive());
		Assertions.assertNull(actualCategory.getDeletedAt());
		Assertions.assertTrue(actualCategory.isActive());
	}

}

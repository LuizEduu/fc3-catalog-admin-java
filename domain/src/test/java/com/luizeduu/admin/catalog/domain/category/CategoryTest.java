package com.luizeduu.admin.catalog.domain.category;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CategoryTest {
	@Test
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
}

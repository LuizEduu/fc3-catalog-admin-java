package com.luizeduu.admin.catalog.application.category.delete;

import com.luizeduu.admin.catalog.application.category.delete.DefaultDeleteCategoryUseCase;
import com.luizeduu.admin.catalog.domain.category.Category;
import com.luizeduu.admin.catalog.domain.category.CategoryGateway;
import com.luizeduu.admin.catalog.domain.category.CategoryID;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DeleteCategoryUseCaseTest {

	@InjectMocks
	private DefaultDeleteCategoryUseCase useCase;

	@Mock
	private CategoryGateway categoryGateway;

	@BeforeEach
	void cleanUp() {
		reset(categoryGateway);
	}

	@Test
	public void givenAValidId_whenCallsDeleteCategory_shouldBeOk() {
		final var aCategory = Category.newCategory("filmes", "A categoria mais assistida", true);
		final var expectedId = aCategory.getId();

		doNothing().when(categoryGateway).deleteById(eq(expectedId));

		Assertions.assertDoesNotThrow(() -> useCase.execute(expectedId.getValue()));
	}

	@Test
	public void givenAInvalidId_whenCallsDeleteCategory_shouldBeOk() {
		final var expectedId = CategoryID.from("123");

		doNothing().when(categoryGateway).deleteById(eq(expectedId));

		Assertions.assertDoesNotThrow(() -> useCase.execute(expectedId.getValue()));
	}

	@Test
	public void givenAValidId_whenGatewayThrowsException_shouldReturnException() {
		final var aCategory = Category.newCategory("filmes", "A categoria mais assistida", true);
		final var expectedId = aCategory.getId();

		doThrow(new IllegalStateException("Gateway error")).when(categoryGateway).deleteById(eq(expectedId));

		Assertions.assertThrows(IllegalStateException.class, ()-> useCase.execute(expectedId.getValue()));
	}


}

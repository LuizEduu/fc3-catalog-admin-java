package com.luizeduu.admin.catalog.application.category.get;

import com.luizeduu.admin.catalog.application.category.retrieve.get.DefaultGetCategoryByIdUseCase;
import com.luizeduu.admin.catalog.domain.category.Category;
import com.luizeduu.admin.catalog.domain.category.CategoryGateway;
import com.luizeduu.admin.catalog.domain.category.CategoryID;
import com.luizeduu.admin.catalog.domain.exceptions.DomainException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GetCategoryByIdUseCaseTest {

	@InjectMocks
	private DefaultGetCategoryByIdUseCase useCase;

	@Mock
	private CategoryGateway categoryGateway;

	@BeforeEach
	void cleanUp() {
		reset(categoryGateway);
	}

	@Test
	public void givenAValidId_whenCallsGetCategory_shouldReturnCategory() {
		final var expectedName = "Filmes";
		final var expectedDescription = "A categoria mais assistida";
		final var expectedIsActive = true;

		final var aCategory = Category.newCategory(expectedName, expectedDescription, expectedIsActive);

		final var expectedId = aCategory.getId();

		when(categoryGateway.findById(Mockito.eq(expectedId)))
			.thenReturn(Optional.of(aCategory.clone()));

		final var actualCategory = useCase.execute(expectedId.getValue());

		Assertions.assertEquals(expectedId, actualCategory.id());
		Assertions.assertEquals(expectedName, actualCategory.name());
		Assertions.assertEquals(expectedDescription, actualCategory.description());
		Assertions.assertEquals(expectedIsActive, actualCategory.isActive());
		Assertions.assertEquals(aCategory.getCreatedAt(), actualCategory.createdAt());
		Assertions.assertEquals(aCategory.getUpdatedAt(), actualCategory.updatedAt());
		Assertions.assertEquals(aCategory.getDeletedAt(), actualCategory.deletedAt());

	}

	@Test
	public void givenAInvalidId_whenCallsGetCategory_shouldReturnNotFound() {
		final var expectedId = CategoryID.from("invalidId");
		final var expectedErrorMessage = "Category with ID %s was not found".formatted(expectedId.getValue());


		when(categoryGateway.findById(Mockito.eq(expectedId)))
			.thenReturn(Optional.empty());

		final var actualException = Assertions.assertThrows(
			DomainException.class,
			() -> useCase.execute(expectedId.getValue())
		);

		Assertions.assertEquals(expectedErrorMessage, actualException.getMessage());
	}


	@Test
	public void givenAValidId_whenGatewayThrowsException_shouldReturnException() {
		final var expectedErrorMessage = "GatewayError";
		final var expectedId = CategoryID.from("invalidId");

		when(categoryGateway.findById(Mockito.eq(expectedId)))
			.thenThrow(new IllegalStateException(expectedErrorMessage));

		final var actualException = Assertions.assertThrows(
			IllegalStateException.class,
			() -> useCase.execute(expectedId.getValue())
		);

		Assertions.assertEquals(expectedErrorMessage, actualException.getMessage());
	}
}

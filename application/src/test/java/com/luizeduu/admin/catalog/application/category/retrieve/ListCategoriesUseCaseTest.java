package com.luizeduu.admin.catalog.application.category.retrieve;

import com.luizeduu.admin.catalog.application.category.retrieve.list.CategoryListOutput;
import com.luizeduu.admin.catalog.application.category.retrieve.list.DefaultListCategoriesUseCase;
import com.luizeduu.admin.catalog.domain.category.Category;
import com.luizeduu.admin.catalog.domain.category.CategoryGateway;
import com.luizeduu.admin.catalog.domain.category.CategorySearchQuery;
import com.luizeduu.admin.catalog.domain.pagination.Pagination;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ListCategoriesUseCaseTest {

	@InjectMocks
	private DefaultListCategoriesUseCase useCase;

	@Mock
	private CategoryGateway categoryGateway;

	@BeforeEach
	void cleanUp() {
		Mockito.reset(categoryGateway);
	}

	@Test
	public void givenAValidQuery_whenCallsListCategories_thenShouldReturnCategories() {
		List<Category> categories = List.of(Category.newCategory("Filmes", null, true), Category.newCategory("Series", null, false));

		final var expectedPage = 0;
		final var expectedPerPage = 10;
		final var expectedTerms = "";
		final var expectedSort = "createdAt";
		final var expectedDirection = "asc";

		CategorySearchQuery aQuery = new CategorySearchQuery(expectedPage, expectedPerPage, expectedTerms, expectedSort, expectedDirection);

		final var expectedPagination = new Pagination<>(expectedPage, expectedPerPage, categories.size(), categories);

		final var expectedItemsCount = 2;
		final var expectedResult = expectedPagination.map(CategoryListOutput::from);

		when(categoryGateway.findAll(eq(aQuery))).thenReturn(expectedPagination);

		final var actualResult = useCase.execute(aQuery);

		assertEquals(expectedItemsCount, actualResult.items().size());
		assertEquals(expectedResult, expectedResult);
		assertEquals(expectedPage, actualResult.currentPage());
		assertEquals(expectedPerPage, actualResult.perPage());
		assertEquals(categories.size(), actualResult.total());
	}

	@Test
	public void givenAValidQuery_whenHasNoResults_thenShouldReturnEmptyCategories() {
		final var categories = List.<Category>of();

		final var expectedPage = 0;
		final var expectedPerPage = 10;
		final var expectedTerms = "";
		final var expectedSort = "createdAt";
		final var expectedDirection = "asc";

		CategorySearchQuery aQuery = new CategorySearchQuery(expectedPage, expectedPerPage, expectedTerms, expectedSort, expectedDirection);

		final var expectedPagination = new Pagination<>(expectedPage, expectedPerPage, categories.size(), categories);

		final var expectedItemsCount = 0;
		final var expectedResult = expectedPagination.map(CategoryListOutput::from);

		when(categoryGateway.findAll(eq(aQuery))).thenReturn(expectedPagination);

		final var actualResult = useCase.execute(aQuery);

		assertEquals(expectedItemsCount, actualResult.items().size());
		assertEquals(expectedResult, expectedResult);
		assertEquals(expectedPage, actualResult.currentPage());
		assertEquals(expectedPerPage, actualResult.perPage());
		assertEquals(categories.size(), actualResult.total());
	}

	@Test
	public void givenAValidQuery_whenGatewayThrowsException_shouldReturnException() {
		final var expectedPage = 0;
		final var expectedPerPage = 10;
		final var expectedTerms = "";
		final var expectedSort = "createdAt";
		final var expectedDirection = "asc";

		final var expectedErrorMessage = "Gateway error";

		CategorySearchQuery aQuery = new CategorySearchQuery(expectedPage, expectedPerPage, expectedTerms, expectedSort, expectedDirection);

		when(categoryGateway.findAll(eq(aQuery))).thenThrow(new IllegalStateException(expectedErrorMessage));

		final var actualException = assertThrows(IllegalStateException.class, () -> useCase.execute(aQuery));

		assertEquals(expectedErrorMessage, actualException.getMessage());
	}
}

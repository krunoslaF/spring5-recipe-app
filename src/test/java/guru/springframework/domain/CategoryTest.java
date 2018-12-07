package guru.springframework.domain;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CategoryTest {
	
	Category category;
	
	@Before
	public void setUp() {
		category = new Category();
	}

	@Test
	public void testGetId() {
		category.setId(1234L);
		Assert.assertEquals(new Long(1234), category.getId());
	}

	@Test
	public void testGetDescription() {
		category.setDescription("Testni opis");
		Assert.assertEquals(category.getDescription(), "Testni opis");
	}

	@Test
	public void testGetRecipes() {

	}

}

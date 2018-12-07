package guru.springframework.controllers;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.HashSet;
import java.util.Set;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import guru.springframework.domain.Difficulty;
import guru.springframework.domain.Recipe;
import guru.springframework.services.RecipeServiceImpl;

public class IndexControllerTest {
	
	IndexController controller;
	
	@Mock
	RecipeServiceImpl recipeService;
	
	@Mock
	Model model;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		
		controller = new IndexController(recipeService);
		
	}
	
	@Test
	public void testMockMVC() throws Exception {
		MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
		mockMvc.perform(get("/"))
		.andExpect(status().isOk())
		.andExpect(view().name("index"));
	}

	@Test
	public void testGetIndexPage() {	
		String response = controller.getIndexPage(model);
		
		Set<Recipe> responseSet = new HashSet<>();
		Recipe prvi = new Recipe();prvi.setCookTime(123);prvi.setDifficulty(Difficulty.KIND_OF_HARD);
		Recipe drugi = new Recipe();drugi.setCookTime(312);drugi.setId(3222L);drugi.setPrepTime(23123);
		responseSet.add(prvi);
		responseSet.add(drugi);

		Assert.assertEquals("index", response);
		
		verify(recipeService, times(1)).getRecipes();
		verify(model,times(1)).addAttribute("recipes", recipeService.getRecipes());
		
		when(recipeService.getRecipes()).thenReturn(responseSet);
		
		Assert.assertEquals(2, recipeService.getRecipes().size());
	}

}

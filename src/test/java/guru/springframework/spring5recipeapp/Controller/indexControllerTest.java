package guru.springframework.spring5recipeapp.Controller;

import guru.springframework.spring5recipeapp.Services.RecipeService;
import guru.springframework.spring5recipeapp.domain.Recipe;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

class indexControllerTest {
    @Mock
RecipeService recipeService;
    indexController controller;
    @Mock
    Model model;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);

         controller =new indexController(recipeService);
    }

    @Test
    void TestMockMvc() throws Exception{
        MockMvc mockMvc= MockMvcBuilders.standaloneSetup(controller).build();
        mockMvc.perform(get("/")).andExpect(status().isOk()).
                andExpect(view().name("index"));
    }

    @Test
    void index() {
        Recipe recipe=new Recipe();
        Recipe recipe1=new Recipe();

        Set<Recipe> recipes=new HashSet<>();

        recipe1.setId(2L);
        recipes.add(recipe);
        recipes.add(recipe1);

        when(recipeService.getRecipe()).thenReturn(recipes);
        String indexNote= controller.index(model);
        ArgumentCaptor<Set<Recipe>> argumentCaptor=ArgumentCaptor.forClass(Set.class);
        assertEquals("index",indexNote);
        verify(recipeService,times(1)).getRecipe();
        verify(model,times(1)).addAttribute(eq("recipes"),argumentCaptor.capture());
        Set<Recipe> recipeSet=argumentCaptor.getValue();
        assertEquals(2,recipeSet.size());
    }
}
package guru.springframework.spring5recipeapp.Controller;

import guru.springframework.spring5recipeapp.Services.IngredientService;
import guru.springframework.spring5recipeapp.Services.RecipeService;
import guru.springframework.spring5recipeapp.Services.UomService;
import guru.springframework.spring5recipeapp.command.IngredientCommand;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class IngredientControllerTest {
    @InjectMocks
 IngredientController controller;
@Mock
   RecipeService recipeService;
@Mock
   IngredientService ingredientService;
@Mock
      UomService uomList;

  MockMvc mockMvc;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);

        controller = new IngredientController(recipeService, ingredientService,uomList);
        mockMvc= MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    void showIngredient() throws Exception{
        IngredientCommand ingredientCommand =new IngredientCommand();
        when(ingredientService.findbyRecipeIdandIngredient(anyLong(),anyLong())).
                thenReturn(ingredientCommand);

        mockMvc.perform(get("/recipe/1/ingredient/2/show"))
                .andExpect(status().isOk())

                .andExpect(view().name("recipe/ingredient/show"))
                .andExpect(model().attributeExists("ingredient"));
    }
    }

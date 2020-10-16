package guru.springframework.spring5recipeapp.Controller;

import guru.springframework.spring5recipeapp.Services.RecipeService;
import guru.springframework.spring5recipeapp.domain.Recipe;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


class RecipeControllerTest {
    MockMvc mockMvc;
    @Mock
     RecipeService recipeService;

    RecipeController recipeController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);

        recipeController=new RecipeController(recipeService);
       mockMvc= MockMvcBuilders.
               standaloneSetup(recipeController).setControllerAdvice( new ControllerExceptionHandler())
               .build();

    }



    @Test
    void showById() throws Exception {
        Recipe recipe1=new Recipe();
        recipe1.setId(1L);

        when(recipeService.findById(anyLong())).thenReturn(recipe1);
        mockMvc.perform(get("/recipe/show/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("recipe/show")).
                andExpect(model().attribute("recipe",recipeService.findById(1L)));
    }

    @Test
    void badrequestException () throws Exception {
        mockMvc.perform(get("/recipe/show/asd"))
                .andExpect(view().name("400error"))
                .andExpect(status().isBadRequest());
    }
}
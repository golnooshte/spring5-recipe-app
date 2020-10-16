package guru.springframework.spring5recipeapp.Services;

import guru.springframework.spring5recipeapp.Repositories.RecipeRepository;
import guru.springframework.spring5recipeapp.convertor.RecipeCommandToRecipe;
import guru.springframework.spring5recipeapp.convertor.RecipeToRecipeCommand;
import guru.springframework.spring5recipeapp.domain.Recipe;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RecipeServiceImpTest {
RecipeServiceImp recipeServiceImp;
@Mock
RecipeRepository recipeRepository;
    private RecipeToRecipeCommand recipeToRecipeCommand;
    private RecipeCommandToRecipe recipeCommandToRecipe;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        recipeServiceImp=new RecipeServiceImp(recipeRepository, recipeToRecipeCommand, recipeCommandToRecipe);

    }



    @Test
    void getRecipe() {
        Recipe recipe=new Recipe();
        HashSet recipes=new HashSet();
        recipes.add(recipe);
        when(recipeRepository.findAll()).thenReturn(recipes);
        Set<Recipe> recipeSet=recipeServiceImp.getRecipe();
        assertEquals(1,recipes.size());
        verify(recipeRepository,times(1)).findAll();
    }
}
package guru.springframework.spring5recipeapp.Services;

import guru.springframework.spring5recipeapp.Repositories.RecipeRepository;
import guru.springframework.spring5recipeapp.Repositories.UnitOfMeasureRepository;
import guru.springframework.spring5recipeapp.command.IngredientCommand;
import guru.springframework.spring5recipeapp.convertor.IngredientCommandToIngredient;
import guru.springframework.spring5recipeapp.convertor.IngredientToIngredientCommand;
import guru.springframework.spring5recipeapp.convertor.UnitOfMeasureToUnitofMeasureCommand;
import guru.springframework.spring5recipeapp.convertor.UniteofMeasureCommmandtoMeeasure;
import guru.springframework.spring5recipeapp.domain.Ingredient;
import guru.springframework.spring5recipeapp.domain.Recipe;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

class IngredientServiceImpTest {
    private final IngredientToIngredientCommand ingredientToIngredientCommand;
    private final IngredientCommandToIngredient ingredientCommandToIngredient;

    @Mock
    RecipeRepository recipeRepository;

    @Mock
    UnitOfMeasureRepository unitOfMeasureRepository;

    IngredientService ingredientService;

    //init converters
    public IngredientServiceImpTest() {
        this.ingredientToIngredientCommand = new IngredientToIngredientCommand(new UnitOfMeasureToUnitofMeasureCommand());
        this.ingredientCommandToIngredient = new IngredientCommandToIngredient(new UniteofMeasureCommmandtoMeeasure());
    }
    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);

        ingredientService = new IngredientServiceImp(recipeRepository,ingredientToIngredientCommand,
                unitOfMeasureRepository,ingredientCommandToIngredient);

    }

    @Test
    void findbyRecipeIdandIngredient() {
    }

    @Test
    void saveIngredientCommand() {
        IngredientCommand command = new IngredientCommand();
        command.setId(3L);
        command.setRecipeId(2L);

        Optional<Recipe> recipeOptional = Optional.of(new Recipe());

        Recipe savedRecipe = new Recipe();
        savedRecipe.addIngredient(new Ingredient());
        savedRecipe.getIngredients().iterator().next().setId(3L);

        when(recipeRepository.findById(anyLong())).thenReturn(recipeOptional);
        when(recipeRepository.save(any())).thenReturn(savedRecipe);

        //when
        IngredientCommand savedCommand = ingredientService.saveIngredientCommand(command);

        //then
        assertEquals(Long.valueOf(3L), savedCommand.getId());
        verify(recipeRepository, times(1)).findById(anyLong());
        verify(recipeRepository, times(1)).save(any(Recipe.class));

    }
}

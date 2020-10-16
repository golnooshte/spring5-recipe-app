package guru.springframework.spring5recipeapp.Services;

import guru.springframework.spring5recipeapp.command.RecipeCommand;
import guru.springframework.spring5recipeapp.domain.Recipe;

import java.util.Set;

public interface RecipeService {
    Set<Recipe> getRecipe();
    Recipe findById(Long l);
    RecipeCommand saveRecipeCommand(RecipeCommand recipe);

    Object findRecipeCommandById(Long id);

    void deleteRecipe(Long id);
}

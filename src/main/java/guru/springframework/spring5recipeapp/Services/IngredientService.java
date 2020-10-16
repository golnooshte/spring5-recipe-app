package guru.springframework.spring5recipeapp.Services;

import guru.springframework.spring5recipeapp.command.IngredientCommand;

public interface IngredientService {
     IngredientCommand findbyRecipeIdandIngredient(Long recipeid, Long ingredientId);
     IngredientCommand saveIngredientCommand(IngredientCommand command);

    void deleteByRecipeId(Long recipeId,Long ingredientId);
}

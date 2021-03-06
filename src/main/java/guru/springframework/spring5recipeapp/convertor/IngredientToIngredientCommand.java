package guru.springframework.spring5recipeapp.convertor;


import guru.springframework.spring5recipeapp.command.IngredientCommand;
import guru.springframework.spring5recipeapp.domain.Ingredient;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
    public class IngredientToIngredientCommand implements Converter<Ingredient, IngredientCommand> {

        private final UnitOfMeasureToUnitofMeasureCommand uomConverter;

        public IngredientToIngredientCommand(UnitOfMeasureToUnitofMeasureCommand uomConverter) {
            this.uomConverter = uomConverter;
        }

        @Synchronized
        @Nullable
        @Override
        public IngredientCommand convert(Ingredient ingredient) {
            if (ingredient == null) {
                return null;
            }

            IngredientCommand ingredientCommand = new IngredientCommand();
            ingredientCommand.setId(ingredient.getId());
            ingredientCommand.setAmount(ingredient.getAmount());
            ingredientCommand.setDescription(ingredient.getDescription());
            ingredientCommand.setUom(uomConverter.convert(ingredient.getUom()));
            ingredientCommand.setRecipeId(ingredient.getRecipe().getId());
            return ingredientCommand;
        }

}

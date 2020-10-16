package guru.springframework.spring5recipeapp.convertor;




import guru.springframework.spring5recipeapp.command.RecipeCommand;
import guru.springframework.spring5recipeapp.domain.Recipe;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

    /**
     * Created by jt on 6/21/17.
     */
    @Component
    public class RecipeCommandToRecipe implements Converter<RecipeCommand, Recipe> {

        private final CategoryCommandToCategory categoryConveter;
        private final IngredientCommandToIngredient ingredientConverter;
        private final NoteCommandToNote notesConverter;

        public RecipeCommandToRecipe(CategoryCommandToCategory categoryConveter, IngredientCommandToIngredient ingredientConverter,
                                     NoteCommandToNote notesConverter) {
            this.categoryConveter = categoryConveter;
            this.ingredientConverter = ingredientConverter;
            this.notesConverter = notesConverter;
        }

        @Synchronized
        @Nullable
        @Override
        public Recipe convert(RecipeCommand source) {
            if (source == null) {
                return null;
            }

            final Recipe recipe = new Recipe();
            recipe.setId(source.getId());
            recipe.setCookTime(source.getCookTime());
            recipe.setPreparation(source.getPreparation());
            recipe.setDescription(source.getDescription());
            recipe.setDifficulty(source.getDifficulty());
            recipe.setDirection(source.getDirection());
            recipe.setServingTime(source.getServingTime());
            recipe.setSource(source.getSource());
            recipe.setUrl(source.getUrl());
            recipe.setNote(notesConverter.convert(source.getNote()));
            recipe.setImages(source.getImages());

            if (source.getCategories() != null && source.getCategories().size() > 0){
                source.getCategories()
                        .forEach( category -> recipe.getCategories().add(categoryConveter.convert(category)));
            }

            if (source.getIngredients() != null && source.getIngredients().size() > 0){
                source.getIngredients()
                        .forEach(ingredient -> recipe.getIngredients().add(ingredientConverter.convert(ingredient)));
            }

            return recipe;
        }
    }


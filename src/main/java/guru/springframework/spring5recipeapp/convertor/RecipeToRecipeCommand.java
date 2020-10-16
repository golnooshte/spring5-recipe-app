package guru.springframework.spring5recipeapp.convertor;


import guru.springframework.spring5recipeapp.command.RecipeCommand;
import guru.springframework.spring5recipeapp.domain.Category;
import guru.springframework.spring5recipeapp.domain.Recipe;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
    public class RecipeToRecipeCommand implements Converter<Recipe, RecipeCommand> {

        private final CategoryToCategoryCommand categoryConveter;
        private final IngredientToIngredientCommand ingredientConverter;
        private final NoteToNoteCommand noteConverter;

        public RecipeToRecipeCommand(CategoryToCategoryCommand categoryConveter,
                                     IngredientToIngredientCommand ingredientConverter,
                                     NoteToNoteCommand noteConverter) {
            this.categoryConveter = categoryConveter;
            this.ingredientConverter = ingredientConverter;
            this.noteConverter = noteConverter;
        }

        @Synchronized
        @Nullable
        @Override
        public RecipeCommand convert(Recipe source) {
            if (source == null) {
                return null;
            }

            final RecipeCommand command = new RecipeCommand();
            command.setId(source.getId());
            command.setCookTime(source.getCookTime());
            command.setPreparation(source.getPreparation());
            command.setDescription(source.getDescription());
            command.setDifficulty(source.getDifficulty());
            command.setDirection(source.getDirection());
            command.setServingTime(source.getServingTime());
            command.setSource(source.getSource());
            command.setUrl(source.getUrl());
            command.setNote(noteConverter.convert(source.getNote()));
command.setImages(source.getImages());
            if (source.getCategories() != null && source.getCategories().size() > 0){
                source.getCategories()
                        .forEach((Category category) -> command.getCategories().add(categoryConveter.convert(category)));
            }

            if (source.getIngredients() != null && source.getIngredients().size() > 0){
                source.getIngredients()
                        .forEach(ingredient -> command.getIngredients().add(ingredientConverter.convert(ingredient)));
            }

            return command;
        }
    }


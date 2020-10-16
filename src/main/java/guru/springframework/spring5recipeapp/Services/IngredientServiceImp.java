package guru.springframework.spring5recipeapp.Services;

import guru.springframework.spring5recipeapp.Repositories.RecipeRepository;
import guru.springframework.spring5recipeapp.Repositories.UnitOfMeasureRepository;
import guru.springframework.spring5recipeapp.command.IngredientCommand;
import guru.springframework.spring5recipeapp.convertor.IngredientCommandToIngredient;
import guru.springframework.spring5recipeapp.convertor.IngredientToIngredientCommand;
import guru.springframework.spring5recipeapp.domain.Ingredient;
import guru.springframework.spring5recipeapp.domain.Recipe;
import guru.springframework.spring5recipeapp.domain.UnitOfMeasure;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;


@Slf4j
@Service
public class IngredientServiceImp implements IngredientService {
    RecipeRepository recipeRepository;
    IngredientToIngredientCommand ingredientToIngredientCommand;
    UnitOfMeasureRepository unitOfMeasureRepository;
    IngredientCommandToIngredient ingredientCommandToIngredient;

    public IngredientServiceImp(RecipeRepository recipeRepository, IngredientToIngredientCommand
            ingredientToIngredientCommand, UnitOfMeasureRepository unitOfMeasureRepository,
                                IngredientCommandToIngredient ingredientCommandToIngredient) {
        this.recipeRepository = recipeRepository;
        this.ingredientToIngredientCommand = ingredientToIngredientCommand;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
        this.ingredientCommandToIngredient = ingredientCommandToIngredient;
    }

    @Override
    public IngredientCommand findbyRecipeIdandIngredient(Long recipeId,Long ingredientid) {

        Optional<Recipe> recipe=recipeRepository.findById(recipeId);
        if(!recipe.isPresent()){
          log.error("cannot find Recipe with this RecipeID: "+recipe.get().getId());

        }

     Optional<IngredientCommand> ingredientCommand =  recipe.get().getIngredients().stream().filter(ingredient -> ingredient.getId().equals(ingredientid))
                .map(ingredient -> ingredientToIngredientCommand.convert(ingredient)).findFirst();
if(!ingredientCommand.isPresent()){
    throw  new RuntimeException("can not find Ingredient Id with Id :"+ingredientCommand.get().getId());
}
        return ingredientCommand.get();
    }




    @Override
    @Transactional
    public IngredientCommand saveIngredientCommand(IngredientCommand command) {
        Optional<Recipe> recipeOptional = recipeRepository.findById(command.getRecipeId());

        if(!recipeOptional.isPresent()){

            //todo toss error if not found!
            log.error("Recipe not found for id: " + command.getRecipeId());
            return new IngredientCommand();
        } else {
            Recipe recipe = recipeOptional.get();

            Optional<Ingredient> ingredientOptional = recipe
                    .getIngredients()
                    .stream()
                    .filter(ingredient -> ingredient.getId().equals(command.getId()))
                    .findFirst();

            if(ingredientOptional.isPresent()){
                Ingredient ingredientFound = ingredientOptional.get();
                ingredientFound.setDescription(command.getDescription());
                ingredientFound.setAmount(command.getAmount());
                ingredientFound.setUom(unitOfMeasureRepository
                        .findById(command
                                .getUom().getId())
                        .orElseThrow(() -> new RuntimeException("UOM NOT FOUND"))); //todo address this
            } else {
                //add new Ingredient
                Ingredient ingredient=ingredientCommandToIngredient.convert(command);
                ingredient.setRecipe(recipe);
recipe.addIngredient(ingredient);

            }

            Recipe savedRecipe = recipeRepository.save(recipe);

            //to do check for fail
            Optional<Ingredient>  savedOptional=savedRecipe.getIngredients().stream()
                    .filter(recipeIngredients -> recipeIngredients.getId().equals(command.getId()))
                    .findFirst();
            if(!savedOptional.isPresent()){
                savedOptional=savedRecipe.getIngredients().stream()
                        .filter(ingredient -> ingredient.getAmount().equals(command.getAmount()))
                        .filter(ingredient -> ingredient.getDescription().equals(command.getDescription()))
                        .filter(ingredient -> ingredient.getUom().getId().equals(command.getUom().getId()))
                        .findFirst();
            }
            return ingredientToIngredientCommand.convert(savedOptional.get());
        }


    }
    @Override
    public void deleteByRecipeId(Long recipeid,Long ingredientid) {
        Optional<Recipe> foundrecipe=recipeRepository.findById(recipeid);
        if(foundrecipe.isPresent()){
            Optional<Ingredient> foundingredient=foundrecipe.get().getIngredients().stream()
                    .filter(ingredient -> ingredient.getId().equals(ingredientid)).findFirst();

            if(foundingredient.isPresent()){
                Recipe recipe=foundrecipe.get();
                Ingredient ingredientTodelete=foundingredient.get();

                recipe.getIngredients().remove(ingredientTodelete);
                ingredientTodelete.setRecipe(null);

                recipeRepository.save(recipe);

            }else{
                throw  new RuntimeException("ingredient does not found");
            }
        }
        else{
            throw  new RuntimeException("recipe does not found");
        }
    }

}

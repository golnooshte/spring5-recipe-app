package guru.springframework.spring5recipeapp.Services;

import guru.springframework.spring5recipeapp.Exception.NotFoundException;
import guru.springframework.spring5recipeapp.Repositories.RecipeRepository;
import guru.springframework.spring5recipeapp.command.RecipeCommand;
import guru.springframework.spring5recipeapp.convertor.RecipeCommandToRecipe;
import guru.springframework.spring5recipeapp.convertor.RecipeToRecipeCommand;
import guru.springframework.spring5recipeapp.domain.Recipe;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Service
public class RecipeServiceImp implements RecipeService {

    private final RecipeRepository recipeRepository;

    private final RecipeToRecipeCommand recipeToRecipeCommand;

    private final RecipeCommandToRecipe recipeCommandToRecipe;


    public RecipeServiceImp(RecipeRepository recipeRepository, RecipeToRecipeCommand recipeToRecipeCommand,
                            RecipeCommandToRecipe recipeCommandToRecipe) {

        this.recipeRepository = recipeRepository;
        this.recipeToRecipeCommand = recipeToRecipeCommand;
        this.recipeCommandToRecipe = recipeCommandToRecipe;
    }

    @Override
    public Set<Recipe> getRecipe() {
Set<Recipe> recipeSet=new HashSet<>();
recipeRepository.findAll().iterator().forEachRemaining(recipeSet::add);
log.debug("i am in the server!");
return  recipeSet;
    }

    @Override
    public Recipe  findById(Long id){
        Optional<Recipe> recipeOptional=recipeRepository.findById(id);
        if(!recipeOptional.isPresent()){
         throw   new NotFoundException("Recipe not found for recipe with id: "+ id.toString());

        }

        else return recipeOptional.get();
    }

    @Override
    @Transactional
    public RecipeCommand saveRecipeCommand(RecipeCommand recipe) {
        Recipe recipe1=recipeCommandToRecipe.convert(recipe);
       Recipe saveRecipe= recipeRepository.save(recipe1);

    log.debug("Saved RecipeId:" + saveRecipe.getId());


        return recipeToRecipeCommand.convert(saveRecipe);
    }

    @Override
    public RecipeCommand findRecipeCommandById(Long id) {

        return recipeToRecipeCommand.convert( recipeRepository.findById(id).orElse(null));
    }

    @Override
    public void deleteRecipe(Long id) {
        recipeRepository.deleteById(id);
    }
}

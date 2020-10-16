package guru.springframework.spring5recipeapp.Controller;

import guru.springframework.spring5recipeapp.Services.IngredientService;
import guru.springframework.spring5recipeapp.Services.RecipeService;
import guru.springframework.spring5recipeapp.Services.UomService;
import guru.springframework.spring5recipeapp.command.IngredientCommand;
import guru.springframework.spring5recipeapp.command.RecipeCommand;
import guru.springframework.spring5recipeapp.command.UnitOfMeasureCommand;
import guru.springframework.spring5recipeapp.domain.Recipe;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
public class IngredientController {
    private final   RecipeService recipeService;
    private final IngredientService ingredientService;
    private final UomService uomList;

    public IngredientController(RecipeService recipeService, IngredientService ingredientService, UomService uomList) {
        this.recipeService = recipeService;
        this.ingredientService = ingredientService;
        this.uomList = uomList;
    }

    @GetMapping
    @RequestMapping("recipe/{recipeId}/ingredients")
    public String ListIngredient(@PathVariable String recipeId, Model model){
        log.debug("getting ingredient list",recipeId);
        model.addAttribute("recipe",recipeService.findRecipeCommandById(Long.valueOf(recipeId)));
        return "recipe/ingredient/list";
    }
    @RequestMapping("recipe/{recipeId}/ingredient/{id}/show")
    public String showIngredient(@PathVariable String recipeId,
                                 @PathVariable String id,Model model){
        model.addAttribute("ingredient",ingredientService.
                findbyRecipeIdandIngredient(Long.valueOf(recipeId),Long.valueOf(id)));

        return "recipe/ingredient/show";

    }
@GetMapping
    @RequestMapping("recipe/{recipeId}/ingredient/{id}/update")
    public String UpdateIngredient(@PathVariable String recipeId,
                                @PathVariable String id,Model model){
        model.addAttribute("ingredient",
                ingredientService.findbyRecipeIdandIngredient(Long.valueOf(recipeId),Long.valueOf(id)));
        model.addAttribute("uomList",uomList.listAllUoms());
        return "recipe/ingredient/ingredientform";

    }
    @PostMapping("recipe/{recipeId}/ingredient")
    public String saveOrUpdate(@ModelAttribute IngredientCommand command) {
        IngredientCommand savedCommand = ingredientService.saveIngredientCommand(command);

        log.debug("saved receipe id:" + savedCommand.getRecipeId());
        log.debug("saved ingredient id:" + savedCommand.getId());

        return "redirect:/recipe/" + savedCommand.getRecipeId() + "/ingredient/" + savedCommand.getId() + "/show";


    }
    @RequestMapping("recipe/{recipeId}/ingredient/new")
    public String CreateIngredient(@PathVariable String recipeId,Model model){

        IngredientCommand ingredientCommand =new IngredientCommand();
        ingredientCommand.setRecipeId(Long.valueOf(recipeId));

model.addAttribute("ingredient",ingredientCommand);
        UnitOfMeasureCommand uom =new UnitOfMeasureCommand();
        ingredientCommand.setUom(uom);
        model.addAttribute("uomList",uomList.listAllUoms());

        return "recipe/ingredient/ingredientform" ;
    }
    @RequestMapping("recipe/{recipeId}/ingredient/{id}/delete")
    public String DeleteIngredient(@PathVariable  String recipeId,
                                   @PathVariable String id){
RecipeCommand recipeCommand= (RecipeCommand) recipeService.findRecipeCommandById(Long.valueOf(recipeId));
        ingredientService.deleteByRecipeId(Long.valueOf(recipeId),Long.valueOf(id));

        return "redirect:/recipe/"+recipeCommand.getId()+"/ingredients";
    }
}

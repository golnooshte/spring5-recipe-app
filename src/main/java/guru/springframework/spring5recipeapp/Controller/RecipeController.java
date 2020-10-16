package guru.springframework.spring5recipeapp.Controller;

import guru.springframework.spring5recipeapp.Exception.NotFoundException;
import guru.springframework.spring5recipeapp.Services.RecipeService;
import guru.springframework.spring5recipeapp.command.RecipeCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.http.HttpStatus;

import javax.validation.Valid;


@Slf4j
@Controller
public class RecipeController {
private RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @RequestMapping("/recipe/show/{id}")
    public String showById(@PathVariable String id, Model model){
        model.addAttribute("recipe", recipeService.findById(Long.valueOf(id)));
        return "recipe/show";
    }
    @RequestMapping("recipe/new")
    public String CreateRecipe(Model model){
        model.addAttribute("recipe",new RecipeCommand());
        return "recipe/form";
    }

@PostMapping("recipe")

    public String SaveRecipe(@Valid @ModelAttribute("recipe") RecipeCommand recipeCommand, BindingResult bindingResult){
        if(bindingResult.hasErrors()) {
            bindingResult.getAllErrors().forEach(objectError -> {
                log.error(objectError.toString());
        });
            return "recipe/form";
        }
        RecipeCommand saveRecipeCommand=recipeService.saveRecipeCommand(recipeCommand);
        return "redirect:/recipe/show/"+saveRecipeCommand.getId();
    }
    @RequestMapping("/recipe/{id}/update")
    public String Update(@PathVariable Long id, Model model){
        model.addAttribute("recipe", recipeService.findRecipeCommandById(id));
        return "recipe/form";
    }

    @RequestMapping("/recipe/{id}/delete")
    public String Delete(@PathVariable Long id, Model model){

     recipeService.deleteRecipe(id);
        return "redirect:/";
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public ModelAndView Exeption(Exception exception){
        log.error("Not found Handeling exception!");
        log.error(exception.getMessage());
        ModelAndView mav=new ModelAndView();
        mav.setViewName("NotFound");
        mav.addObject("exception",exception);
        return mav;
}


}

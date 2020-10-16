package guru.springframework.spring5recipeapp.Controller;

import guru.springframework.spring5recipeapp.Repositories.CategoryRepository;
import guru.springframework.spring5recipeapp.Repositories.UnitOfMeasureRepository;
import guru.springframework.spring5recipeapp.Services.RecipeService;
import guru.springframework.spring5recipeapp.domain.Category;
import guru.springframework.spring5recipeapp.domain.UnitOfMeasure;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;
@Slf4j
@Controller
public class indexController {
   private RecipeService recipeService;

    public indexController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @RequestMapping({"","/","index"})
    public String index(Model model){
        model.addAttribute("recipes",recipeService.getRecipe());
        log.debug("I am in the index controller");
        return "index";
    }
}

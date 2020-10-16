package guru.springframework.spring5recipeapp.Bootstrap;

import guru.springframework.spring5recipeapp.Repositories.CategoryRepository;
import guru.springframework.spring5recipeapp.Repositories.RecipeRepository;
import guru.springframework.spring5recipeapp.Repositories.UnitOfMeasureRepository;
import guru.springframework.spring5recipeapp.domain.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Slf4j
@Component
public class LoadData implements ApplicationListener<ContextRefreshedEvent> {
    private CategoryRepository categoryRepository;
    private RecipeRepository recipeRepository;
    private UnitOfMeasureRepository unitOfMeasureRepository;


public LoadData(CategoryRepository categoryRepository, RecipeRepository recipeRepository, UnitOfMeasureRepository unitOfMeasureRepository) {
        this.categoryRepository = categoryRepository;
        this.recipeRepository = recipeRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }
@Transactional
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        recipeRepository.saveAll(getrecipes());
 
    }
private List<Recipe> getrecipes() {
    List<Recipe>recipes=new ArrayList<>(2);
    Optional<UnitOfMeasure> Teaspone = unitOfMeasureRepository.findByDescription("Teaspone");
    Optional<UnitOfMeasure> TableSpoon = unitOfMeasureRepository.findByDescription("TableSpoon");
    Optional<UnitOfMeasure> cup = unitOfMeasureRepository.findByDescription("cup");
    Optional<UnitOfMeasure> Pinch = unitOfMeasureRepository.findByDescription("Pinch");
    Optional<UnitOfMeasure> Ounce = unitOfMeasureRepository.findByDescription("Ounce");
    Optional<UnitOfMeasure> pound = unitOfMeasureRepository.findByDescription("pound");
    Optional<UnitOfMeasure> number = unitOfMeasureRepository.findByDescription("number");

    if (!number.isPresent()) {
        throw new RuntimeException("Expedted UOM  not found");
    }

    if (!Teaspone.isPresent()) {
        throw new RuntimeException("Expedted UOM  not found");
    }
    if (!TableSpoon.isPresent()) {
        throw new RuntimeException("Expedted UOM  not found");
    }
    if (!cup.isPresent()) {
        throw new RuntimeException("Expedted UOM  not found");
    }
    if (!Pinch.isPresent()) {
        throw new RuntimeException("Expedted UOM  not found");
    }
    if (!Ounce.isPresent()) {
        throw new RuntimeException("Expedted UOM  not found");
    }
    if (!pound.isPresent()) {
        throw new RuntimeException("Expedted UOM  not found");
    }


    Optional<Category> American = categoryRepository.findByDescription("American");
    if (!American.isPresent()) {
        throw new RuntimeException("Expedted Category  not found");
    }
    Optional<Category> Mexican = categoryRepository.findByDescription("Mexican");
    if (!American.isPresent()) {
        throw new RuntimeException("Expedted Category  not found");
    }

    Recipe guacRecipe = new Recipe();
    guacRecipe.setDescription("Perfect Guacamole");
    guacRecipe.setPreparation(10);
    guacRecipe.setCookTime(0);
    guacRecipe.setDifficulty(Difficulty.EASY);
    guacRecipe.setDirection("1 Cut avocado, remove flesh: Cut the avocados in half. Remove seed. Score the inside of the avocado with a blunt knife and scoop out the flesh with a spoon" +
            "\n" +
            "2 Mash with a fork: Using a fork, roughly mash the avocado. (Don't overdo it! The guacamole should be a little chunky.)" +
            "\n" +
            "3 Add salt, lime juice, and the rest: Sprinkle with salt and lime (or lemon) juice. The acid in the lime juice will provide some balance to the richness of the avocado and will help delay the avocados from turning brown.\n" +
            "Add the chopped onion, cilantro, black pepper, and chiles. Chili peppers vary individually in their hotness. So, start with a half of one chili pepper and add to the guacamole to your desired degree of hotness.\n" +
            "Remember that much of this is done to taste because of the variability in the fresh ingredients. Start with this recipe and adjust to your taste.\n" +
            "4 Cover with plastic and chill to store: Place plastic wrap on the surface of the guacamole cover it and to prevent air reaching it. (The oxygen in the air causes oxidation which will turn the guacamole brown.) Refrigerate until ready to serve.\n" +
            "Chilling tomatoes hurts their flavor, so if you want to add chopped tomato to your guacamole, add it just before serving.\n" +
            "\n" +
            "\n" +
            "Read more: http://www.simplyrecipes.com/recipes/perfect_guacamole/#ixzz4jvpiV9Sd");

    Note guacNotes = new Note();
    guacNotes.setRecipeNote("For a very quick guacamole just take a 1/4 cup of salsa and mix it in with your mashed avocados.\n" +
            "Feel free to experiment! One classic Mexican guacamole has pomegranate seeds and chunks of peaches in it (a Diana Kennedy favorite). Try guacamole with added pineapple, mango, or strawberries.\n" +
            "The simplest version of guacamole is just mashed avocados with salt. Don't let the lack of availability of other ingredients stop you from making guacamole.\n" +
            "To extend a limited supply of avocados, add either sour cream or cottage cheese to your guacamole dip. Purists may be horrified, but so what? It tastes great.\n" +
            "\n" +
            "\n" +
            "Read more: http://www.simplyrecipes.com/recipes/perfect_guacamole/#ixzz4jvoun5ws");
    guacRecipe.setNote(guacNotes);

    guacRecipe.addIngredient(new Ingredient("Kosher salt", new BigDecimal(".5"), Teaspone.get()));
    guacRecipe.addIngredient(new Ingredient("fresh lime juice or lemon juice", new BigDecimal(2), TableSpoon.get()));
    guacRecipe.addIngredient(new Ingredient("minced red onion or thinly sliced green onion", new BigDecimal(2), TableSpoon.get()));
    guacRecipe.addIngredient(new Ingredient("Cilantro", new BigDecimal(2), TableSpoon.get()));
    guacRecipe.setServingTime(4);
    guacRecipe.setUrl("http://www.simplyrecipes.com/recipes/perfect_guacamole/#ixzz4jvoun5ws");


    guacRecipe.getCategories().add(American.get());
    guacRecipe.getCategories().add(Mexican.get());

    //add to return list
    recipes.add(guacRecipe);

    Recipe tacoRecipe = new Recipe();
    tacoRecipe.setPreparation(20);
    tacoRecipe.setCookTime(15);
    tacoRecipe.setDifficulty(Difficulty.MODERATE);
    tacoRecipe.setDescription("Spicy Grilled Chicken Taco");

    tacoRecipe.setDirection("Look for ancho chile powder with the Mexican ingredients at your grocery " +
            "store,+\n" +
            "+on buy it online. (If you can't find ancho chili powder, you replace the ancho chili,\n" +
            "+the oregano, and the cumin with 2 1/2 tablespoons regular chili powder, though+\n" +
            "the flavor won't be quite the same.)");
    Note note = new Note();
    note.setRecipeNote("1 Prepare a gas or charcoal grill for medium-high, direct heat.\n" +
            "\n+2 Make the marinade and coat the chicken: In a large bowl, stir together the chili powder, oregano, cumin," +
            " sugar, salt, garlic and orange zest. Stir in the orange juice and olive oil to make a loose" +
            " paste." +
            " Add the chicken to the bowl and toss to coat all over.\n" +
            "\n" +
            "Set aside to marinate while the grill heats and you prepare the rest of the toppings.");
    note.setRecipe(tacoRecipe);
    tacoRecipe.setNote(note);
    tacoRecipe.getCategories().add(American.get());

    tacoRecipe.getIngredients().add(new Ingredient("dried oregano",
            new BigDecimal(1), TableSpoon.get(),tacoRecipe));

    tacoRecipe.getIngredients().add(new Ingredient("sugar",
            new BigDecimal(1), TableSpoon.get(),tacoRecipe));
    tacoRecipe.getIngredients().add(new Ingredient("salt",
            new BigDecimal(1 / 2), TableSpoon.get(),tacoRecipe));
    tacoRecipe.getIngredients().add(new Ingredient("chicken thighs",
            new BigDecimal(1.25), number.get(),tacoRecipe));
    recipes.add(tacoRecipe);





    return recipes;


}

}


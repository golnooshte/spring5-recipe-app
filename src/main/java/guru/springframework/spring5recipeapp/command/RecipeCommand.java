package guru.springframework.spring5recipeapp.command;

import guru.springframework.spring5recipeapp.domain.Category;
import guru.springframework.spring5recipeapp.domain.Difficulty;
import guru.springframework.spring5recipeapp.domain.Ingredient;
import guru.springframework.spring5recipeapp.domain.Note;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.BatchSize;
import org.hibernate.validator.constraints.URL;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;
@Getter
@Setter
@NoArgsConstructor
public class RecipeCommand {
    private Long id;
    @NotBlank
    @Size(min =3 ,max=255)
    private String description;
    @Min(1)
    @Max(999)
    private Integer preparation;
    @Min(1)
    @Max(999)
    private Integer cookTime;

@Min(1)
@Max(100)

    private Integer servingTime;
    private String source;
    @URL
    private String url;
    @NotBlank
    private String direction;
    private byte[] images;
    private NoteCommand note;
    private Difficulty difficulty;

    private Set<IngredientCommand> ingredients=new HashSet<>();
    private Set<Categorycommand>categories=new HashSet<>();



}

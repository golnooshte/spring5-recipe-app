package guru.springframework.spring5recipeapp.domain;

import lombok.*;
import net.bytebuddy.dynamic.loading.InjectionClassLoader;
import org.hibernate.engine.internal.Cascade;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity


public class Recipe {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;
    private Integer preparation;
    private Integer cookTime;
    private Integer servingTime;
    private String source;
    private String url;
    @Lob
    private String direction;
@Lob
    private byte[] images;
@Enumerated(value = EnumType.STRING)
private Difficulty difficulty;
@OneToOne(cascade= CascadeType.ALL)
    private Note note;


@OneToMany(cascade =CascadeType.ALL ,mappedBy = "recipe")
private Set<Ingredient> ingredients=new HashSet<>();
@ManyToMany
@JoinTable(name="recipe_category",
        joinColumns = @JoinColumn(name="recipe_id"),
        inverseJoinColumns = @JoinColumn(name="category_id"))

 private Set<Category>categories=new HashSet<>();




    public void setNote(Note notes) {
        if (notes != null) {
            this.note = notes;
            notes.setRecipe(this);
        }
    }
    public Recipe addIngredient(Ingredient ingredient){
        ingredient.setRecipe(this);
        this.getIngredients().add(ingredient);

        return this;
    }


}

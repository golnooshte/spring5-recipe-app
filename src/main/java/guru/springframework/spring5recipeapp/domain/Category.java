package guru.springframework.spring5recipeapp.domain;

import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.Set;

@lombok.Data
@EqualsAndHashCode(exclude = {"recipes"})
@Entity
public class Category {


    private String description;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToMany(mappedBy = "categories")
    private Set<Recipe> recipes;


}

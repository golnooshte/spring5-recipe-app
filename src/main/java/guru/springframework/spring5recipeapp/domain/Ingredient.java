package guru.springframework.spring5recipeapp.domain;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Entity
@EqualsAndHashCode(exclude = {"recipe"})

public class Ingredient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
     private String description;
     private BigDecimal amount;
     @ManyToOne
     private Recipe recipe;
//we use fetch.eager to tell whenever  we want data we can have it
     @OneToOne(fetch = FetchType.EAGER)
     private UnitOfMeasure uom;

    public Ingredient() {
    }
    public Ingredient(String description,BigDecimal amount,UnitOfMeasure unitOfMeasure,Recipe recipe) {
        this.description=description;
        this.amount=amount;
        this.uom=unitOfMeasure;
        this.recipe=recipe;
    }

    public Ingredient(String description,BigDecimal amount,UnitOfMeasure unitOfMeasure) {
        this.description=description;
        this.amount=amount;
        this.uom=unitOfMeasure;

    }

    public Long getId() {
        return id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public UnitOfMeasure getUom() {
        return uom;
    }
}

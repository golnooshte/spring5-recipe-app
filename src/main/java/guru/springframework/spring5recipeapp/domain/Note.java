package guru.springframework.spring5recipeapp.domain;

import lombok.*;

import javax.persistence.*;

@Data
@Entity
@EqualsAndHashCode(exclude = {"recipe"})

public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

@OneToOne
    private Recipe recipe;
    @Lob
    private  String recipeNote;

}

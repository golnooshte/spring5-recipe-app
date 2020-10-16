package guru.springframework.spring5recipeapp.command;

import guru.springframework.spring5recipeapp.domain.Recipe;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Lob;
import javax.persistence.OneToOne;
@Getter
@Setter
@NoArgsConstructor
public class NoteCommand {
    private Long id;
    private  String recipeNote;
}

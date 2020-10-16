package guru.springframework.spring5recipeapp.convertor;


    import guru.springframework.spring5recipeapp.command.NoteCommand;
    import guru.springframework.spring5recipeapp.domain.Note;
    import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

    /**
     * Created by jt on 6/21/17.
     */
    @Component
    public class NoteCommandToNote implements Converter<NoteCommand, Note> {

        @Synchronized
        @Nullable
        @Override
        public Note convert(NoteCommand source) {
            if(source == null) {
                return null;
            }

            final Note notes = new Note();
            notes.setId(source.getId());
            notes.setRecipeNote(source.getRecipeNote());
            return notes;
        }
    }



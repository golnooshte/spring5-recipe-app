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
    public class NoteToNoteCommand implements Converter<Note, NoteCommand>{

        @Synchronized
        @Nullable
        @Override
        public NoteCommand convert(Note source) {
            if (source == null) {
                return null;
            }

            final NoteCommand notesCommand = new NoteCommand();
            notesCommand.setId(source.getId());
            notesCommand.setRecipeNote(source.getRecipeNote());
            return notesCommand;
        }
    }



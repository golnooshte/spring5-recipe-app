package guru.springframework.spring5recipeapp.convertor;


import guru.springframework.spring5recipeapp.command.Categorycommand;
import guru.springframework.spring5recipeapp.domain.Category;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class CategoryToCategoryCommand implements Converter<Category, Categorycommand> {

    @Synchronized
    @Nullable

    @Override
    public Categorycommand convert(Category source) {
        if (source == null) {
            return null;
        }

        final Categorycommand categoryCommand = new Categorycommand();

        categoryCommand.setId(source.getId());
        categoryCommand.setDescription(source.getDescription());

        return categoryCommand;
    }
}

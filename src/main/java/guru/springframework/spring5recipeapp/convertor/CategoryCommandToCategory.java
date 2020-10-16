package guru.springframework.spring5recipeapp.convertor;

import guru.springframework.spring5recipeapp.command.Categorycommand;
import guru.springframework.spring5recipeapp.domain.Category;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class CategoryCommandToCategory implements Converter<Categorycommand,Category> {
@Synchronized
@Nullable
    @Override
    public Category convert(Categorycommand source) {
        if (source == null) {
            return null;
        }
        final Category category = new Category();
        category.setId(source.getId());
        category.setDescription(source.getDescription());
        return category;

    }
}
package guru.springframework.spring5recipeapp.convertor;


import guru.springframework.spring5recipeapp.command.UnitOfMeasureCommand;
import guru.springframework.spring5recipeapp.domain.UnitOfMeasure;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
    public class UniteofMeasureCommmandtoMeeasure implements Converter<UnitOfMeasureCommand, UnitOfMeasure> {

        @Synchronized
        @Nullable
        @Override
        public UnitOfMeasure convert(UnitOfMeasureCommand source) {
            if (source == null) {
                return null;
            }

            final UnitOfMeasure uom = new UnitOfMeasure();
            uom.setId(source.getId());
            uom.setDescription(source.getDescription());
            return uom;
        }
    }


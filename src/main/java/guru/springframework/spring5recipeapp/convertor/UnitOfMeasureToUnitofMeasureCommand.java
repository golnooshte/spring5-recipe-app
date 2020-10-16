package guru.springframework.spring5recipeapp.convertor;

import guru.springframework.spring5recipeapp.command.UnitOfMeasureCommand;
import guru.springframework.spring5recipeapp.domain.UnitOfMeasure;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class UnitOfMeasureToUnitofMeasureCommand implements Converter<UnitOfMeasure,
        UnitOfMeasureCommand> {
    @Synchronized
    @Nullable
    @Override

    public UnitOfMeasureCommand convert(UnitOfMeasure source) {
        if(source!=null){


            UnitOfMeasureCommand unitOfMeasureCommand=new UnitOfMeasureCommand();
            unitOfMeasureCommand.setId(source.getId());
            unitOfMeasureCommand.setDescription(source.getDescription());
            return unitOfMeasureCommand;

}
        return null;
    }}

package guru.springframework.spring5recipeapp.Services;

import guru.springframework.spring5recipeapp.command.UnitOfMeasureCommand;

import java.util.Set;

public interface UomService {
    Set<UnitOfMeasureCommand> listAllUoms();
}

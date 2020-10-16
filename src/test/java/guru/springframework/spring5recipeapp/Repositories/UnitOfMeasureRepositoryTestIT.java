package guru.springframework.spring5recipeapp.Repositories;

import guru.springframework.spring5recipeapp.domain.UnitOfMeasure;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@RunWith(SpringRunner.class)
@DataJpaTest
class UnitOfMeasureRepositoryTestIT {
    @Autowired
UnitOfMeasureRepository unitOfMeasureRepository;
    @BeforeEach
    void setUp() {
        Optional<UnitOfMeasure> unitOfMeasure=unitOfMeasureRepository.findByDescription("cup");
        assertEquals("cup",unitOfMeasure.get().getDescription());
    }

    @Test
    void findByDescription() {
    }
}
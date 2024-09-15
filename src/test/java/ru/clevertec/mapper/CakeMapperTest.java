package ru.clevertec.mapper;

import org.junit.jupiter.api.Test;
import ru.clevertec.domain.Cake;
import ru.clevertec.entity.CakeEntity;
import ru.clevertec.util.TestData;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CakeMapperTest {
    private final static CakeMapper MAPPER = new CakeMapperImpl();

    @Test
    void shouldMappingToCakeEntity() {
        //given
        Cake cake = TestData.generateCake();

        //when
        CakeEntity cakeEntityActual = MAPPER.toCakeEntity(cake);

        //then
        assertThat(cakeEntityActual)
                .isNotNull()
                .hasFieldOrPropertyWithValue("id", cake.getId())
                .hasFieldOrPropertyWithValue("name", cake.getName())
                .hasFieldOrPropertyWithValue("cakeType", cake.getCakeType())
                .hasFieldOrPropertyWithValue("expiredPeriod", cake.getExpiredPeriod());
    }

    @Test
    void shouldMappingToCakeEntities() {
        //given
        List<Cake> cakes = List.of(TestData.generateCake());

        //when
        List<CakeEntity> cakeEntitiesActual = MAPPER.toCakeEntities(cakes);

        //then
        cakeEntitiesActual.forEach(cakeEntity -> {
                    cakes.forEach(cake -> {
                                assertThat(cakeEntity)
                                        .isNotNull()
                                        .hasFieldOrPropertyWithValue("id", cake.getId())
                                        .hasFieldOrPropertyWithValue("name", cake.getName())
                                        .hasFieldOrPropertyWithValue("cakeType", cake.getCakeType())
                                        .hasFieldOrPropertyWithValue("expiredPeriod", cake.getExpiredPeriod());
                            }
                    );
                }
        );
    }

    @Test
    void shouldMappingToCake() {
        //given
        CakeEntity cakeEntity = TestData.generateCakeEntity();

        //when
        Cake cakeActual = MAPPER.toCake(cakeEntity);

        //then
        assertEquals(cakeEntity.getId(), cakeActual.getId());
        assertEquals(cakeEntity.getName(), cakeActual.getName());
        assertEquals(cakeEntity.getCakeType(), cakeActual.getCakeType());
        assertEquals(cakeEntity.getExpiredPeriod(), cakeActual.getExpiredPeriod());
        assertThat(cakeActual).isNotNull();
    }

    @Test
    void shouldMappingToCakes() {
        //given
        CakeEntity entity = TestData.generateCakeEntity();
        List<CakeEntity> cakeEntities = new ArrayList<>();
        cakeEntities.add(entity);

        //when
        List<Cake> cakesActual = MAPPER.toCakes(cakeEntities);

        //then
        cakesActual.forEach(cake -> {
                    cakeEntities.forEach(cakeEntity -> {
                                assertEquals(cakeEntity.getId(), cake.getId());
                                assertEquals(cakeEntity.getName(), cake.getName());
                                assertEquals(cakeEntity.getCakeType(), cake.getCakeType());
                                assertEquals(cakeEntity.getExpiredPeriod(), cake.getExpiredPeriod());
                            }
                    );
                }
        );
        assertThat(cakesActual).isNotNull();
    }
}
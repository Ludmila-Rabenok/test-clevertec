package ru.clevertec.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.clevertec.common.CakeType;
import ru.clevertec.domain.Cake;
import ru.clevertec.entity.CakeEntity;
import ru.clevertec.exception.CakeNotFoundException;
import ru.clevertec.mapper.CakeMapper;
import ru.clevertec.repository.CakeRepository;
import ru.clevertec.util.TestData;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CakeServiceTest {
    @Mock
    private CakeRepository cakeRepository;
    @Mock
    private CakeMapper cakeMapper;
    @InjectMocks
    private CakeService cakeService;

    @Test
    void shouldGetCakes() {

        //given
        List<CakeEntity> cakeEntities = List.of(TestData.generateCakeEntity());
        List<Cake> cakes = List.of(TestData.generateCake());
        when(cakeRepository.getCakes()).thenReturn(Optional.of(cakeEntities));
        when(cakeMapper.toCakes(cakeEntities)).thenReturn(cakes);

        //when
        List<Cake> actualCakes = cakeService.getCakes();

        //then
        assertFalse(actualCakes.isEmpty());
        assertEquals(cakes, actualCakes);
    }

    @Test
    void shouldGetCakeById() {

        //given
        CakeEntity cakeEntity = TestData.generateCakeEntity();
        Cake cake = TestData.generateCake();
        UUID cakeId = cake.getId();

        when(cakeRepository.getCakeById(cakeId)).thenReturn(Optional.of(cakeEntity));
        when(cakeMapper.toCake(cakeEntity)).thenReturn(cake);

        //when
        Cake actualCake = cakeService.getCakeById(cakeId);

        //then
        assertEquals(cake.getId(), actualCake.getId());
        verify(cakeRepository).getCakeById(cakeId);
        verify(cakeMapper).toCake(cakeEntity);
    }

    @Test
    void shouldThrowCakeNotFoundException_whenCakeNotFoundById() {

        //given
        UUID cakeId = UUID.randomUUID();
        when(cakeRepository.getCakeById(cakeId)).thenReturn(Optional.empty());

        //when, then
        assertThrows(CakeNotFoundException.class,
                () -> cakeService.getCakeById(cakeId)
        );
        verifyNoInteractions(cakeMapper);
    }

    @ParameterizedTest
    @EnumSource(CakeType.class)
    void shouldGetCakeByType(CakeType cakeType) {

        //given
        CakeEntity cakeEntity = TestData.generateCakeEntity();
        Cake cake = TestData.generateCake();
        cake.setCakeType(cakeType);

        when(cakeRepository.getCakeByType(cakeType)).thenReturn(Optional.of(cakeEntity));
        when(cakeMapper.toCake(cakeEntity)).thenReturn(cake);

        //when, then
        assertDoesNotThrow(() -> cakeService.getCakeByType(cakeType));
    }

    @Test
    void shouldThrowCakeNotFoundException_whenCakeNotFoundByType() {

        //given
        CakeType cakeType = CakeType.SMALL;
        when(cakeRepository.getCakeByType(cakeType)).thenReturn(Optional.empty());

        //when, then
        assertThrows(CakeNotFoundException.class,
                () -> cakeService.getCakeByType(cakeType)
        );
        verifyNoInteractions(cakeMapper);
    }

    @Test
    void shouldCreate() {
        //given
        Cake cake = TestData.generateCake();
        CakeEntity cakeEntity = TestData.generateCakeEntity();

        when(cakeMapper.toCakeEntity(cake)).thenReturn(cakeEntity);
        when(cakeRepository.create(cakeEntity)).thenReturn(Optional.of(cakeEntity));
        when(cakeMapper.toCake(cakeEntity)).thenReturn(cake);

        //when
        Cake actualCake = cakeService.create(cake);

        //then
        assertEquals(cake.getId(), actualCake.getId());
        verify(cakeRepository).create(cakeEntity);
    }

    @Test
    void shouldUpdate() {

        //given
        Cake cake = TestData.generateCake();
        UUID cakeId = cake.getId();
        CakeEntity cakeEntity = TestData.generateCakeEntity();

        when(cakeMapper.toCakeEntity(cake)).thenReturn(cakeEntity);
        when(cakeRepository.update(cakeId, cakeEntity)).thenReturn(Optional.of(cakeEntity));
        when(cakeMapper.toCake(cakeEntity)).thenReturn(cake);

        //when
        cakeService.update(cakeId, cake);

        //then
        verify(cakeRepository).update(cakeId, cakeEntity);
    }

    @Test
    void shouldDeleteById() {
        //given
        UUID cakeId = UUID.randomUUID();
        //when
        cakeService.deleteById(cakeId);
        //then
        verify(cakeRepository).deleteById(cakeId);
    }
}
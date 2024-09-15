package ru.clevertec.mapper;

import org.mapstruct.Mapper;
import ru.clevertec.domain.Cake;
import ru.clevertec.entity.CakeEntity;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CakeMapper {
    List<Cake> toCakes(List<CakeEntity> cakes);//toDomains

    Cake toCake(CakeEntity cakeEntity);//toDomain

    List<CakeEntity> toCakeEntities(List<Cake> cakes);//toEntities

    CakeEntity toCakeEntity(Cake cake);//toEntity
}

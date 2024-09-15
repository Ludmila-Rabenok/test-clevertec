package ru.clevertec.repository;

import org.springframework.stereotype.Repository;
import ru.clevertec.common.CakeType;
import ru.clevertec.entity.CakeEntity;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class CakeRepository {
    private static final List<CakeEntity> db = List.of(
            new CakeEntity(UUID.randomUUID(), "cake1", CakeType.BIG, OffsetDateTime.now().plusDays(1)),
            new CakeEntity(UUID.randomUUID(), "cake2", CakeType.SMALL, OffsetDateTime.now().plusDays(2)),
            new CakeEntity(UUID.randomUUID(), "cake3", CakeType.BIG, OffsetDateTime.now().plusDays(3)),
            new CakeEntity(UUID.randomUUID(), "cake4", CakeType.SMALL, OffsetDateTime.now().plusDays(4)),
            new CakeEntity(UUID.randomUUID(), "cake5", CakeType.BIG, OffsetDateTime.now().plusDays(5)),
            new CakeEntity(UUID.randomUUID(), "cake6", CakeType.SMALL, OffsetDateTime.now())
    );

    public Optional<List<CakeEntity>> getCakes() {
        return Optional.ofNullable(db);
    }

    public Optional<CakeEntity> getCakeById(UUID cakeId) {
        return Optional.ofNullable(db.get(0));
    }

    public Optional<CakeEntity> getCakeByType(CakeType cakeType) {
        return Optional.ofNullable(db.get(0));
    }

    public Optional<CakeEntity> create(CakeEntity cakeEntity) {
        return Optional.ofNullable(cakeEntity);
    }

    public Optional<CakeEntity> update(UUID cakeId, CakeEntity newCakeEntity) {
        return Optional.ofNullable(newCakeEntity.setId(cakeId));
    }

    public void deleteById(UUID cakeId) {
        //without body
    }
}

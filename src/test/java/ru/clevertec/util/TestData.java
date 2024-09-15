package ru.clevertec.util;

import ru.clevertec.common.CakeType;
import ru.clevertec.common.helper.DataSupplier;
import ru.clevertec.domain.Cake;
import ru.clevertec.entity.CakeEntity;

import java.util.UUID;

public class TestData {
    private static final DataSupplier DATA_SUPPLIER = new DataSupplierTest();

    public static CakeEntity generateCakeEntity() {
        return CakeEntity.builder().id(UUID.fromString("3412b448-2460-4fd2-9183-8000de6f8343"))
                .cakeType(CakeType.SMALL)
                .name("CakeEntityName")
                .expiredPeriod(DATA_SUPPLIER.getCurrentDateTime())
                .build();
    }

    public static Cake generateCake() {
        return Cake.builder().id(UUID.fromString("3422b448-2460-4fd2-9183-8000de6f8343"))
                .cakeType(CakeType.SMALL)
                .name("CakeName")
                .expiredPeriod(DATA_SUPPLIER.getCurrentDateTime())
                .build();
    }

    public static CakeEntity generateValidCakeEntity() {
        return new CakeEntity();
    }

    public static CakeEntity generateInvalidCakeEntity() {
        return new CakeEntity();
    }

}

package ru.clevertec.common.helper;

import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;

@Component
public class DataSupplierImpl implements DataSupplier {
    @Override
    public OffsetDateTime getCurrentDateTime() {
        return OffsetDateTime.now();
    }
}

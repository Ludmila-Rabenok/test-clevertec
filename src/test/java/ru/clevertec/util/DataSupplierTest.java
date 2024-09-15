package ru.clevertec.util;

import ru.clevertec.common.helper.DataSupplier;

import java.time.OffsetDateTime;

public class DataSupplierTest implements DataSupplier {
    @Override
    public OffsetDateTime getCurrentDateTime() {
        return OffsetDateTime.parse("2007-12-03T10:15:30+01:00");
    }
}

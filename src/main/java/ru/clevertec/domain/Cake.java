package ru.clevertec.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.clevertec.common.CakeType;

import java.time.OffsetDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Cake {

    private UUID id;
    private String name;
    private CakeType cakeType;
    private OffsetDateTime expiredPeriod;
}

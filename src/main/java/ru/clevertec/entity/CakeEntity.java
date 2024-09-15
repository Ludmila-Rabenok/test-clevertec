package ru.clevertec.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import ru.clevertec.common.CakeType;

import java.time.OffsetDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@Builder
public class CakeEntity {

    private UUID id;
    private String name;
    private CakeType cakeType;
    private OffsetDateTime expiredPeriod;
}

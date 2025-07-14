package com.glara.springcloud.commons.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

public record SubscriptionDTO(
        String name,
        String description,
        UUID accountId,
        BigDecimal amount, // Tu DTO usa double
        String frequency,
        Date paymentDate // Tu DTO usa java.util.Date
) {
}

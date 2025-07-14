package com.glara.springcloud.commons.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

public record SubscriptionDTOUpdate(String name, UUID accountId, String description, BigDecimal amount, String frequency,  Date paymentDate) {
}

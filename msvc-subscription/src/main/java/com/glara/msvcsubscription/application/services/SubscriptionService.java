package com.glara.msvcsubscription.application.services;

import com.glara.springcloud.commons.dto.SubscriptionDTO;
import com.glara.springcloud.commons.dto.SubscriptionDTOUpdate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface SubscriptionService {
    Page<SubscriptionDTO> getAllSubscriptions(Pageable pageable);
    SubscriptionDTO getSubscriptionById(UUID id);
    SubscriptionDTO createSubscription(SubscriptionDTO subscription);
    SubscriptionDTO updateSubscription(UUID id, SubscriptionDTOUpdate subscriptionUpdate);
    void deleteSubscription(UUID id);
}

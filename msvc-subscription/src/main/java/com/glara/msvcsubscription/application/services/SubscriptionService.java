package com.glara.msvcsubscription.application.services;

import com.glara.springcloud.commons.dto.SubscriptionDTO;
import com.glara.springcloud.commons.dto.SubscriptionDTOUpdate;

import java.util.List;
import java.util.UUID;

public interface SubscriptionService {
    List<SubscriptionDTO> getAllSubscriptions();
    SubscriptionDTO getSubscriptionById(UUID id);
    SubscriptionDTO createSubscription(SubscriptionDTO subscription);
    SubscriptionDTO updateSubscription(UUID id, SubscriptionDTOUpdate subscriptionUpdate);
    void deleteSubscription(UUID id);
}

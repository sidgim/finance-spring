package com.glara.msvcsubscription.infrastructure.repositories;

import com.glara.msvcsubscription.domain.Subscription;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface SubscriptionRepository extends CrudRepository<Subscription, UUID> {

    Optional<Subscription> findByIdAndDeletedFalse(UUID id);

}

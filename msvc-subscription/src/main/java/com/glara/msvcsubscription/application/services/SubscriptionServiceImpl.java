package com.glara.msvcsubscription.application.services;

import com.glara.msvcsubscription.application.mapper.SubscriptionMapper;
import com.glara.msvcsubscription.domain.Subscription;
import com.glara.msvcsubscription.infrastructure.repositories.SubscriptionRepository;
import com.glara.springcloud.commons.dto.SubscriptionDTO;
import com.glara.springcloud.commons.dto.SubscriptionDTOUpdate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class SubscriptionServiceImpl implements SubscriptionService {
    private final Logger log = LoggerFactory.getLogger(SubscriptionServiceImpl.class);
    private final SubscriptionRepository subscriptionRepository;
    private final SubscriptionMapper subscriptionMapper;

    public SubscriptionServiceImpl(SubscriptionRepository subscriptionRepository, SubscriptionMapper subscriptionMapper) {
        this.subscriptionRepository = subscriptionRepository;
        this.subscriptionMapper = subscriptionMapper;
    }



    @Override
    @Transactional(readOnly = true)
    public Page<SubscriptionDTO> getAllSubscriptions(Pageable pageable) {
        Page<Subscription> subscriptionPage = subscriptionRepository.findAll(pageable);
        return subscriptionPage.map(subscriptionMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public SubscriptionDTO getSubscriptionById(UUID id) {
        // Esta es la parte que corrige el error que mencionaste
        Subscription subscription = this.subscriptionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Suscripción con ID " + id + " no encontrada"));

        return subscriptionMapper.toDto(subscription);
    }

    @Override
    @Transactional
    public SubscriptionDTO createSubscription(SubscriptionDTO subscriptionDTO) {
        // Convierte el DTO de entrada a entidad
        Subscription subscription = subscriptionMapper.toEntity(subscriptionDTO);
        Subscription savedSubscription = subscriptionRepository.save(subscription);
        // Convierte la entidad guardada de vuelta a DTO para la respuesta
        return subscriptionMapper.toDto(savedSubscription);
    }

    @Override
    @Transactional
    public SubscriptionDTO updateSubscription(UUID id, SubscriptionDTOUpdate subscriptionUpdateDTO) {
        Subscription existingSubscription = subscriptionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Suscripción con ID " + id + " no encontrada para actualizar"));

        // Usa el mapper para actualizar los campos del DTO al objeto entidad existente
        subscriptionMapper.updateEntityFromDto(subscriptionUpdateDTO, existingSubscription);

        Subscription updatedSubscription = subscriptionRepository.save(existingSubscription);
        return subscriptionMapper.toDto(updatedSubscription);
    }

    @Override
    @Transactional
    public void deleteSubscription(UUID id) {
        // Considera si necesitas verificar la existencia antes de eliminar
        // o manejar la excepción si deleteById lanza una para un ID inexistente
        if (!subscriptionRepository.existsById(id)) {
            throw new RuntimeException("Suscripción con ID " + id + " no encontrada para eliminar");
        }
        subscriptionRepository.deleteById(id);
    }
}

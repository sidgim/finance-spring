package com.glara.msvcsubscription.infrastructure.controllers;

import com.glara.msvcsubscription.application.services.SubscriptionService;
import com.glara.springcloud.commons.dto.SubscriptionDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/subscription")
public class SubscriptionController {
    private final SubscriptionService subscriptionService;

    public SubscriptionController(SubscriptionService subscriptionService) {
        this.subscriptionService = subscriptionService;
    }

    @GetMapping
    public List<SubscriptionDTO> getSubscriptions() {
        // Aquí puedes llamar a tu servicio para obtener las suscripciones
        return subscriptionService.getAllSubscriptions(); // Reemplaza con la lógica real
    }

    @GetMapping("/{id}")
    public SubscriptionDTO getSubscriptionById(String id) {

        return subscriptionService.getSubscriptionById(UUID.fromString(id)); // Reemplaza con la lógica real
    }

    @PostMapping
    public SubscriptionDTO createSubscription(@RequestBody SubscriptionDTO subscriptionDTO) {
        return subscriptionService.createSubscription(subscriptionDTO);
    }

}

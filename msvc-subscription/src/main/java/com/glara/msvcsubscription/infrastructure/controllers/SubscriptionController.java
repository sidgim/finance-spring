package com.glara.msvcsubscription.infrastructure.controllers;

import com.glara.msvcsubscription.application.services.SubscriptionService;
import com.glara.msvcsubscription.config.ApiStandardErrors;
import com.glara.springcloud.commons.dto.SubscriptionDTO;
import com.glara.springcloud.commons.dto.SubscriptionDTOUpdate;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/subscription")
@Tag(name = "Suscripciones", description = "API para la gestión de suscripciones")
public class SubscriptionController {
    private final SubscriptionService subscriptionService;

    public SubscriptionController(SubscriptionService subscriptionService) {
        this.subscriptionService = subscriptionService;
    }

    @Operation(summary = "Obtiene una lista paginada de suscripciones")
    @ApiResponse(responseCode = "200", description = "Lista de suscripciones obtenida exitosamente")
    @ApiStandardErrors
    @GetMapping
    public Page<SubscriptionDTO> getSubscriptions(Pageable pageable) {
        return subscriptionService.getAllSubscriptions(pageable);
    }

    @Operation(summary = "Obtiene una suscripción por ID")
    @ApiResponse(responseCode = "200", description = "Suscripción obtenida exitosamente")
    @ApiStandardErrors
    @GetMapping("/{id}")
    public SubscriptionDTO getSubscriptionById(@PathVariable String id) {
        return subscriptionService.getSubscriptionById(UUID.fromString(id));
    }

    @Operation(summary = "Crea una nueva suscripción")
    @ApiResponse(responseCode = "201", description = "Suscripción creada exitosamente")
    @ApiStandardErrors
    @PostMapping
    public SubscriptionDTO createSubscription(@RequestBody SubscriptionDTO subscriptionDTO) {
        return subscriptionService.createSubscription(subscriptionDTO);
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Actualiza una suscripción existente")
    @ApiResponse(responseCode = "200", description = "Suscripción actualizada exitosamente")
    @ApiStandardErrors
    public SubscriptionDTO updateSubscription(
            @PathVariable String id,
            @RequestBody SubscriptionDTOUpdate subscriptionDTOUpdate) {
        return subscriptionService.updateSubscription(UUID.fromString(id), subscriptionDTOUpdate);
    }

    @Operation(summary = "Elimina una suscripción por ID")
    @ApiResponse(responseCode = "204", description = "Suscripción eliminada exitosamente")
    @ApiStandardErrors
    @DeleteMapping("/{id}")
    public void deleteSubscription(@PathVariable String id) {
        subscriptionService.deleteSubscription(UUID.fromString(id));
    }

}

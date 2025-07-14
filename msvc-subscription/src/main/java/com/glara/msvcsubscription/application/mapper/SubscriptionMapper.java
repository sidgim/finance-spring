package com.glara.msvcsubscription.application.mapper;


import com.glara.msvcsubscription.domain.Subscription;
import com.glara.springcloud.commons.dto.SubscriptionDTO;
import com.glara.springcloud.commons.dto.SubscriptionDTOUpdate;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring",
        uses = { MappingUtils.class } // <-- AÑADE ESTA LÍNEA
)
public interface  SubscriptionMapper {
    // --- Mapeo de Entidad a DTO ---
    @Mapping(source = "frequency", target = "frequency", qualifiedByName = "frequencyEnumToString")
    @Mapping(source = "paymentDate", target = "paymentDate", qualifiedByName = "localDateToDate")
    @Mapping(source = "amount", target = "amount", qualifiedByName = "bigDecimalToDouble")
    SubscriptionDTO toDto(Subscription subscription);

    // --- Mapeo de DTO a Entidad (para crear) ---
    // Record DTOs no tienen ID, así que lo ignoramos al crear la entidad
    @Mapping(target = "id", ignore = true)
    @Mapping(source = "frequency", target = "frequency", qualifiedByName = "frequencyStringToEnum")
    @Mapping(source = "paymentDate", target = "paymentDate", qualifiedByName = "dateToLocalDate")
    @Mapping(source = "amount", target = "amount", qualifiedByName = "doubleToBigDecimal")
    Subscription toEntity(SubscriptionDTO subscriptionDTO);

    // --- Mapeo para actualizar una entidad existente desde un DTO de actualización ---
    @Mapping(target = "id", ignore = true) // El ID de la entidad existente no se actualiza
    @Mapping(source = "frequency", target = "frequency", qualifiedByName = "frequencyStringToEnum")
    @Mapping(source = "paymentDate", target = "paymentDate", qualifiedByName = "dateToLocalDate")
    @Mapping(source = "amount", target = "amount", qualifiedByName = "doubleToBigDecimal")
    void updateEntityFromDto(SubscriptionDTOUpdate subscriptionUpdate, @MappingTarget Subscription subscription);

    // --- Mapeo de Listas ---
    List<SubscriptionDTO> toDtoList(List<Subscription> subscriptions);

}

package com.glara.msvcsubscription.application.mapper;

import com.glara.msvcsubscription.domain.Subscription;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

@Component
public class MappingUtils {

    // --- Métodos para convertir Frecuencia (Enum <-> String) ---
    @Named("frequencyEnumToString")
    public String frequencyEnumToString(Subscription.Frequency frequency) {
        return frequency != null ? frequency.name() : null;
    }

    @Named("frequencyStringToEnum")
    public Subscription.Frequency frequencyStringToEnum(String frequency) {
        return frequency != null ? Subscription.Frequency.valueOf(frequency) : null;
    }

    // --- Métodos para convertir Fechas (LocalDate <-> Date) ---
    @Named("localDateToDate")
    public Date localDateToDate(LocalDate localDate) {
        return localDate != null ? Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant()) : null;
    }

    @Named("dateToLocalDate")
    public LocalDate dateToLocalDate(Date date) {
        return date != null ? date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate() : null;
    }

    // --- Métodos para convertir Montos (BigDecimal <-> Double) ---
    // Nota: El log muestra un error en "BigDecimal a BigDecimal", pero tu mapping
    // pide "BigDecimal a Double". Aquí está la implementación para ambos.
    @Named("bigDecimalToDouble")
    public Double bigDecimalToDouble(BigDecimal value) {
        return value != null ? value.doubleValue() : null;
    }

    @Named("doubleToBigDecimal")
    public BigDecimal doubleToBigDecimal(Double value) {
        return value != null ? BigDecimal.valueOf(value) : null;
    }
}
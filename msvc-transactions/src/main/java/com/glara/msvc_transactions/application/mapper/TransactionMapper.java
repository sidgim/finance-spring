package com.glara.msvc_transactions.application.mapper;

import com.glara.msvc_transactions.domain.entities.Transaction;
import com.glara.msvc_transactions.dto.TransactionDTO;
import com.glara.msvc_transactions.dto.TransactionType;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TransactionMapper {

    // mapeo de String transactionType → enum TransactionType
    @Mapping(source = "transactionType", target = "type")
    TransactionDTO toDto(Transaction transaction);

    // inversa: enum → String, y le decimos que ignore createdAt para que lo ponga DB/JPA
    @InheritInverseConfiguration
    @Mapping(target = "createdAt", ignore = true)
    Transaction toEntity(TransactionDTO dto);

    // métodos auxiliares para el enum (MapStruct los detecta automáticamente)
    default TransactionType map(String tipo) {
        return TransactionType.fromString(tipo);
    }

    default String map(TransactionType tipo) {
        return tipo.toString();
    }
}


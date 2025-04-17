package com.glara.msvc_transactions.application.mapper;

import com.glara.msvc_transactions.domain.entities.Vendor;
import com.glara.msvc_transactions.dto.VendorDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface VendorMapper {

    VendorDTO toDTO(Vendor vendor);

    Vendor toEntity(VendorDTO vendorDTO);


}

package com.glara.msvc_transactions.application.services;



import com.glara.msvc_transactions.application.mapper.VendorMapper;
import com.glara.msvc_transactions.domain.entities.Vendor;
import com.glara.msvc_transactions.dto.VendorDTO;
import com.glara.msvc_transactions.infrastructure.repository.VendorRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class VendorService {

    private final VendorRepository vendorRepository;

    private final VendorMapper vendorMapper;

    public VendorService(VendorRepository vendorRepository, VendorMapper vendorMapper) {
        this.vendorRepository = vendorRepository;
        this.vendorMapper = vendorMapper;
    }

    @Transactional(readOnly = true)
    public List<VendorDTO> findAllVendor() throws Exception {
        List<Vendor> vendors = (List<Vendor>) vendorRepository.findAll();
        if (vendors.isEmpty()) {
            throw new Exception("Vendors not found");
        }
        return vendors.stream().map(vendorMapper::toDTO).toList();
    }

    @Transactional
    public VendorDTO createVendor(VendorDTO vendorDTO) throws Exception {
        Vendor saved = vendorRepository.save(vendorMapper.toEntity(vendorDTO));
        return vendorMapper.toDTO(saved);
    }

    @Transactional
    public VendorDTO updateVendor(UUID id, VendorDTO vendorDTO) throws Exception {
        if (!vendorRepository.existsById(id)) {
            throw new Exception("Vendor not updated: Vendor not found");
        }
        Vendor updated = vendorRepository.save(vendorMapper.toEntity(vendorDTO));

        return vendorMapper.toDTO(updated);
    }

    @Transactional
    public void deleteVendor(UUID id) throws Exception {
        if (!vendorRepository.existsById(id)) {
            throw new Exception("Vendor not deleted: Vendor not found");
        }
        vendorRepository.deleteById(id);
    }
}
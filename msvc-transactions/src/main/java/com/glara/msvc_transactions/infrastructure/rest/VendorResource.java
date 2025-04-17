package com.glara.msvc_transactions.infrastructure.rest;

import com.glara.msvc_transactions.application.services.VendorService;
import com.glara.msvc_transactions.dto.VendorDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/vendor", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
public class VendorResource {


    private VendorService vendorService;


    public VendorResource(VendorService vendorService) {
        this.vendorService = vendorService;
    }

    @GetMapping
    public List<VendorDTO> getAllVendors() throws Exception {
        return vendorService.findAllVendor();
    }

    @PostMapping
    public VendorDTO createVendor(@RequestBody VendorDTO vendorDTO) throws Exception {
        return vendorService.createVendor(vendorDTO);
    }

    @PutMapping("/{id}")
    public VendorDTO updateVendor(@PathVariable("id") UUID id, @RequestBody VendorDTO vendorDTO) throws Exception {
        return vendorService.updateVendor(id, vendorDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteVendor(@PathVariable("id") UUID id) throws Exception {
        vendorService.deleteVendor(id);
    }
}
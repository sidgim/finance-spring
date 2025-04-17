package com.glara.msvc_transactions.infrastructure.repository;


import com.glara.msvc_transactions.domain.entities.Vendor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface VendorRepository extends CrudRepository<Vendor,UUID> {
}

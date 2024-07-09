package com.example.SmartApparel.Operations.repo;

import com.example.SmartApparel.Operations.entity.Quotation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuotationRepository extends JpaRepository<Quotation, Long> {
    // Add custom query methods if needed
}

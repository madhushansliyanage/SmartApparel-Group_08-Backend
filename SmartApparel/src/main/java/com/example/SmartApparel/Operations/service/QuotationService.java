package com.example.SmartApparel.Operations.service;

import com.example.SmartApparel.Operations.entity.Quotation;
import com.example.SmartApparel.Operations.repo.QuotationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuotationService {

    @Autowired
    private QuotationRepository quotationRepository;

    public List<Quotation> getAllQuotations() {
        return quotationRepository.findAll();
    }

    public Quotation getQuotationById(Long id) {
        return quotationRepository.findById(id).orElse(null);
    }

    public Quotation saveQuotation(Quotation quotation) {
        return quotationRepository.save(quotation);
    }

    public void deleteQuotation(Long id) {
        quotationRepository.deleteById(id);
    }
}
package com.example.SmartApparel.Operations.service;

import com.example.SmartApparel.Operations.entity.Quotation;

import com.example.SmartApparel.Operations.repo.QuotationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuotationService {
    @Autowired
    //private QuotationRepository quotationRepository;
    private QuotationRepository quotationRepository;
    public QuotationService(QuotationRepository quotationRepository) {
        this.quotationRepository = quotationRepository;
    }

    public Quotation saveQuotation(Quotation quotation) {
        return quotationRepository.save(quotation);
    }
}
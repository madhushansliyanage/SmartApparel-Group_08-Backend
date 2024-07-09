package com.example.SmartApparel.Operations.controller;

import com.example.SmartApparel.Operations.entity.Quotation;
import com.example.SmartApparel.Operations.service.QuotationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/quotations")
public class QuotationController {

    @Autowired
    private QuotationService quotationService;

    @GetMapping
    public List<Quotation> getAllQuotations() {
        return quotationService.getAllQuotations();
    }

    @GetMapping("/{id}")
    public Quotation getQuotationById(@PathVariable Long id) {
        return quotationService.getQuotationById(id);
    }

    @PostMapping
    public Quotation createQuotation(@RequestBody Quotation quotation) {
        return quotationService.saveQuotation(quotation);
    }

    @DeleteMapping("/{id}")
    public void deleteQuotation(@PathVariable Long id) {
        quotationService.deleteQuotation(id);
    }
}
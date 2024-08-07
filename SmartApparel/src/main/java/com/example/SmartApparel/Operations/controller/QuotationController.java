package com.example.SmartApparel.Operations.controller;

import com.example.SmartApparel.Operations.entity.Quotation;

import com.example.SmartApparel.Operations.service.QuotationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(path = "/smart-apperal/api")
public class QuotationController {
    @Autowired

    private QuotationService quotationService;
    public QuotationController(QuotationService quotationService) {
        this.quotationService = quotationService;
    }

    @PostMapping("/quotation")
    public ResponseEntity<?> submitQuotation(@RequestBody Quotation quotation) {
        try {
            Quotation savedQuotation = quotationService.saveQuotation(quotation);
            return ResponseEntity.ok("Quotation submitted successfully with ID: " + savedQuotation.getId());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error processing quotation: " + e.getMessage());
        }
    }
}
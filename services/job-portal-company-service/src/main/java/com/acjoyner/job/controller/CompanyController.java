package com.acjoyner.job.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.acjoyner.job.domain.CompanyStatus;
import com.acjoyner.job.domain.CompanyType;
import com.acjoyner.job.domain.IndustryType;
import com.acjoyner.job.dto.ApiResonse;
import com.acjoyner.job.dto.CompanyRequest;
import com.acjoyner.job.dto.CompanyResponse;
import com.acjoyner.job.service.CompanyService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/api/companies")
@RequiredArgsConstructor
public class CompanyController {

    private final CompanyService companyService;

    @PostMapping
    public ResponseEntity<CompanyResponse> createCompany(
            @RequestHeader("X-User-Id") Long ownerId,
            @RequestBody @Valid CompanyRequest companyRequest) throws Exception {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(companyService.createCompany(ownerId, companyRequest));
    }

    @PostMapping("/{id}")
    public ResponseEntity<CompanyResponse> getCompanyById(
            @PathVariable Long id) throws Exception {
        return ResponseEntity.status(HttpStatus.OK)
                .body(companyService.getCompanyById(id));
    }

    @PostMapping("/my")
    public ResponseEntity<CompanyResponse> getMyCompany(
            @RequestHeader("X-User-Id") Long ownerId) throws Exception {
        return ResponseEntity.status(HttpStatus.OK)
                .body(companyService.getMyCompany(ownerId));
    }

    @GetMapping
    public ResponseEntity<List<CompanyResponse>> getAllCompanies(
            @RequestParam(required = false) CompanyType companyType,
            @RequestParam(required = false) IndustryType industryType,
            @RequestParam(required = false) CompanyStatus status) {
        return ResponseEntity.ok(companyService.getAllComponies(companyType, industryType, status));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CompanyResponse> updateCompany(
            @PathVariable Long id,
            @RequestHeader("X-User-Id") Long ownerId,
            @RequestBody @Valid CompanyRequest req)
            throws Exception {

        return ResponseEntity.ok(companyService.updateCompany(id, ownerId, req));
    }

    @PatchMapping("/{id}/verify")
    public ResponseEntity<CompanyResponse> verifyCompany(
            @PathVariable Long id) throws Exception {
        return ResponseEntity.ok(companyService.verifyCompany(id));
    }
    
    @PatchMapping("/{id}/deactivate")
    public ResponseEntity<CompanyResponse> deactivateCompany(
            @PathVariable Long id) throws Exception {
        return ResponseEntity.ok(companyService.deactivateCompany(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResonse> deleteCompany(
        @PathVariable Long id,
        @RequestHeader("X-User-Id") Long ownerId) throws Exception {
            companyService.deleteComnay(id, ownerId);
            return ResponseEntity.ok(new ApiResonse("Company sucessfully deleted", true));
        }

}

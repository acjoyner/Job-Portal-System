package com.acjoyner.job.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.acjoyner.job.domain.CompanyStatus;
import com.acjoyner.job.domain.CompanyType;
import com.acjoyner.job.domain.IndustryType;
import com.acjoyner.job.dto.CompanyRequest;
import com.acjoyner.job.dto.CompanyResponse;
import com.acjoyner.job.model.Company;

@Service
public interface CompanyService {
    CompanyResponse createCompany(Long id, CompanyRequest req) throws Exception;

    CompanyResponse getCompanyById(Long id) throws Exception;

    CompanyResponse getMyCompany(Long ownerId) throws Exception;

    List<CompanyResponse> getAllCompanies(CompanyType companyType,
            IndustryType industryType,
            CompanyStatus companyStatus);

    CompanyResponse updateCompany(Long companyId,
            Long ownerId,
            CompanyRequest requ) throws Exception;

    CompanyResponse verifyCompany(Long companyId) throws Exception;

    void deleteCompany(Long companyId, Long ownerId) throws Exception;

    CompanyResponse deactivateCompany(Long companyId) throws Exception;

    Company getCompanyEntityById(Long id) throws Exception;

}

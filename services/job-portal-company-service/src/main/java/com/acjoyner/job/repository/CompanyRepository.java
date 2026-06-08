package com.acjoyner.job.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.acjoyner.job.domain.CompanyStatus;
import com.acjoyner.job.domain.CompanyType;
import com.acjoyner.job.domain.IndustryType;
import com.acjoyner.job.model.Company;
import java.util.List;
import java.util.Optional;


@Repository
public interface CompanyRepository extends JpaRepository<Company, Long>{
    Optional<Company> findByOwnerId(Long ownerId);
    boolean existsByOwnerId(Long id);
    boolean existsByName(String name);
    boolean existsBySlug(String slug);
    boolean existsByRegistrationNumber(String registrationNumber);
    
    @Query(
        "SELECT c FROM Company c WHERE " +
        "(:companyType IS NULL OR c.companyType = :companyType) AND " +
        "(:industryType IS NULL OR c.industryType = :industryType) AND " +
        "(:status IS NULL OR c.status = :status)"
    )

    List<Company> findByFilters(
        @Param("companyType") CompanyType companyType,
        @Param("industryType") IndustryType industryType,
        @Param("status") CompanyStatus companyStatus
    );



}

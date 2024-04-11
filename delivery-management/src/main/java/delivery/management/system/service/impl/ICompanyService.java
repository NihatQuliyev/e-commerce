package delivery.management.system.service.impl;

import common.exception.model.exception.ApplicationException;
import common.exception.model.service.ExceptionService;
import delivery.management.system.model.entity.Company;
import delivery.management.system.repository.CompanyRepository;
import delivery.management.system.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class ICompanyService implements CompanyService {

    private final CompanyRepository companyRepository;
    private final ExceptionService exceptionService;


    @Override
    public Company increaseCompanyBalance(BigDecimal cartAmount) {
        Company company =  companyRepository.findAll().stream()
                        .findAny()
                                .orElseThrow(() -> new ApplicationException(exceptionService.exceptionResponse("Company not found", HttpStatus.NOT_FOUND)));

        company.setTotal_budget(company.getTotal_budget().add(cartAmount));
        companyRepository.save(company);
        return company;
    }
}

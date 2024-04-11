package delivery.management.system.service;

import delivery.management.system.model.entity.Company;

import java.math.BigDecimal;

public interface CompanyService {
    Company increaseCompanyBalance(BigDecimal totalAmount);
}

package com.sajee.myJobWeb.company;

import java.util.List;
public interface CompanyService {

    List<Company> getAllCompanies();
    boolean updateCompanyById(Long id, Company updateCompany);
    boolean createCompany(Company company);
    boolean deleteByCompanyId(Long id);
    Company findByCompanyId(Long id);

}

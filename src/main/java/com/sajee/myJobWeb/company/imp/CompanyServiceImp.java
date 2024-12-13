package com.sajee.myJobWeb.company.imp;

import com.sajee.myJobWeb.company.Company;
import com.sajee.myJobWeb.company.CompanyRepository;
import com.sajee.myJobWeb.company.CompanyService;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CompanyServiceImp  implements CompanyService {

    private final CompanyRepository companyRepository;

    public CompanyServiceImp(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Override
    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    @Override
    public boolean updateCompanyById(Long id, Company updateCompany) {
        Optional<Company> companyOptional = companyRepository.findById(id);
        if (companyOptional.isPresent()){
            Company company = companyOptional.get();
            company.setName(updateCompany.getName());
            company.setDescription(updateCompany.getDescription());
            company.setJobs(updateCompany.getJobs());

            companyRepository.save(company);
            return true;
        }
        else
            return false;
    }

    @Override
    public boolean createCompany(Company company) {
        try {
            companyRepository.save(company);
            return true;
        }catch (Exception ex) {
            System.out.println("-------------"+ex.getMessage()+"-------------");
            return false;
        }
    }

    @Override
    public boolean deleteByCompanyId(Long id) {
        if (companyRepository.existsById(id)) {

            companyRepository.deleteById(id);
            return true;
        }
        else
            return false;
    }

    @Override
    public Company findByCompanyId(Long id) {
        return companyRepository.findById(id).orElse(null);
    }

}

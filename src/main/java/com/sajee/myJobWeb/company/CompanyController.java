package com.sajee.myJobWeb.company;

import com.sajee.myJobWeb.job.Job;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Iterator;
import java.util.List;

@RestController
@RequestMapping("/companies")
public class CompanyController {

    private final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Company>> findAll(){
        List<Company> company = companyService.getAllCompanies();
        if( !company.isEmpty() ){
            return new ResponseEntity<>(company, HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(company, HttpStatus.NOT_FOUND);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateCompanyById(@PathVariable Long id, @RequestBody Company updateCompany){
        boolean result = companyService.updateCompanyById(id, updateCompany);
        String msg = "Company updated successfully existing with Id: "+ id;
        if (result)
            return new ResponseEntity<>(msg, HttpStatus.OK);
        return new ResponseEntity<>("Oops! Company not existing with id: "+id, HttpStatus.NOT_FOUND);
    }

    @PostMapping("/create")
    public ResponseEntity<String> createCompany(@RequestBody Company company){
        boolean response = companyService.createCompany(company);
        if (response)
            return new ResponseEntity<>("Company created successfully!", HttpStatus.OK);
        else
            return new ResponseEntity<>("Something went wrong! Please check again...", HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteCompanyById(@PathVariable Long id){
        boolean isDeleted = companyService.deleteByCompanyId(id);
        if (isDeleted)
            return new ResponseEntity<>("Company deleted successfully with id: "+id, HttpStatus.OK);
        else
            return new ResponseEntity<>("No company existing with id: "+id, HttpStatus.NOT_FOUND);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Company> getCompanyById(@PathVariable Long id){
        Company company = companyService.findByCompanyId(id);
        if(company!=null){
            return new ResponseEntity<>(company, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(company, HttpStatus.NOT_FOUND);
        }
    }

}

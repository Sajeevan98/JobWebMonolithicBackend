package com.sajee.myJobWeb.job.imp;

import com.sajee.myJobWeb.company.Company;
import com.sajee.myJobWeb.company.CompanyService;
import com.sajee.myJobWeb.job.Job;
import com.sajee.myJobWeb.job.JobRepository;
import com.sajee.myJobWeb.job.JobService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JobServiceImp implements JobService {

    private final JobRepository jobRepository;
    private final CompanyService companyService;

    public JobServiceImp(JobRepository jobRepository, CompanyService companyService) {
        this.jobRepository = jobRepository;
        this.companyService = companyService;
    }

    @Override
    public List<Job> getAll() {
        return jobRepository.findAll();
    }

    @Override
    public boolean createJob(Long companyId, Job job) {
        Company company = companyService.findByCompanyId(companyId); // get company details through company-id.
        job.setCompany(company);
        if (company != null) {
            jobRepository.save(job);
            return true;
        }
        else
            return false;
    }

    @Override
    public Job getById(Long id) {
        Optional<Job> job = jobRepository.findById(id);
        if(job.isPresent()){
            return jobRepository.findById(id).orElse(null);
        }
        else
            return null;
    }

    @Override
    public boolean deleteById(Long id) {
        try{
            jobRepository.deleteById(id);
            return true;
        }catch (Exception ex){
            System.out.println("==================="+ex.getMessage()+"===================");
            return false;
        }
    }

    @Override
    public boolean updateById(Long id, Job updateJob) {
        Optional<Job> jobOptional = jobRepository.findById(id);
            if (jobOptional.isPresent()){
                Job job = jobOptional.get();
                job.setTitle(updateJob.getTitle());
                job.setDescription(updateJob.getDescription());
                job.setMinSalary(updateJob.getMinSalary());
                job.setMaxSalary(updateJob.getMaxSalary());
                job.setLocation(updateJob.getLocation());
                job.setCompany(updateJob.getCompany());
                jobRepository.save(job);
                return true;
            }
        return false;
    }

    @Override
    public List<Job> getJobByCompanyId(Long companyId) {
        return jobRepository.findByCompanyId(companyId);
    }
}

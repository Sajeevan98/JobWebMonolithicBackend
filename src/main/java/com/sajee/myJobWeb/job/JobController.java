package com.sajee.myJobWeb.job;

import com.sajee.myJobWeb.company.Company;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/jobs")
public class JobController {

    private final JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @GetMapping
    public ResponseEntity<List<Job>> findAllJop(){
        List<Job> job = jobService.getAll();
        if( !job.isEmpty() ){
            return new ResponseEntity<>(job, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @PostMapping("/company/{companyId}")
    public ResponseEntity<String> addJob(@PathVariable Long companyId, @RequestBody Job job){
        boolean response = jobService.createJob(companyId, job);
        if(response)
            return new ResponseEntity<>("Job successfully created to Company-Id: "+companyId, HttpStatus.OK);
        else
            return new ResponseEntity<>("Oops! Company Not existing with Id: "+companyId, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Company> getJobById(@PathVariable Long id){
        Job job = jobService.getById(id);
        if(job!=null){

            Company jobWithCompanyDetails = job.getCompany();
            return new ResponseEntity<>(jobWithCompanyDetails, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteJobById(@PathVariable Long id){
        boolean result = jobService.deleteById(id);
        String msg = "Job deleted successfully existing with Id: "+ id;
        if (result)
            return new ResponseEntity<>(msg, HttpStatus.OK);
        return new ResponseEntity<>("Oops! Job not existing with id: "+id, HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateJobById(@PathVariable Long id, @RequestBody Job updateJob){
        boolean result = jobService.updateById(id, updateJob);
        String msg = "Job updated successfully existing with Id: "+ id;
        if (result)
            return new ResponseEntity<>(msg, HttpStatus.OK);
        return new ResponseEntity<>("Oops! Job not existing with id: "+id, HttpStatus.NOT_FOUND);
    }

    @GetMapping("/company/{companyId}")
    public ResponseEntity<List<Job>> getAllJobFromCompany(@PathVariable Long companyId){
        List<Job> response = jobService.getJobByCompanyId(companyId);
        if (!response.isEmpty())
            return new ResponseEntity<>(response, HttpStatus.OK);
        else
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

}

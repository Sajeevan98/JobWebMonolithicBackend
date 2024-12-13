package com.sajee.myJobWeb.job;

import java.util.List;

public interface JobService {

    List<Job> getAll();
    boolean createJob(Long companyId, Job job);
    Job getById(Long id);
    boolean deleteById(Long id);
    boolean updateById(Long id, Job updateJob);
    List<Job> getJobByCompanyId(Long companyId);
}

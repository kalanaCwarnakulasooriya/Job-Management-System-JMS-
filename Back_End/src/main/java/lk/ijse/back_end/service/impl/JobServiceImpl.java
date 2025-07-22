package lk.ijse.back_end.service.impl;

import lk.ijse.back_end.dto.JobDTO;
import lk.ijse.back_end.entity.Job;
import lk.ijse.back_end.repository.JobRepository;
import lk.ijse.back_end.service.JobService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class JobServiceImpl implements JobService {
    private final JobRepository JOBREPOSITORY;
    private final ModelMapper MODELMAPPER;

    @Override
    public void saveJob(JobDTO jobDTO) {
        if (jobDTO == null) {
            log.error("JobDTO cannot be null");
            throw new IllegalArgumentException("JobDTO cannot be null");
        }
        JOBREPOSITORY.save(MODELMAPPER.map(jobDTO, Job.class));
    }

    @Override
    public void updateJob(JobDTO jobDTO) {
        if (jobDTO == null) {
            throw new IllegalArgumentException("JobDTO cannot be null");
        }
        JOBREPOSITORY.findById(jobDTO.getId()).orElseThrow(() -> new ResourceNotFound("Job not found"));
        JOBREPOSITORY.save(MODELMAPPER.map(jobDTO, Job.class));
    }

    @Override
    public List<JobDTO> getAllJobs() {
        List<Job> allJobs = JOBREPOSITORY.findAll();
        if (allJobs.isEmpty()) {
            throw new ResourceNotFound("Job not found");
        }
        return MODELMAPPER.map(allJobs, new TypeToken<Collection<JobDTO>>() {}.getType());
    }

    @Override
    public void changeJobStatus(String jobId) {
        if (jobId == null) {
            throw new IllegalArgumentException("JobId cannot be null");
        }
        JOBREPOSITORY.updateJobStatus(Integer.parseInt(jobId));
    }

    @Override
    public List<JobDTO> getAllJobsByKeyword(String keyword) {
        if (keyword == null) {
            throw new IllegalArgumentException("Keyword cannot be null");
        }
        List<Job> alljobs = JOBREPOSITORY.findJobByJobTitleContainingIgnoreCase(keyword);

        if (alljobs.isEmpty()) {
            throw new ResourceNotFound("Job not found");
        }
        return MODELMAPPER.map(alljobs, new TypeToken<List<JobDTO>>(){}.getType());
    }

}

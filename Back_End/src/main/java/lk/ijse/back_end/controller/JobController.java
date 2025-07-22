package lk.ijse.back_end.controller;

import jakarta.validation.Valid;
import lk.ijse.back_end.dto.JobDTO;
import lk.ijse.back_end.service.JobService;
import lk.ijse.back_end.util.APIResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/job")
@RequiredArgsConstructor //meka dmmhm @Autowired annotation ek danna onna
@CrossOrigin
@Slf4j
public class JobController {
    private final JobService JOBSERVICE;
//    private static final Logger logger = LoggerFactory.getLogger(JobController.class);

    @PostMapping("create")
    public ResponseEntity<APIResponse<Object>> createJob(@RequestBody @Valid JobDTO jobDTO) {
        log.info("Job Created Successfully");
        log.debug("Job Creation Debugged");
        log.error("Job Creation Failed");
        log.warn("Job Creation Warned");
        log.trace("Job Creation traced");
        JOBSERVICE.saveJob(jobDTO);
        return ResponseEntity.ok(new APIResponse<>(
                200,
                "Job Saved Successfully",
                null
        ));
    }

    @PutMapping("update")
    public ResponseEntity<APIResponse<Object>> updateJob(@RequestBody JobDTO jobDTO) {
        JOBSERVICE.updateJob(jobDTO);
        return ResponseEntity.ok(new APIResponse<>(
                200,
                "Job Updated Successfully",
                null
        ));
    }

    @GetMapping("getall")
    public ResponseEntity<APIResponse<List<JobDTO>>> getAllJobs() {
        List<JobDTO> jobDTOS = JOBSERVICE.getAllJobs();
        return ResponseEntity.ok(new APIResponse<>(
                200,
                "Job List Fetched Successfully",
                jobDTOS
        ));
    }

    @PatchMapping("status/{id}")
    private ResponseEntity<APIResponse<Object>> changeJobStatus(@PathVariable("id") String jobId) {
        JOBSERVICE.changeJobStatus(jobId);
        return ResponseEntity.ok(new APIResponse<>(
                200,
                "Job Status Changed Successfully",
                null
        ));
    }

    @GetMapping("search/{keyword}")
    public ResponseEntity<APIResponse<List<JobDTO>>> searchJob(@PathVariable("keyword") String keyword) {
        List<JobDTO> jobDTOS = JOBSERVICE.getAllJobsByKeyword(keyword);
        return ResponseEntity.ok(new APIResponse<>(
                200,
                "Job List Search Fetched Successfully",
                jobDTOS
        ));
    }
}

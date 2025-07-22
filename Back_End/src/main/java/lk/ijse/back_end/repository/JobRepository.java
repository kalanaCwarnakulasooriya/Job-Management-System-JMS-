package lk.ijse.back_end.repository;

import jakarta.transaction.Transactional;
import lk.ijse.back_end.entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobRepository extends JpaRepository<Job,Integer> {
    @Transactional
    @Modifying
    @Query(value = "UPDATE job SET status = 'Deactivated' WHERE id = ?1", nativeQuery = true)
    void updateJobStatus(Integer jobId);

    List<Job> findJobByJobTitleContainingIgnoreCase(String keyword);
}

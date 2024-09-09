package ai.rnt.customerFeedback.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ai.rnt.customerFeedback.entity.EmployeePerformenceMailBox;

public interface EmployeePerformenceMailBoxRepo extends JpaRepository<EmployeePerformenceMailBox, Integer> {

	@Query(value = "select * from employee_performance_mailbox where emp_perf_id = ?1", nativeQuery = true)
	EmployeePerformenceMailBox findByEmployeePerformance(int id);

}

package ai.rnt.customerFeedback.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ai.rnt.customerFeedback.entity.EmployeePerformenceFeedback;

public interface EmployeePerformenceFeedbackRepository extends JpaRepository<EmployeePerformenceFeedback, Integer> {

	List<EmployeePerformenceFeedback> findByEmployeePerformenceMasterEmpPerformenceIdOrderByQuestionMasterQuestionIdAsc(
			Integer id);

	List<EmployeePerformenceFeedback> findByEmployeePerformenceMasterEmpPerformenceId(Integer id);

}

package ai.rnt.customerFeedback.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ai.rnt.customerFeedback.entity.EmployeePerformenceMaster;

public interface EmployeePerformenceMasterRepository extends JpaRepository<EmployeePerformenceMaster, Integer> {

}

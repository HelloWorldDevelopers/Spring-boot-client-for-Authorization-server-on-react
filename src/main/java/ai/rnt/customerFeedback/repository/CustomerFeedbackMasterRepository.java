package ai.rnt.customerFeedback.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ai.rnt.customerFeedback.entity.CustomerFeedbackMaster;

public interface CustomerFeedbackMasterRepository extends JpaRepository<CustomerFeedbackMaster, Integer> {

}
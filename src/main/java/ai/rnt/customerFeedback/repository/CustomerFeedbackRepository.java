package ai.rnt.customerFeedback.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ai.rnt.customerFeedback.entity.CustomerFeedback;

public interface CustomerFeedbackRepository extends JpaRepository<CustomerFeedback, Integer> {

	List<CustomerFeedback> findByCustomerFeedbackMasterCustFeedbackId(int id);

	List<CustomerFeedback> findByCustomerFeedbackMasterCustFeedbackIdOrderByQuestionMasterQuestionIdAsc(int id);

}

package ai.rnt.customerFeedback.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ai.rnt.customerFeedback.entity.CustomerFeedbackMailBox;

public interface CustomerFeedbackMailBoxRepo extends JpaRepository<CustomerFeedbackMailBox, Integer> {

	@Query(value = "select * from customer_feedback_mailbox where cust_feedback_id = ?1", nativeQuery = true)
	CustomerFeedbackMailBox findByCustomerFeedback(int customerId);
}

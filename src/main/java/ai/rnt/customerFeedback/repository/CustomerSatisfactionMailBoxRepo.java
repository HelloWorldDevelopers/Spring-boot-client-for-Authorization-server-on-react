package ai.rnt.customerFeedback.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ai.rnt.customerFeedback.entity.CustomerSatisfactionMailBox;

public interface CustomerSatisfactionMailBoxRepo extends JpaRepository<CustomerSatisfactionMailBox, Integer> {

	@Query(value = "select * from customer_satisfaction_mailbox where cust_satisfaction_id = ?1", nativeQuery = true)
	CustomerSatisfactionMailBox findByCustomerSatisfaction(int id);

}

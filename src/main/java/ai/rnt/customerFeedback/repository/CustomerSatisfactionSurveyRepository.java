/**
 * 
 */
package ai.rnt.customerFeedback.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ai.rnt.customerFeedback.entity.CustomerSatisfactionSurvey;

/**
 * @author Abhishek Ingle
 * @Date Jan 11, 2024
 * @Version 1.0
 */
public interface CustomerSatisfactionSurveyRepository extends JpaRepository<CustomerSatisfactionSurvey, Integer> {

	List<CustomerSatisfactionSurvey> findByCustomerSatisfactionMasterCustSatisfactionId(int id);

	List<CustomerSatisfactionSurvey> findByCustomerSatisfactionMasterCustSatisfactionIdOrderByQuestionMasterQuestionIdAsc(
			int id);
}

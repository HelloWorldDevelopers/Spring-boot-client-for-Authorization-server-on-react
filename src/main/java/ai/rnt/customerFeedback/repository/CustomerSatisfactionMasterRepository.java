/**
 * 
 */
package ai.rnt.customerFeedback.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ai.rnt.customerFeedback.entity.CustomerSatisfactionMaster;

/**
 * @author Abhishek Ingle
 * @Date Jan 11, 2024
 * @Version 1.0
 */
public interface CustomerSatisfactionMasterRepository extends JpaRepository<CustomerSatisfactionMaster, Integer> {

}

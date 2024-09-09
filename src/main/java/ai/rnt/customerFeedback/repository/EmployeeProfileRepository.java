/**
 * @author Akash Bhor
 */
package ai.rnt.customerFeedback.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ai.rnt.customerFeedback.entity.EmployeeProfile;

/**
 * @author Akash Bhor
 *
 */
@Repository
public interface EmployeeProfileRepository extends JpaRepository<EmployeeProfile, Integer> {

}

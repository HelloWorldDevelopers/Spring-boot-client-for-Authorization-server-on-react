package ai.rnt.customerFeedback.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ai.rnt.customerFeedback.entity.EmployeeMaster;

public interface EmployeeMasterRepository extends JpaRepository<EmployeeMaster, Integer> {

	Optional<EmployeeMaster> findByUserId(String userId);

	@Query(value = "SELECT mail_password FROM corp_mail where mail_id='uatapps@rabbitandtortoise.com' ", nativeQuery = true)
	String getEmailPassword();

}

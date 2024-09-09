package ai.rnt.customerFeedback.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ai.rnt.customerFeedback.entity.VendorOnboardingMailBox;

public interface VendorOnboardingMailBoxRepo extends JpaRepository<VendorOnboardingMailBox, Integer> {

	@Query(value = "select * from vendor_onboarding_mailbox where kyc_id = ?1", nativeQuery = true)
	VendorOnboardingMailBox findByVendoronboardingId(int id);

}

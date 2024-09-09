package ai.rnt.customerFeedback.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ai.rnt.customerFeedback.entity.VenderBankDetails;

public interface VendorBankDetailsRepository extends JpaRepository<VenderBankDetails, Integer> {

}

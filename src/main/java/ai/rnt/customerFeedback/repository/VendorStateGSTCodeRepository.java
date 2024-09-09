package ai.rnt.customerFeedback.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ai.rnt.customerFeedback.entity.VendorStateGSTCode;

@Repository
public interface VendorStateGSTCodeRepository extends JpaRepository<VendorStateGSTCode, Integer> {

	VendorStateGSTCode findByStateGstCode(Integer gstCode);
}

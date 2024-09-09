package ai.rnt.customerFeedback.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ai.rnt.customerFeedback.entity.VendorKYC;

@Repository
public interface VendorKYCRepository extends JpaRepository<VendorKYC, Integer> {

	VendorKYC findByvendorKycId(Integer vendorKycId);

	List<VendorKYC> findAllByOrderByCreatedDateDescVendorKycIdDesc();

}

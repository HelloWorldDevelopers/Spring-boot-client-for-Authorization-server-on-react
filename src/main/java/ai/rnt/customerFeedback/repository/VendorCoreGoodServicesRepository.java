package ai.rnt.customerFeedback.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ai.rnt.customerFeedback.entity.VendorCoreGoodServices;

@Repository
public interface VendorCoreGoodServicesRepository extends JpaRepository<VendorCoreGoodServices, Integer> {

}

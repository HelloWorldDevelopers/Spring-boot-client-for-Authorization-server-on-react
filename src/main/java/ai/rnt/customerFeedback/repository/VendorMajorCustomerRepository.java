package ai.rnt.customerFeedback.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ai.rnt.customerFeedback.entity.VendorMajorCustomer;

@Repository
public interface VendorMajorCustomerRepository extends JpaRepository<VendorMajorCustomer, Integer> {

}

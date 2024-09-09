package ai.rnt.customerFeedback.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ai.rnt.customerFeedback.entity.VendorServiceProvider;

@Repository
public interface VendorServiceProviderRepository extends JpaRepository<VendorServiceProvider, Integer> {

}

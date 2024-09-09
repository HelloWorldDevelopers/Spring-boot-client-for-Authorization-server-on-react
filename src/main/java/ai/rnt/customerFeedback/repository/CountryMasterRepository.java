package ai.rnt.customerFeedback.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ai.rnt.customerFeedback.entity.CountryMaster;

@Repository
public interface CountryMasterRepository extends JpaRepository<CountryMaster, Integer> {

}

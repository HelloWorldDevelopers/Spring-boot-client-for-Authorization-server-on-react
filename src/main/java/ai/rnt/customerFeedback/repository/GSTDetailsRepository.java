package ai.rnt.customerFeedback.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ai.rnt.customerFeedback.entity.GSTDetails;

@Repository
public interface GSTDetailsRepository extends JpaRepository<GSTDetails, Integer> {

}

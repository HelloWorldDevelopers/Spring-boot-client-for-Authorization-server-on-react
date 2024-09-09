package ai.rnt.customerFeedback.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ai.rnt.customerFeedback.entity.QuestionMaster;

public interface QuestionRepository extends JpaRepository<QuestionMaster, Integer> {
	List<QuestionMaster> findByFormType(Integer formType);
}
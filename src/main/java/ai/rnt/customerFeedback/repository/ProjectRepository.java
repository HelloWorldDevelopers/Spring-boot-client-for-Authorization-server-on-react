package ai.rnt.customerFeedback.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

import ai.rnt.customerFeedback.entity.Project;

public interface ProjectRepository extends JpaRepository<Project, Integer> {

	Collection<Project> findByCustomerCustomerIdOrderByProjectNameAsc(int id);

	Collection<Project> findByCustomerCustomerIdAndProjectNameIsNotNullOrderByProjectNameAsc(int id);

}

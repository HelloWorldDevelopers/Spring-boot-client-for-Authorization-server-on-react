package ai.rnt.customerFeedback.entity;

import static javax.persistence.GenerationType.IDENTITY;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.Where;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "project")
@Setter
@Getter
@NoArgsConstructor
@Where(clause = "deleted_by is null")
public class Project extends Auditable {
	private static final long serialVersionUID = 931377982617396405L;

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "Project_ID")
	private Integer projectId;

	@Column(name = "Project_Name")
	private String projectName;

	@Column(name = "project_alias")
	private String projectAlias;

	@Column(name = "start_date")
	private LocalDate projectStartDate;

	@Column(name = "end_date")
	private LocalDate projectEndDate;

	@Column(name = "project_manager")
	private Integer projectManager;

	@ManyToOne
	@JoinColumn(name = "customer_id")
	private Customer customer;

	@LazyCollection(LazyCollectionOption.TRUE)
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
	@JoinTable(name = "project_team", joinColumns = {
			@JoinColumn(name = "project_ID", nullable = false) }, inverseJoinColumns = {
					@JoinColumn(name = "staff_ID", nullable = false) })

	private Set<EmployeeMaster> employeeMaster = new HashSet<>();

}

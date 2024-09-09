package ai.rnt.customerFeedback.entity;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Where;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "employee_performance_master")
@Setter
@Getter
@NoArgsConstructor
@Where(clause = "deleted_by is null")
public class EmployeePerformenceMaster extends Auditable {

	private static final long serialVersionUID = 5635410671051941762L;

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "emp_perf_id")
	private Integer empPerformenceId;

	@OneToOne(mappedBy = "employeePerformenceMaster", fetch = FetchType.LAZY)
	private EmployeePerformenceMailBox employeePerformenceMailBox;

	@ManyToOne
	@JoinColumn(name = "Staff_ID")
	private EmployeeMaster employeeMaster;

//	@Column(name = "project_team_id")
//	private Integer projectTeamId;

	@Column(name = "project_manager_name")
	private String projectManagerName;

	@Column(name = "project_manager_email")
	private String projectManagerEmail;

	@ManyToOne
	@JoinColumn(name = "Project_ID")
	private Project project;

	@Transient
	private Boolean filled;

	@Transient
	private String filledDate;

	@Transient
	private Integer sortStatus;

	@Transient
	private Boolean isEmailSend;

}

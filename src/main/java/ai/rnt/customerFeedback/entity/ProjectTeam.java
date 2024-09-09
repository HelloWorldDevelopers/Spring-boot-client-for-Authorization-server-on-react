package ai.rnt.customerFeedback.entity;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Where;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "project_team")
@Setter
@Getter
@NoArgsConstructor
@Where(clause = "deleted_by is null")
public class ProjectTeam extends Auditable {

	private static final long serialVersionUID = 931377982617396405L;

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "project_team_id")
	private Integer projectTeamId;

	@Column(name = "report_lead_name")
	private String reportLeadName;

	@Column(name = "report_lead_email")
	private String reportLeadEmail;

	@Column(name = "report_lead_contact_no")
	private String reportLeadContactNo;

	@ManyToOne
	@JoinColumn(name = "project_id")
	private Project project;

	@ManyToOne
	@JoinColumn(name = "staff_id")
	private EmployeeMaster employeeMastar;

	@ManyToOne
	@JoinColumn(name = "role_id")
	private RoleMaster roleMaster;

}

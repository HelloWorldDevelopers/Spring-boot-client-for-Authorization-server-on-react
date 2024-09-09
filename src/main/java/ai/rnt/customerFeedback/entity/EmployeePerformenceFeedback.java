package ai.rnt.customerFeedback.entity;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
@Table(name = "employee_performance_feedback")
@Setter
@Getter
@NoArgsConstructor
@Where(clause = "deleted_by is null")
public class EmployeePerformenceFeedback extends Auditable {

	private static final long serialVersionUID = 2129174594975969547L;

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "emp_perf_fb_id")
	private Integer empPerfFeedbackId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "emp_perf_id")
	private EmployeePerformenceMaster employeePerformenceMaster;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "question_id")
	private QuestionMaster questionMaster;

	@Column(name = "answer")
	private String answer;

}

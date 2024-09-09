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
@Table(name = "customer_feedback_master")
@Setter
@Getter
@NoArgsConstructor
@Where(clause = "deleted_by is null")
public class CustomerFeedbackMaster extends Auditable {
	private static final long serialVersionUID = 931377982617396405L;

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "cust_feedback_id")
	private Integer custFeedbackId;

	@OneToOne(mappedBy = "customerFeedbackMaster", fetch = FetchType.LAZY)
	private CustomerFeedbackMailBox customerFeedbackMailBox;

	@Column(name = "role")
	private String role;

	@Column(name = "feedback_by")
	private String feedbackBy;

	@Column(name = "email_id")
	private String emailId;

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

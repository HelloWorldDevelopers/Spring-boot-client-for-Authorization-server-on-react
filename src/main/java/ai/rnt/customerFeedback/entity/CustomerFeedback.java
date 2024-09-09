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
@Table(name = "customer_feedback")
@Setter
@Getter
@NoArgsConstructor
@Where(clause = "deleted_by is null")
public class CustomerFeedback extends Auditable {

	private static final long serialVersionUID = 931377982617396405L;

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "customer_feedback_id")
	private Integer customerFeedbackId;

	@Column(name = "answer")
	private String answer;

	@ManyToOne
	@JoinColumn(name = "cust_feedback_id")
	private CustomerFeedbackMaster customerFeedbackMaster;

	@ManyToOne
	@JoinColumn(name = "question_id")
	private QuestionMaster questionMaster;
}

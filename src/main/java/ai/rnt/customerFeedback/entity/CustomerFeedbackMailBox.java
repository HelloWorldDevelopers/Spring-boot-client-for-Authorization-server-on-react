package ai.rnt.customerFeedback.entity;

import static javax.persistence.GenerationType.IDENTITY;
import static org.hibernate.annotations.LazyToOneOption.PROXY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.Where;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "customer_feedback_mailbox")
@Setter
@Getter
@NoArgsConstructor
@Where(clause = "deleted_by is null")
public class CustomerFeedbackMailBox extends Auditable {
	/**
	* 
	*/
	private static final long serialVersionUID = -5871637763538811821L;

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "feedback_mailbox_id")
	private Integer mailboxId;

	@LazyToOne(PROXY)
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cust_feedback_id", unique = true)
	private CustomerFeedbackMaster customerFeedbackMaster;

	@Column(name = "from_email")
	private String fromEmail;

	@Column(name = "to_email")
	private String toEmail;

	@Column(name = "email_subject")
	private String subject;

	@Column(name = "email_body")
	private String emailBody;

}

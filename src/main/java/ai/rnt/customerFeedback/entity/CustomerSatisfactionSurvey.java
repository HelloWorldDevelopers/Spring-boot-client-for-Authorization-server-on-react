/**
 * 
 */
package ai.rnt.customerFeedback.entity;

import static javax.persistence.CascadeType.DETACH;
import static javax.persistence.CascadeType.MERGE;
import static javax.persistence.CascadeType.REFRESH;
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

/**
 * @author Abhishek Ingle
 * @Date Jan 8, 2024
 * @Version 1.0
 */

@Entity
@Table(name = "customer_satisfaction_survey")
@Setter
@Getter
@NoArgsConstructor
@Where(clause = "deleted_by is null")
public class CustomerSatisfactionSurvey extends Auditable {

	private static final long serialVersionUID = -6110145587459233661L;

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "customer_satisfaction_survey_id")
	private Integer customerSatisfactionSurveyId;

	@Column(name = "answer")
	private String answer;

	@ManyToOne(cascade = { MERGE, DETACH, REFRESH })
	@JoinColumn(name = "cust_satisfaction_id")
	private CustomerSatisfactionMaster customerSatisfactionMaster;

	@ManyToOne(cascade = { MERGE, DETACH, REFRESH })
	@JoinColumn(name = "question_id")
	private QuestionMaster questionMaster;

}

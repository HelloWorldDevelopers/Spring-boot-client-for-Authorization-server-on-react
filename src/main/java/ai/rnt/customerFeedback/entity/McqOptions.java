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

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "mcq_options")
@Setter
@Getter
@NoArgsConstructor
@Where(clause = "deleted_by is null")
public class McqOptions extends Auditable {
	private static final long serialVersionUID = 931377982617396405L;

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "mcq_option_id")
	private Integer mcqOptionId;

	@Column(name = "option")
	private String option;

	@ManyToOne(cascade = { MERGE, DETACH, REFRESH })
	@JoinColumn(name = "question_id")
	@JsonBackReference
	private QuestionMaster questionMaster;

}

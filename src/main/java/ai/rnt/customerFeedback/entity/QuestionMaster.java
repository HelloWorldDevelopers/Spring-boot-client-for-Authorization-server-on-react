package ai.rnt.customerFeedback.entity;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Where;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "question_master")
@Setter
@Getter
@NoArgsConstructor
@Where(clause = "deleted_by is null")
public class QuestionMaster extends Auditable {
	private static final long serialVersionUID = 931377982617396405L;

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "question_id")
	private Integer questionId;

	@Column(name = "is_mcq_type")
	private Integer isMcqType;

	@Column(name = "form_type")
	private Integer formType;

	@Column(name = "question")
	private String question;

	@OneToMany(mappedBy = "questionMaster")
	private List<McqOptions> mcqOptions;

}

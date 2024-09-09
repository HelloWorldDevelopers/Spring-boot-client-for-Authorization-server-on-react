package ai.rnt.customerFeedback.entity;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Where;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Akash Bhor
 *
 */
@Entity
@Table(name = "employee_profile")
@Setter
@Getter
@NoArgsConstructor
@Where(clause = "deleted_by is null")
public class EmployeeProfile extends Auditable {

	private static final long serialVersionUID = 931377982617396405L;

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "profile_id")
	private Integer profileId;

	@Column(name = "profile_picture")
	private String profilePicture;
}

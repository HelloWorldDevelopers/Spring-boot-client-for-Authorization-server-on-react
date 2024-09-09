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

@Entity
@Table(name = "customer")
@Setter
@Getter
@NoArgsConstructor
@Where(clause = "deleted_by is null")
public class Customer extends Auditable {
	private static final long serialVersionUID = 931377982617396405L;

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "Customer_ID")
	private Integer customerId;

	@Column(name = "Company_Name")
	private String companyName;

	@Column(name = "email_id")
	private String emailId;

	@Column(name = "contact_person_number")
	private String customerContactNumber;

	@Column(name = "contact_person_name")
	private String contactPersonName;

}

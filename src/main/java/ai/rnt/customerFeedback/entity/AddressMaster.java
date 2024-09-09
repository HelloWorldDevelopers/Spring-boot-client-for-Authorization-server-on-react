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
@Table(name = "address_master")
@Setter
@Getter
@NoArgsConstructor
@Where(clause = "deleted_by is null")
public class AddressMaster extends Auditable {

	private static final long serialVersionUID = -1622379289446226156L;

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "address_id")
	private Integer addressId;

	@ManyToOne
	@JoinColumn(name = "customer_id")
	private Customer customer;

	@Column(name = "contact_person_name")
	private String contactPersonName;

	@Column(name = "contact_person_email")
	private String contactPersonEmail;

	@Column(name = "contact_person_no")
	private String contactPersonNo;

}

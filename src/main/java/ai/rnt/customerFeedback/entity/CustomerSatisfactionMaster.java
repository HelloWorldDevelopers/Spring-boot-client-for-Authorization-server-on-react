package ai.rnt.customerFeedback.entity;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

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
@Table(name = "customer_satisfaction_master")
@Setter
@Getter
@NoArgsConstructor
@Where(clause = "deleted_by is null")
public class CustomerSatisfactionMaster extends Auditable {

	private static final long serialVersionUID = 5635410671051941762L;

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "cust_satisfaction_id")
	private Integer custSatisfactionId;

	@OneToOne(mappedBy = "customerSatisfactionMaster", fetch = FetchType.LAZY)
	private CustomerSatisfactionMailBox customerSatisfactionMailBox;

	@Column(name = "address_id")
	private Integer addressId;

//	@ManyToOne
//	@JoinColumn(name = "customer_id")
//	private Customer customer;

	@Transient
	private Boolean filled;

	@Transient
	private Integer sortStatus;

	@Transient
	private String filledDate;

	@Transient
	private String pseudoName;

	@Transient
	private String formCreatedDate;

	@Transient
	private Boolean isEmailSend;

}

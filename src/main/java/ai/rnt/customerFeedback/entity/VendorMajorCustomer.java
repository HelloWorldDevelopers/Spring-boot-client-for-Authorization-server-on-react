package ai.rnt.customerFeedback.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Where;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "vendor_major_customers")
@Setter
@Getter
@NoArgsConstructor
@Where(clause = "deleted_by is null")
public class VendorMajorCustomer extends Auditable {
	/**
	* 
	*/
	private static final long serialVersionUID = -8487284927361435356L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "major_customer_id")
	private Integer majorCustomerId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "kyc_id")
	private VendorKYC vendorKYC;

	@Column(name = "major_customer")
	private String majorCustomer;

}

package ai.rnt.customerFeedback.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.Where;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "vendor_country_master")
@Setter
@Getter
@NoArgsConstructor
@Where(clause = "deleted_by is null")
public class CountryMaster extends Auditable {
	/**
	* 
	*/
	private static final long serialVersionUID = 7399497777811952697L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "country_id")
	private Integer countryId;

	@LazyCollection(LazyCollectionOption.TRUE)
	@OneToMany(mappedBy = "billingcountryMaster", fetch = FetchType.LAZY)
	private List<VendorKYC> vendorKYCListForBilling;

	@LazyCollection(LazyCollectionOption.TRUE)
	@OneToMany(mappedBy = "shippingcountryMaster", fetch = FetchType.LAZY)
	private List<VendorKYC> vendorKYCListForShipping;

	@LazyCollection(LazyCollectionOption.TRUE)
	@OneToMany(mappedBy = "countryMaster", fetch = FetchType.LAZY)
	private List<VenderBankDetails> bankaddress;

//	@OneToOne(mappedBy = "countryMaster", fetch = FetchType.LAZY)
//	private VenderBankDetails bankaddress;

	@Column(name = "country_name")
	private String country;

}

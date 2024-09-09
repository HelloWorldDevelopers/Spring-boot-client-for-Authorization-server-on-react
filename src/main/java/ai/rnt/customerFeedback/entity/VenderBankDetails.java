package ai.rnt.customerFeedback.entity;

import static javax.persistence.GenerationType.IDENTITY;
import static org.hibernate.annotations.LazyToOneOption.PROXY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.Where;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "vendor_bank_details")
@Setter
@Getter
@NoArgsConstructor
@Where(clause = "deleted_by is null")
public class VenderBankDetails extends Auditable {

	private static final long serialVersionUID = 1138048316124003849L;

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "vendor_bank_dtl_id")
	private Integer vendorBankDtlId;

	@LazyToOne(PROXY)
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "kyc_id", unique = true)
	private VendorKYC vendorKYC;

	@Column(name = "bank_name")
	private String bankName;

	@Column(name = "branch")
	private String branch;

	@Column(name = "beneficiary_acc_name")
	private String beneficiaryAccName;

	@Column(name = "account_no")
	private Long accountNo;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "country_id")
	private CountryMaster countryMaster;

	@Column(name = "bank_address_line1")
	private String bankAddressLine1;

	@Column(name = "bank_address_line2")
	private String bankAddressLine2;

	@Column(name = "city")
	private String city;

	@Column(name = "state")
	private String state;

	@Column(name = "zip_code")
	private String zipCode;

	@Column(name = "intermediary_bank_dtl")
	private String intermediaryBankDtl;

	@Column(name = "acc_currency")
	private String accCurrency;

	@Column(name = "ifsc_code")
	private String ifscCode;

	@Column(name = "swift_code")
	private String swiftCode;

	@Column(name = "company_turnover")
	private String companyTurnover;

	@Column(name = "is_itr_filed")
	private String isItrFiled;

}

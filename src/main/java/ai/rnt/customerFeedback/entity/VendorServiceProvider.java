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
@Table(name = "vendor_service_provider")
@Setter
@Getter
@NoArgsConstructor
@Where(clause = "deleted_by is null")
public class VendorServiceProvider extends Auditable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -35877812437562692L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "service_provider_id")
	private Integer svcsProviderId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "kyc_id")
	private VendorKYC vendorKYC;

	@Column(name = "desc_of_service")
	private String descOfServices;

	@Column(name = "service_acc_code")
	private String serviceAccgCode;

	@Column(name = "gst_rate_expected")
	private String gstRateExpected;

	@Column(name = "is_comp_scheme_under_gst")
	private String isCompSchemeUnderGst;

}

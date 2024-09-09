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
@Table(name = "vendor_gst")
@Setter
@Getter
@NoArgsConstructor
@Where(clause = "deleted_by is null")
public class GSTDetails extends Auditable {
	private static final long serialVersionUID = 3051308994165912760L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "gst_dtl_id")
	private Integer gstDtlId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "kyc_id")
	private VendorKYC vendorKYC;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "state_gst_code_id")
	private VendorStateGSTCode vendorStateGSTCode;

	@Column(name = "gst_no")
	private String gstNo;

	@Column(name = "pan_no")
	private String panNo;

	@Column(name = "gst_address")
	private String gstAddress;

}

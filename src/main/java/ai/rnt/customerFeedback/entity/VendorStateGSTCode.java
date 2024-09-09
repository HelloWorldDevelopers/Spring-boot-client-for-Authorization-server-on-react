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
@Table(name = "vendor_state_gst_codes")
@Setter
@Getter
@NoArgsConstructor
@Where(clause = "deleted_by is null")
public class VendorStateGSTCode extends Auditable {
	/**
	* 
	*/
	private static final long serialVersionUID = 4286326732211374548L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "state_gst_code_id")
	private Integer stateGstCodeId;

	@LazyCollection(LazyCollectionOption.TRUE)
	@OneToMany(mappedBy = "vendorStateGSTCode", fetch = FetchType.LAZY)
	private List<GSTDetails> gstdetailsList;

	@Column(name = "state_gst_code")
	private Integer stateGstCode;

	@Column(name = "state")
	private String state;

}

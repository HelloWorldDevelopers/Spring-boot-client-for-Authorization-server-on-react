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
@Table(name = "vendor_core_goods_services")
@Setter
@Getter
@NoArgsConstructor
@Where(clause = "deleted_by is null")
public class VendorCoreGoodServices extends Auditable {

	private static final long serialVersionUID = -8487284927361435356L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "service_id")
	private Integer serviceId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "kyc_id")
	private VendorKYC vendorKYC;

	@Column(name = "core_goods_service")
	private String coreGoodsService;
}

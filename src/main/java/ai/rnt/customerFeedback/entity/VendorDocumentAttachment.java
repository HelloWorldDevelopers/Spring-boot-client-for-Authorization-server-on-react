package ai.rnt.customerFeedback.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Where;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "vendor_document_attachment")
@Setter
@Getter
@NoArgsConstructor
@Where(clause = "deleted_by is null")
public class VendorDocumentAttachment extends Auditable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8532598128201123621L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "attachment_id")
	private Integer attachmentId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "document_id")
	private VendorDocumentMaster vendorDocumentMaster;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "kyc_id")
	private VendorKYC vendorKYC;

	@Lob
	@Column(name = "document_attachment")
	private String documentAttachment;

	@Column(name = "file_type")
	private String fileType;

	@Column(name = "file_name")
	private String fileName;

}

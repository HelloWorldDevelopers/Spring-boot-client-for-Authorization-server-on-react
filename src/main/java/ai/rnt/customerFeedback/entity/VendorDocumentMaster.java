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
@Table(name = "vendor_document_master")
@Setter
@Getter
@NoArgsConstructor
@Where(clause = "deleted_by is null")
public class VendorDocumentMaster extends Auditable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2113817857160590386L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "document_id")
	private Integer documentId;

	@LazyCollection(LazyCollectionOption.TRUE)
	@OneToMany(mappedBy = "vendorDocumentMaster", fetch = FetchType.LAZY)
	private List<VendorDocumentAttachment> vendorDocumentAttachment;

	@Column(name = "document_type")
	private String documentType;

}

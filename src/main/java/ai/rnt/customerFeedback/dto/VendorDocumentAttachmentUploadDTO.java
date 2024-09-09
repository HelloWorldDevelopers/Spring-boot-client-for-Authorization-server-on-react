package ai.rnt.customerFeedback.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VendorDocumentAttachmentUploadDTO {
	
	private Integer attachmentId;

	private String documentAttachment;
	
	private VendorKYCNewDTO vendorKYC;

	private String fileType;

	private String fileName;

	private VendorDocumentMasterDto vendorDocumentMaster;
}

package ai.rnt.customerFeedback.dto;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VendorDocumentAttachmentDto {

	private Integer attachmentId;

	private String documentAttachment;

	private String fileType;

	private String fileName;
	
	private LocalDateTime createdDate;

	private VendorDocumentMasterDto vendorDocumentMaster;
}

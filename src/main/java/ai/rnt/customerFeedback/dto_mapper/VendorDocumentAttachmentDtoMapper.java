package ai.rnt.customerFeedback.dto_mapper;

import static ai.rnt.customerFeedback.util.CollectionUtil.newList;
import static ai.rnt.customerFeedback.util.FunctionUtil.evalMapperCollection;
import static ai.rnt.customerFeedback.util.FunctionUtil.evalMapper;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import ai.rnt.customerFeedback.dto.VendorDocumentAttachmentDto;
import ai.rnt.customerFeedback.dto.VendorDocumentAttachmentUploadDTO;
import ai.rnt.customerFeedback.entity.VendorDocumentAttachment;

public class VendorDocumentAttachmentDtoMapper {

	private VendorDocumentAttachmentDtoMapper() {
	}

	public static final Function<Collection<VendorDocumentAttachment>, List<VendorDocumentAttachmentDto>> TO_DOCUMENT_ATTACHMENT_DTO_LIST = e -> newList(
			evalMapperCollection(e, VendorDocumentAttachmentDto.class));

	public static final Function<Collection<VendorDocumentAttachmentDto>, List<VendorDocumentAttachment>> TO_DOCUMENT_ATTACHMENT_LIST = e -> newList(
			evalMapperCollection(e, VendorDocumentAttachment.class));
	
	public static final Function<VendorDocumentAttachmentUploadDTO, Optional<VendorDocumentAttachment>> TO_DOCUMENT_DTO = e -> evalMapper(e,
			VendorDocumentAttachment.class);
}

package ai.rnt.customerFeedback.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ai.rnt.customerFeedback.entity.VendorDocumentAttachment;

@Repository
public interface VendorDocumentAttachmentRepository extends JpaRepository<VendorDocumentAttachment, Integer> {

}

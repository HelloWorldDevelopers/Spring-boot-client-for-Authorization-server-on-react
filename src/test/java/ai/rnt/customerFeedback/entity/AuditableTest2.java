package ai.rnt.customerFeedback.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

class AuditableTest2 {
	private static class ConcreteAuditable extends Auditable {
	}

	@Test
	void testAuditableInMyEntity() {
		ConcreteAuditable myEntity = new ConcreteAuditable();

		myEntity.setCreatedBy(1);
		myEntity.setCreatedDate(LocalDateTime.now());

		myEntity.setUpdatedBy(2);
		myEntity.setUpdatedDate(LocalDateTime.now());

		myEntity.setDeletedBy(3);
		myEntity.setDeletedDate(LocalDateTime.now());

		assertEquals(1, myEntity.getCreatedBy());
		assertNotNull(myEntity.getCreatedDate());

		assertEquals(2, myEntity.getUpdatedBy());
		assertNotNull(myEntity.getUpdatedDate());

		assertEquals(3, myEntity.getDeletedBy());
		assertNotNull(myEntity.getDeletedDate());
	}

	@Test
	void testAuditable() {
		ConcreteAuditable auditable = new ConcreteAuditable();

		auditable.beforPersist();
		Integer createdByPersist = auditable.getCreatedBy();
		LocalDateTime createdDatePersist = auditable.getCreatedDate();

		auditable.beforUpdate();
		Integer updatedByUpdate = auditable.getUpdatedBy();
		LocalDateTime updatedDateUpdate = auditable.getUpdatedDate();

		auditable.beforDelete();
		Integer deletedByDelete = auditable.getDeletedBy();
		LocalDateTime deletedDateDelete = auditable.getDeletedDate();

		assertEquals(1, createdByPersist);
		assertNotNull(createdDatePersist);

		assertEquals(1, updatedByUpdate);
		assertNotNull(updatedDateUpdate);

		assertEquals(1, deletedByDelete);
		assertNotNull(deletedDateDelete);
	}

}
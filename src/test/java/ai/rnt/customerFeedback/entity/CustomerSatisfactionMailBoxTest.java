package ai.rnt.customerFeedback.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

class CustomerSatisfactionMailBoxTest {

	@Test
	void test() {
		CustomerSatisfactionMailBox moduleMailBox = new CustomerSatisfactionMailBox();

		moduleMailBox.setMailboxId(1);
		moduleMailBox.setFromEmail("uatrntapps@rabbitandtortoise.com");
		moduleMailBox.setToEmail("n.raut@rnt.ai");
		moduleMailBox.setSubject("subject for mail");
		moduleMailBox.setEmailBody("email body for sending mail");
		CustomerSatisfactionMaster customerFeedback = new CustomerSatisfactionMaster();
		moduleMailBox.setCustomerSatisfactionMaster(customerFeedback);
		assertEquals(1, moduleMailBox.getMailboxId());
		assertEquals("uatrntapps@rabbitandtortoise.com", moduleMailBox.getFromEmail());
		assertEquals("n.raut@rnt.ai", moduleMailBox.getToEmail());
		assertEquals("subject for mail", moduleMailBox.getSubject());
		assertEquals("email body for sending mail", moduleMailBox.getEmailBody());

		moduleMailBox.setCreatedBy(1);
		moduleMailBox.setCreatedDate(LocalDateTime.now());
		moduleMailBox.setUpdatedBy(2);
		moduleMailBox.setUpdatedDate(LocalDateTime.now());
		moduleMailBox.setDeletedBy(3);
		moduleMailBox.setDeletedDate(LocalDateTime.now());

		assertEquals(1, moduleMailBox.getCreatedBy());
		assertNotNull(moduleMailBox.getCreatedDate());
		assertEquals(2, moduleMailBox.getUpdatedBy());
		assertNotNull(moduleMailBox.getUpdatedDate());
		assertEquals(3, moduleMailBox.getDeletedBy());
		assertNotNull(moduleMailBox.getDeletedDate());
	}

}

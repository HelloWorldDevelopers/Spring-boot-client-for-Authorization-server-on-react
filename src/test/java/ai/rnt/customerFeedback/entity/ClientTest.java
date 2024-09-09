//package ai.rnt.customerFeedback.entity;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertNotNull;
//
//import java.time.LocalDateTime;
//
//import org.junit.jupiter.api.Test;
//
//class ClientTest {
//
//	@Test
//	void test() {
//		Client client = new Client();
//		// Set Auditable properties
//		client.setCreatedBy(1);
//		client.setCreatedDate(LocalDateTime.now());
//		client.setUpdatedBy(2);
//		client.setUpdatedDate(LocalDateTime.now());
//		client.setDeletedBy(3);
//		client.setDeletedDate(LocalDateTime.now());
//
//		// Check Auditable properties
//		assertEquals(1, client.getCreatedBy());
//		assertNotNull(client.getCreatedDate());
//		assertEquals(2, client.getUpdatedBy());
//		assertNotNull(client.getUpdatedDate());
//		assertEquals(3, client.getDeletedBy());
//		assertNotNull(client.getDeletedDate());
//	}
//
//}

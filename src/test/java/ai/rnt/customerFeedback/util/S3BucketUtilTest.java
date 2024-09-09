package ai.rnt.customerFeedback.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import ai.rnt.customerFeedback.exception.CRMException;

@ExtendWith(MockitoExtension.class)
class S3BucketUtilTest {

	@Mock
	private RestTemplate restTemplate;

	@InjectMocks
	private S3BucketUtil s3BucketUtil;

	@BeforeEach
	void setUp() {
		s3BucketUtil = new S3BucketUtil();
		s3BucketUtil.restTemplate = restTemplate;
		s3BucketUtil.bucketName = "bucket-name";
		s3BucketUtil.bugImage = "bug-image-folder";
		s3BucketUtil.serverType = "test";
	}

	@Test
	void testGetBugImage_Success() {
		String imageName = "testImage.jpg";
		String expectedUrl = "http://172.20.1.38:8080/rnt-vault/api/v1/getFiles/" + imageName
				+ "?path=bucket-name/bug-image-folder";
		String responseBody = "image data";

		when(restTemplate.getForEntity(eq(expectedUrl), eq(String.class))).thenReturn(ResponseEntity.ok(responseBody));

		Map<String, Object> result = s3BucketUtil.getBugImage(imageName);

		assertTrue((boolean) result.get("SUCCESS"));
		assertEquals(expectedUrl, result.get("MESSAGE"));
		assertEquals(responseBody, result.get("DATA"));
		assertEquals(imageName, result.get("DOCUMENTNAME"));
	}

	@Test
	void testGetBugImage_Exception() {
		String imageName = "testImage.jpg";
		String expectedUrl = "http://172.20.1.38:8080/rnt-vault/api/v1/getFiles/" + imageName
				+ "?path=bucket-name/bug-image-folder";

		when(restTemplate.getForEntity(eq(expectedUrl), eq(String.class)))
				.thenThrow(new RuntimeException("Error occurred"));

		CRMException exception = null;
		try {
			s3BucketUtil.getBugImage(imageName);
		} catch (CRMException e) {
			exception = e;
		}

		assertTrue(exception instanceof CRMException);
	}
}

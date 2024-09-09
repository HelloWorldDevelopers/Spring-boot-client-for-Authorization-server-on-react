package ai.rnt.customerFeedback.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ContentTypeUtilTest {

	@Test
	public void testGetContentTypeName_ExistingContentType() {
		String contentType = "application/pdf";
		String result = ContentTypeUtil.getContentTypeName(contentType);
		assertEquals("PDF", result);
	}

	@Test
	public void testGetContentTypeName_NonExistingContentType() {
		String contentType = "application/json";
		String result = ContentTypeUtil.getContentTypeName(contentType);
		assertEquals("OTHER", result);
	}

	@Test
	public void testGetContentTypeName_NullContentType() {
		String result = ContentTypeUtil.getContentTypeName(null);
		assertEquals("OTHER", result);
	}

	@Test
	public void testGetContentTypeName_EmptyContentType() {
		String contentType = "";
		String result = ContentTypeUtil.getContentTypeName(contentType);
		assertEquals("OTHER", result);
	}

	@Test
	public void testGetContentTypeName_WhitespaceContentType() {
		String contentType = "   ";
		String result = ContentTypeUtil.getContentTypeName(contentType);
		assertEquals("OTHER", result);
	}

	@Test
	public void testGetContentTypeName_MultipleContentTypes() {
		String contentType1 = "application/pdf";
		String contentType2 = "image/jpeg";
		String contentType3 = "text/plain";

		String result1 = ContentTypeUtil.getContentTypeName(contentType1);
		String result2 = ContentTypeUtil.getContentTypeName(contentType2);
		String result3 = ContentTypeUtil.getContentTypeName(contentType3);

		assertEquals("PDF", result1);
		assertEquals("IMAGE", result2);
		assertEquals("PLAIN TEXT", result3);
	}

}

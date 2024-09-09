package ai.rnt.customerFeedback.util;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import ai.rnt.customerFeedback.exception.CRMException;

@ExtendWith(MockitoExtension.class)
class ConvertDateFormatUtilTest {

	@Test
	void testConvertDate() {
		LocalDateTime inputDate = LocalDateTime.of(2023, 9, 6, 12, 30);
		String formattedDate = ConvertDateFormatUtil.convertDate(inputDate);

	}

	@Test
	void testConvertDateWithNullInput() {
		assertThrows(CRMException.class, () -> ConvertDateFormatUtil.convertDate(null));
	}

	@Test
	void testConvertDateWithInvalidInput() {
		LocalDateTime invalidInput = LocalDateTime.of(2023, 9, 6, 12, 30);
	}

}

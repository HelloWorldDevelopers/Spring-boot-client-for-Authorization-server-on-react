package ai.rnt.customerFeedback.util;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import ai.rnt.customerFeedback.exception.CRMException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ConvertDateFormatUtil {

	private ConvertDateFormatUtil() {

	}

	public static String convertDate(LocalDateTime toConvertDate) {
		try {
			return new SimpleDateFormat("dd/MM/yyyy hh:mm a").format(new SimpleDateFormat("E MMM dd HH:mm:ss z yyyy")
					.parse(Date.from(toConvertDate.atZone(ZoneId.systemDefault()).toInstant()).toString()));
		} catch (Exception e) {
			log.error("Got Excetion while converting date format:", e.getMessage());
			throw new CRMException("error while date conversion");
		}
	}
}

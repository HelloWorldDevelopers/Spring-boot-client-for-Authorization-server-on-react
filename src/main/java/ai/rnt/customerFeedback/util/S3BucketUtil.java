package ai.rnt.customerFeedback.util;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import ai.rnt.customerFeedback.exception.CRMException;

@PropertySource("classpath:application.properties")
@Service
public class S3BucketUtil {

	S3BucketUtil() {
	}

	@Value("${aws.image.folder}")
	String bugImage;

	@Value("${server.type}")
	String serverType;

	@Value("${aws.client.bucketName}")
	String bucketName;

	RestTemplate restTemplate = new RestTemplate();

	public Map<String, Object> getBugImage(String imageName) {
		Map<String, Object> map = new HashMap<>();
		String filePath = bucketName + "/" + bugImage;
		String apiUrl = serverType.equalsIgnoreCase("test")
				? "http://172.20." + "1.38:8080/rnt-vault/api/v1/getFiles/" + imageName
				: "https://corpapps.rabbitandtortoise.com/rnt-vault/api/v1/getFiles/" + imageName;
		String url = apiUrl + "?path=" + filePath;
		try {
			ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
			if (response.getStatusCode().is2xxSuccessful()) {
				String body = response.getBody();
				map.put("SUCCESS", true);
				map.put("MESSAGE", url);
				map.put("DATA", body);
				map.put("DOCUMENTNAME", imageName);
			}
		} catch (Exception e) {
			map.put("SUCCESS", false);
			map.put("MESSAGE", "Exception occur while getting document for project.");
			throw new CRMException(e);
		}
		return map;
	}

}

package ai.rnt.customerFeedback.util;

import static java.util.regex.Pattern.CASE_INSENSITIVE;
import static javax.mail.Part.INLINE;

import java.io.File;
import java.io.IOException;
import java.util.EnumMap;
import java.util.Objects;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import ai.rnt.customerFeedback.entity.CustomerFeedbackMailBox;
import ai.rnt.customerFeedback.entity.CustomerSatisfactionMailBox;
import ai.rnt.customerFeedback.entity.EmployeePerformenceMailBox;
import ai.rnt.customerFeedback.entity.VendorOnboardingMailBox;
import ai.rnt.customerFeedback.enums.ApiResponse;
import ai.rnt.customerFeedback.exception.CRMException;
import ai.rnt.customerFeedback.repository.AddressMasterRepository;
import ai.rnt.customerFeedback.repository.CustomerFeedbackMailBoxRepo;
import ai.rnt.customerFeedback.repository.CustomerFeedbackMasterRepository;
import ai.rnt.customerFeedback.repository.CustomerSatisfactionMailBoxRepo;
import ai.rnt.customerFeedback.repository.CustomerSatisfactionMasterRepository;
import ai.rnt.customerFeedback.repository.EmployeeMasterRepository;
import ai.rnt.customerFeedback.repository.EmployeePerformenceMailBoxRepo;
import ai.rnt.customerFeedback.repository.EmployeePerformenceMasterRepository;
import ai.rnt.customerFeedback.repository.ProjectTeamRepository;
import ai.rnt.customerFeedback.repository.VendorKYCRepository;
import ai.rnt.customerFeedback.repository.VendorOnboardingMailBoxRepo;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class EmailUtil {

	@Autowired
	EmployeeMasterRepository employeeMasterRepository;

	@Autowired
	VendorKYCRepository vendorKYCRepository;

	@Autowired
	CustomerFeedbackMasterRepository customerFeedbackMasterRepository;

	@Autowired
	EmployeePerformenceMasterRepository employeePerformenceMasterRepository;

	@Autowired
	ProjectTeamRepository projectTeamRepository;

	@Autowired
	AddressMasterRepository addressMasterRepository;

	@Autowired
	CustomerFeedbackMailBoxRepo customerFeedbackMailBoxRepo;

	@Autowired
	VendorOnboardingMailBoxRepo vendorOnboardingMailBoxRepo;

	@Autowired
	EmployeePerformenceMailBoxRepo employeePerformenceMailBoxRepo;

	@Value("${mail.url}")
	private String cutomerlinkUrl;

	@Value("${mail.employee.link}")
	private String employeeLink;

	@Value("${vendor.mail.link}")
	private String vendorLinkUrl;

	@Value("${mail.project.link}")
	private String projectMail;

	@Value("${img.regex}")
	private String imgRegex1;

//	 String senderEmail = "rntapps@rabbitandtortoise.com";
	String senderEmail = "uatapps@rabbitandtortoise.com";

	@Autowired
	CustomerSatisfactionMasterRepository customerSatisfactionMasterRepository;

	@Autowired
	CustomerSatisfactionMailBoxRepo customerSatisfactionMailBoxRepo;

	@Autowired
	ResourceLoader loader;

	public ResponseEntity<EnumMap<ApiResponse, Object>> sendEmail(int id) {
		EnumMap<ApiResponse, Object> resultMap = new EnumMap<>(ApiResponse.class);
		CustomerSatisfactionMailBox customerSatisfactionMailBox = customerSatisfactionMailBoxRepo
				.findByCustomerSatisfaction(id);
		String recipientEmail = customerSatisfactionMailBox.getToEmail();
		if (recipientEmail.endsWith("@rnt.ai") || recipientEmail.endsWith("@rabbitandtortoise.com")) {
			resultMap.put(ApiResponse.SUCCESS, false);
			resultMap.put(ApiResponse.MESSAGE, "Email Domain should not be rnt.ai and @rabbitandtortoise.com");
		} else {
			String senderPassword = employeeMasterRepository.getEmailPassword();

			Properties properties = new Properties();
			properties.put("mail.smtp.host", "smtp.zoho.com");
			properties.put("mail.smtp.port", "587");
			properties.put("mail.smtp.auth", "true");
			properties.put("mail.smtp.starttls.enable", "true");

			Session session = Session.getInstance(properties, new Authenticator() {
				@Override
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(senderEmail, senderPassword);
				}
			});

			try {
				session.setDebug(true);

				MimeMessage message = new MimeMessage(session);
				message.setFrom(new InternetAddress(senderEmail));
				message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipientEmail));
				message.setSubject(customerSatisfactionMailBox.getSubject());

				getMessage(customerSatisfactionMailBox.getEmailBody(), message);

				Transport.send(message);
				resultMap.put(ApiResponse.SUCCESS, true);
				resultMap.put(ApiResponse.MESSAGE, "Email sent successfully!");

			} catch (MessagingException e) {
				resultMap.put(ApiResponse.SUCCESS, false);
				resultMap.put(ApiResponse.MESSAGE, "Error occurred while sending email: ");

			}
		}
		return new ResponseEntity<>(resultMap, HttpStatus.OK);
	}

	public ResponseEntity<EnumMap<ApiResponse, Object>> sendEmailVedor(int id) {
		EnumMap<ApiResponse, Object> resultMap = new EnumMap<>(ApiResponse.class);
		VendorOnboardingMailBox vendorOnboardingMailBox = vendorOnboardingMailBoxRepo.findByVendoronboardingId(id);
		String recipientEmail = vendorOnboardingMailBox.getToEmail();
		if (recipientEmail.endsWith("@rnt.ai") || recipientEmail.endsWith("@rabbitandtortoise.com")) {
			resultMap.put(ApiResponse.SUCCESS, false);
			resultMap.put(ApiResponse.MESSAGE, "Email Domain should not be rnt.ai and @rabbitandtortoise.com");
		} else {
			String senderPassword = employeeMasterRepository.getEmailPassword();

			Properties properties = new Properties();
			properties.put("mail.smtp.host", "smtp.zoho.com");
			properties.put("mail.smtp.port", "587");
			properties.put("mail.smtp.auth", "true");
			properties.put("mail.smtp.starttls.enable", "true");

			Session session = Session.getInstance(properties, new Authenticator() {
				@Override
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(senderEmail, senderPassword);
				}
			});

			try {
				session.setDebug(true);
				MimeMessage message = new MimeMessage(session);
				message.setFrom(new InternetAddress(senderEmail));
				message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipientEmail));
				message.setSubject(vendorOnboardingMailBox.getSubject());
				getMessage(vendorOnboardingMailBox.getEmailBody(), message);

				Transport.send(message);
				resultMap.put(ApiResponse.SUCCESS, true);
				resultMap.put(ApiResponse.MESSAGE, "Email sent successfully!");

			} catch (MessagingException e) {
				resultMap.put(ApiResponse.SUCCESS, false);
				resultMap.put(ApiResponse.MESSAGE, "Error occurred while sending email: ");

			}
		}
		return new ResponseEntity<>(resultMap, HttpStatus.OK);
	}

	public ResponseEntity<EnumMap<ApiResponse, Object>> sendEmailEmployeePerformence(int id) {
		EnumMap<ApiResponse, Object> resultMap = new EnumMap<>(ApiResponse.class);

		EmployeePerformenceMailBox employeePerformanceMailBox = employeePerformenceMailBoxRepo
				.findByEmployeePerformance(id);
		String recipientEmail = employeePerformanceMailBox.getToEmail();
		String senderPassword = employeeMasterRepository.getEmailPassword(); // Change this to your password

		Properties properties = new Properties();
		properties.put("mail.smtp.host", "smtp.zoho.com");
		properties.put("mail.smtp.port", "587");
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");

		Session session = Session.getInstance(properties, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(senderEmail, senderPassword);
			}
		});

		try {
			session.setDebug(true);
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(senderEmail));
			message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipientEmail));
			message.setSubject(employeePerformanceMailBox.getSubject());
			getMessage(employeePerformanceMailBox.getEmailBody(), message);

			Transport.send(message);
			resultMap.put(ApiResponse.SUCCESS, true);
			resultMap.put(ApiResponse.MESSAGE, "Email sent successfully!");

		} catch (MessagingException e) {
			resultMap.put(ApiResponse.SUCCESS, false);
			resultMap.put(ApiResponse.MESSAGE, "Error occurred while sending email: ");

		}
		return new ResponseEntity<>(resultMap, HttpStatus.OK);
	}

	public ResponseEntity<EnumMap<ApiResponse, Object>> sendEmailProjectEndFeedback(int id) {
		EnumMap<ApiResponse, Object> resultMap = new EnumMap<>(ApiResponse.class);
		CustomerFeedbackMailBox customerFeedbackMailBox = customerFeedbackMailBoxRepo.findByCustomerFeedback(id);
		String recipientEmail = customerFeedbackMailBox.getToEmail();
		if (recipientEmail.endsWith("@rnt.ai") || recipientEmail.endsWith("@rabbitandtortoise.com")) {
			resultMap.put(ApiResponse.SUCCESS, false);
			resultMap.put(ApiResponse.MESSAGE, "Email Domain should not be rnt.ai and @rabbitandtortoise.com");
		} else {
			String senderPassword = employeeMasterRepository.getEmailPassword(); // Change this to your password

			Properties properties = new Properties();
			properties.put("mail.smtp.host", "smtp.zoho.com");
			properties.put("mail.smtp.port", "587");
			properties.put("mail.smtp.auth", "true");
			properties.put("mail.smtp.starttls.enable", "true");

			Session session = Session.getInstance(properties, new Authenticator() {
				@Override
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(senderEmail, senderPassword);
				}
			});

			try {
				session.setDebug(true);
				MimeMessage message = new MimeMessage(session);
				message.setFrom(new InternetAddress(senderEmail));
				message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipientEmail));
				message.setSubject(customerFeedbackMailBox.getSubject());
				getMessage(customerFeedbackMailBox.getEmailBody(), message);

				Transport.send(message);
				resultMap.put(ApiResponse.SUCCESS, true);
				resultMap.put(ApiResponse.MESSAGE, "Email sent successfully!");

			} catch (MessagingException e) {
				resultMap.put(ApiResponse.SUCCESS, false);
				resultMap.put(ApiResponse.MESSAGE, "Error occurred while sending email: ");

			}
		}
		return new ResponseEntity<>(resultMap, HttpStatus.OK);
	}

	private void getMessage(String emailBody, MimeMessage message) throws MessagingException {
//		String imgRegex = "<img\\s+[^>]*alt\\s*=\\s*['\"]RNT Logo['\"][^>]*>";
		String imgRegex = imgRegex1;
		String replacementImgTag = "<img src=\"cid:rntlogo\" alt=\"RNT Logo\" style=\"height:50; width:50;\">";
		if (Objects.nonNull(imgRegex)) {
			Pattern pattern = Pattern.compile(imgRegex, CASE_INSENSITIVE);
			Matcher matcher = pattern.matcher(emailBody);

			if (matcher.find()) {
				emailBody = matcher.replaceAll(replacementImgTag);
				BodyPart messageBodyPart = new MimeBodyPart();
				messageBodyPart.setContent(emailBody, "text/html");

				MimeMultipart multipart = new MimeMultipart("related");
				multipart.addBodyPart(messageBodyPart);
				MimeBodyPart imagePart = new MimeBodyPart();

				imagePart.setHeader("Content-ID", "<rntlogo>");
				imagePart.setDisposition(INLINE);
				imagePart.setDataHandler(new DataHandler(getFileDataSource()));
				multipart.addBodyPart(imagePart);
				message.setContent(multipart);
			} else
				message.setContent(emailBody, "text/html");
		}
	}

	private FileDataSource getFileDataSource() {
		try {
			return new FileDataSource(new File(loader.getResource("classpath:static/images/rntlogo.png").getURI()));
		} catch (IOException e) {
			log.error("error occureed while sending email ...{}", e.getMessage());
			throw new CRMException(e);
		}
	}

}

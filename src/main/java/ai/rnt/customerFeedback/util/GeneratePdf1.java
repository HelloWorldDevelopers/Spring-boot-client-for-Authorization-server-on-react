package ai.rnt.customerFeedback.util;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Base64;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.itextpdf.html2pdf.HtmlConverter;
import com.itextpdf.io.source.ByteArrayOutputStream;
import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.layout.Canvas;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.property.TextAlignment;

import ai.rnt.customerFeedback.dto.GSTDetailsDto;
import ai.rnt.customerFeedback.dto.VendorCoreGoodServicesDTO;
import ai.rnt.customerFeedback.dto.VendorDocumentAttachmentDto;
import ai.rnt.customerFeedback.dto.VendorKYCDto;
import ai.rnt.customerFeedback.dto.VendorMajorCustomerDTO;
import ai.rnt.customerFeedback.dto.VendorServiceProviderDTO;
import ai.rnt.customerFeedback.exception.CRMException;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class GeneratePdf1 {

	@Value("${pdf.ip}")
	private String pdfUrl;

	@Autowired
	TokanEncrypterDecrypter tokanEncrypterDecrypter;

	public String getImage() throws IOException {
		Resource resource = new ClassPathResource("static/images/rntlogo.svg");

		if (!resource.exists()) {
			return "Image not found";
		}

		byte[] imageBytes = Files.readAllBytes(resource.getFile().toPath());
		return new String(imageBytes);

	}

	public String getCurrency() throws IOException {
		Resource crrencyImage = new ClassPathResource("static/images/dram.svg");
		byte[] currencyBytes = Files.readAllBytes(crrencyImage.getFile().toPath());
		String base64Image = Base64.getEncoder().encodeToString(currencyBytes);
		return new String(base64Image);
	}

	public String getRiyalCurrency() throws IOException {
		Resource crrencRiyalyImage = new ClassPathResource("static/images/rial 1.svg");
		byte[] currencyRiyalBytes = Files.readAllBytes(crrencRiyalyImage.getFile().toPath());
		String riyalbase64Image = Base64.getEncoder().encodeToString(currencyRiyalBytes);
		return new String(riyalbase64Image);
	}

	public ResponseEntity<ByteArrayResource> generatePdfMethod(VendorKYCDto vendorKYCMainDto) throws IOException {

		log.info("Enter into generatePdf method...{} ");

		try {
			ByteArrayOutputStream pdfOutputStream = new ByteArrayOutputStream();

			HtmlConverter.convertToPdf(new ByteArrayInputStream(genrateHtml(vendorKYCMainDto).getBytes()),
					pdfOutputStream);

			PdfDocument pdf = new PdfDocument(new PdfReader(new ByteArrayInputStream(pdfOutputStream.toByteArray())),
					new PdfWriter(pdfOutputStream));
			Document document = new Document(pdf);
			PdfPage lasPage = pdf.getLastPage();

			try (Canvas canvasForFooter = new Canvas(new PdfCanvas(lasPage), pdf, lasPage.getPageSize())) {
				Paragraph footerParagraph = new Paragraph("+91 2027012345").setFontSize(10)
						.setFontColor(new DeviceRgb(194, 36, 87));
				Rectangle pageSize = lasPage.getPageSize();
				float x = pageSize.getLeft() + 30;
				float y = pageSize.getBottom() + 40;
				canvasForFooter.showTextAligned(footerParagraph, x, y, TextAlignment.LEFT);
				Paragraph footerForWeb = new Paragraph("www.rnt.ai").setFontSize(11)
						.setFontColor(new DeviceRgb(194, 36, 87));
				x = pageSize.getLeft() + 250;
				y = pageSize.getBottom() + 40;
				canvasForFooter.showTextAligned(footerForWeb, x, y, TextAlignment.LEFT);
				Paragraph footerForEmail = new Paragraph("info@rnt.ai").setFontSize(11)
						.setFontColor(new DeviceRgb(194, 36, 87));
				x = pageSize.getLeft() + 480;
				y = pageSize.getBottom() + 40;
				canvasForFooter.showTextAligned(footerForEmail, x, y, TextAlignment.LEFT);
			}
			document.close();
			byte[] pdfBytes = pdfOutputStream.toByteArray();
			String fileName = vendorKYCMainDto.getLeagalName() + ".pdf";

			ByteArrayResource resource = new ByteArrayResource(pdfBytes);
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_PDF);
			headers.setContentDispositionFormData("attachment", fileName);

			return new ResponseEntity<>(resource, headers, HttpStatus.OK);
		} catch (IOException e) {
			log.error("Error white genrating pdf....." + e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

		}

	}

	String contentstring = "© Copyright LocalDateTime.now().getYear() Rabbit and Tortoise Technology Solutions.";

	private String genrateHtml(VendorKYCDto vendorKYCMainDto) {
		try {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
			String svgContent = getImage();

			String isUnderTaxExemptions = vendorKYCMainDto.getIsUnderTaxExemptions();
			String isUnderGst = vendorKYCMainDto.getIsUnderGst();
			String companyIncorporationDocument = null;

			String cancelledChequeBankDocument = null;

			String panCard = null;

			String gSTCertificate = null;

			String last3MonthBankstmt = null;

			String underTaxExemptionsDoc = null;

			String msmeCertificate = null;

			String iTRFiled = null;
			List<VendorDocumentAttachmentDto> vendorDocumentAttachmentList = vendorKYCMainDto
					.getVendorDocumentAttachmentList();
			for (VendorDocumentAttachmentDto doc : vendorDocumentAttachmentList) {
				Integer documentId = doc.getVendorDocumentMaster().getDocumentId();
				if (documentId == 1) {
					companyIncorporationDocument = doc.getDocumentAttachment();
				}
				if (documentId == 2) {
					cancelledChequeBankDocument = doc.getDocumentAttachment();
				}
				if (documentId == 3) {
					panCard = doc.getDocumentAttachment();
				}
				if (documentId == 4) {
					gSTCertificate = doc.getDocumentAttachment();
				}
				if (documentId == 5) {
					last3MonthBankstmt = doc.getDocumentAttachment();
				}
				if (documentId == 7) {
					underTaxExemptionsDoc = doc.getDocumentAttachment();
				}
				if (documentId == 8) {
					msmeCertificate = doc.getDocumentAttachment();
				}
				if (documentId == 9) {
					iTRFiled = doc.getDocumentAttachment();
				}

			}
			String encodeToString = null;
			try {
				Integer vendorKycId = vendorKYCMainDto.getVendorKycId();
				encodeToString = Base64.getEncoder().encodeToString(vendorKycId.toString().getBytes());
			} catch (Exception e) {
				throw new CRMException(e);
			}

			StringBuilder htmlBuilder = new StringBuilder();
			htmlBuilder.append("<!DOCTYPE html>\n");
			htmlBuilder.append("<html lang=\"en\">\n");
			htmlBuilder.append(
					"<meta http-equiv=Content-Type content=\"text/html;charset=utf-8\"> <meta content=\"IE=edge,chrome=1\" http-equiv=X-UA-Compatible> <meta name=viewport content=\"width=device-width, initial-scale=1\">\n");
			htmlBuilder.append("<style>\n");
			htmlBuilder.append("\n    label {\n");
			htmlBuilder.append("        display: block;\n");
			htmlBuilder.append("        color: #9c9999;\n");
			htmlBuilder.append("        margin: 7px 0;\n");
			htmlBuilder.append("        /* Change color of labels */\n");
			htmlBuilder.append("    }\n");
			htmlBuilder.append("@page {\n");
			htmlBuilder.append("    margin-bottom: 50px;\n");
			htmlBuilder.append("    margin-top: 50px;\n");
			htmlBuilder.append("    @top-left {\n");
			htmlBuilder.append("        content: element(header);\n");
			htmlBuilder.append("    }\n");

			htmlBuilder.append("    @bottom-center {\n");
			htmlBuilder.append("        content: element(footer);\n");
			htmlBuilder.append("    }\n");
			htmlBuilder.append("}\n");
			htmlBuilder.append("    body {\n");
			htmlBuilder.append("        position: relative;\n");
			htmlBuilder.append("        padding-bottom: 50px;\n");
			htmlBuilder.append("    }\n");
			htmlBuilder.append("    .footer {\n");
			htmlBuilder.append("        position: fixed;\n");
			htmlBuilder.append("        bottom: 0;\n");
			htmlBuilder.append("        width: 100%;\n");
			htmlBuilder.append("        text-align: center;\n");
			htmlBuilder.append("        padding: 5px 0;\n");
			htmlBuilder.append("        font-size: 11px;\n");
			htmlBuilder.append("        color: gray;\n");
			htmlBuilder.append("    }\n");
			htmlBuilder.append("    .footer-line {\n");
			htmlBuilder.append("        border: 0;\n");
			htmlBuilder.append("        height: 1px;\n");
			htmlBuilder.append("        background: #D3D3D3;\n");
			htmlBuilder.append("        margin: 0 auto 10px;\n");
			htmlBuilder.append("        width: 90%;\n");
			htmlBuilder.append("    }\n");
			htmlBuilder.append("        div.header img {\n");
			htmlBuilder.append("            height: 50%;\n");
			htmlBuilder.append("            margin-right: 40px;\n");
			htmlBuilder.append("            padding-top: 20px;\n");
			htmlBuilder.append("        }\n");
			htmlBuilder.append("</style>\n");
			htmlBuilder.append("\n<head>\n");
			htmlBuilder.append("    <meta charset=\"UTF-8\">\n");
			htmlBuilder.append("    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n");
			htmlBuilder.append(
					"<link href=\"https://fonts.googleapis.com/css2?family=Poppins:wght@400;700&display=swap\" rel=\"stylesheet\">\n");
			htmlBuilder.append(
					"<link rel=\"stylesheet\" href=\"https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css\">\n");
			htmlBuilder.append("    <title>Document</title>\n");
			htmlBuilder.append("</head>\n");
			htmlBuilder.append("\n<body style=\"font-family: 'Poppins', sans-serif;\">\n");
			htmlBuilder.append("    <div style=\"position: running(header);\" class=\"header\">\n");
			htmlBuilder.append("        <div>" + svgContent + "</div>\n");
			htmlBuilder.append("    </div>\n");
			htmlBuilder.append("    <div style=\"text-align: center;color: #C22457;\">\n");
			htmlBuilder.append(" <h3>Vendor Onboarding Form</h3></div>\n");

			htmlBuilder.append("    <div style=\"position: running(footer)\" class=\"footer\">\n");
			htmlBuilder.append("        <hr class=\"footer-line\">\n");
			htmlBuilder.append("    <span class='footer-content'>© Copyright " + LocalDateTime.now().getYear()
					+ " Rabbit and Tortoise Technology Solutions.</span>\n");
			htmlBuilder.append("    </div>\n");

			htmlBuilder.append("    <div class=\"form-table\">\n");
			htmlBuilder.append("<div style=\"width: 100%;margin: 0 auto;\">\n");
			htmlBuilder.append(
					"    <div style=\"border-left: 3px solid #C22457; padding-left: 10px; margin: 20px 0; display: flex; justify-content: space-between;gap:20px;flex-direction: column; align-items: center;\">\n");
			htmlBuilder.append(
					"        <span style=\"font-size: 14px; color: #C22457; font-weight: 500;\">Supplier Details</span>\n");
			htmlBuilder.append("    </div>\n");

			htmlBuilder.append(
					"    <table style=\"width: 100%;border-collapse: collapse; border: 1px solid #D3D3D3;\">\n");
			htmlBuilder.append("        <tr>\n");
			htmlBuilder
					.append("            <td style=\"border: 1px solid #D3D3D3; vertical-align: top;padding:5px;\">\n");
			htmlBuilder.append(
					"                <p style=\"color: #3C3535;font-size: 13px;font-weight: 500;margin: 0;\">Legal Name</p>\n");
			htmlBuilder.append(
					"                <p style=\"word-wrap: break-word;color: #696969;font-size: 11px;font-weight: 400;margin: 0;\">"
							+ vendorKYCMainDto.getLeagalName() + "</p>\n");
			htmlBuilder.append("            </td>\n");
			htmlBuilder
					.append("            <td style=\"border: 1px solid #D3D3D3; vertical-align: top;padding:5px;\">\n");
			htmlBuilder.append(
					"                <p style=\"color: #3C3535;font-weight: 500;font-size: 13px;margin: 0;\">Trade Name</p>\n");
			htmlBuilder.append(
					"                <p style=\"word-wrap: break-word;color: #696969;font-size: 11px;font-weight: 400;margin: 0;\">"
							+ vendorKYCMainDto.getTradeName() + "</p>\n");
			htmlBuilder.append("            </td>\n");
			htmlBuilder
					.append("            <td style=\"border: 1px solid #D3D3D3;vertical-align: top;padding:5px;\">\n");
			htmlBuilder.append(
					"                <p style=\"color: #3C3535;font-weight: 500;font-size: 13px;margin: 0;\">Company Type</p>\n");
			htmlBuilder.append(
					"                <p style=\"word-wrap: break-word;color: #696969;font-size: 11px;font-weight: 400;margin: 0;\">"
							+ vendorKYCMainDto.getCompanyType() + "</p>\n");
			htmlBuilder.append("            </td>\n");
			htmlBuilder
					.append("            <td style=\"border: 1px solid #D3D3D3;vertical-align: top;padding:5px;\">\n");
			htmlBuilder.append(
					"                <p style=\"color: #3C3535;font-weight: 500;font-size: 13px;margin: 0;\">Telephone No.</p>\n");
			htmlBuilder.append(
					"                <p style=\"word-wrap: break-word;color: #696969;font-size: 11px;font-weight: 400;margin: 0;\">"
							+ vendorKYCMainDto.getTelephoneNo() + "</p>\n");
			htmlBuilder.append("            </td>\n");
			htmlBuilder.append("        </tr>\n");
			htmlBuilder.append(
					"            <td style=\"border: 1px solid #D3D3D3; vertical-align: top; padding:5px;\" colspan=\"2\" rowspan=\"2\">\n");
			htmlBuilder.append(
					"                <p style=\"color: #3C3535;font-weight: 500;font-size: 13px;margin:0\">Billing Address </p>\n");
			htmlBuilder.append(
					"                <p style=\"word-wrap: break-word;color: #696969;font-size: 11px;margin:0\">");
			String billingAddressLine1 = vendorKYCMainDto.getBillingAddressLine1().replace("⋅", "&middot;");
			htmlBuilder.append(billingAddressLine1 + " ");
			if (Objects.nonNull(vendorKYCMainDto.getBillingAddressLine2())) {
				String billingAddressLine2 = vendorKYCMainDto.getBillingAddressLine2().replace("⋅", "&middot;");
				htmlBuilder.append(billingAddressLine2);
			}
			htmlBuilder.append("            </p>\n");
			htmlBuilder.append("            </td>\n");
			htmlBuilder
					.append("            <td style=\"border: 1px solid #D3D3D3;vertical-align: top;padding:5px; \">\n");
			htmlBuilder.append(
					"                <p style=\"color: #3C3535;font-weight: 500;font-size: 13px;margin: 0;\">City</p>\n");
			htmlBuilder.append(
					"                <p style=\"width:10em;word-wrap: break-word;color: #696969;font-size: 11px;margin: 0;\">"
							+ vendorKYCMainDto.getBillingCity() + "</p>\n");
			htmlBuilder.append("            </td>\n");
			htmlBuilder
					.append("            <td style=\"border: 1px solid #D3D3D3;vertical-align: top;padding:5px; \">\n");
			htmlBuilder.append(
					"                <p style=\"color: #3C3535;font-weight: 500;font-size: 13px;margin: 0;\">State</p>\n");
			htmlBuilder.append(
					"                <p style=\"width:10em;word-wrap: break-word;color: #696969;font-size: 11px;margin: 0;\">"
							+ vendorKYCMainDto.getBillingState() + "</p>\n");
			htmlBuilder.append("            </td>\n");

			htmlBuilder.append("        </tr>\n");
			htmlBuilder.append("        <tr>\n");
			htmlBuilder.append(
					"            <td style=\"border: 1px solid #D3D3D3;vertical-align: top;padding:5px; \" >\n");
			htmlBuilder.append(
					"                <p style=\"color: #3C3535;font-weight: 500;font-size: 13px;margin: 0\">Country</p>\n");
			htmlBuilder.append(
					"                <p style=\"width:10em;word-wrap: break-word;word-wrap: break-word;color: #696969;font-size: 11px;margin: 0;\">"
							+ vendorKYCMainDto.getBillingcountryMaster().getCountry() + "</p>\n");
			htmlBuilder.append("            </td>\n");
			htmlBuilder
					.append("            <td style=\"border: 1px solid #D3D3D3;vertical-align: top; padding:5px;\">\n");
			htmlBuilder.append(
					"                <p style=\"color: #3C3535;font-weight: 500;font-size: 13px;margin: 0;\">ZIP code</p>\n");
			htmlBuilder.append(
					"                <p style=\"width:10em;word-wrap: break-word;word-wrap: break-word;color: #696969;font-size: 11px;margin: 0;\">"
							+ vendorKYCMainDto.getBillingZipCode() + "</p>\n");
			htmlBuilder.append("       </td>\n");
			htmlBuilder.append("       </tr>\n");
			htmlBuilder.append("       <tr>\n");
			htmlBuilder.append(
					"      <td style=\"border: 1px solid #D3D3D3; padding:5px; vertical-align: top;\" colspan=\"2\" rowspan=\"2\">\n");
			htmlBuilder.append(
					"                <p style=\"color: #3C3535;font-weight: 500;font-size: 13px;margin:0\">Shipping Address </p>\n");
			htmlBuilder.append(
					"                <p style=\"word-wrap: break-word;color: #696969;font-size: 11px;margin:0\">");
			String shippingAddressLine1 = vendorKYCMainDto.getShippingAddressLine1().replace("⋅", "&middot;");
			htmlBuilder.append(shippingAddressLine1 + " ");
			if (Objects.nonNull(vendorKYCMainDto.getShippingAddressLine2())) {
				String shippingAddressLine2 = vendorKYCMainDto.getShippingAddressLine2().replace("⋅", "&middot;");
				htmlBuilder.append(shippingAddressLine2);
			}
			htmlBuilder.append("</p>\n");
			htmlBuilder.append(" </td>\n");

			htmlBuilder
					.append("            <td style=\"border: 1px solid #D3D3D3;vertical-align: top;padding:5px; \">\n");
			htmlBuilder.append(
					"                <p style=\"color: #3C3535;font-weight: 500;font-size: 13px;margin: 0\">City</p>\n");
			htmlBuilder.append(
					"                <p style=\"width:10em;word-wrap: break-word;color: #696969;font-size: 11px;margin: 0\">"
							+ vendorKYCMainDto.getShippingCity() + "</p>\n");
			htmlBuilder.append("            </td>\n");
			htmlBuilder
					.append("            <td style=\"border: 1px solid #D3D3D3;vertical-align: top; padding:5px;\">\n");
			htmlBuilder.append(
					"                <p style=\"color: #3C3535;font-weight: 500;font-size: 13px;margin: 0;\">State</p>\n");
			htmlBuilder.append(
					"                <p style=\"width:10em;word-wrap: break-word;color: #696969;font-size: 11px;margin: 0;\">"
							+ vendorKYCMainDto.getShippingState() + "</p>\n");
			htmlBuilder.append("            </td>\n");

			htmlBuilder.append("        </tr>\n");
			htmlBuilder.append("        <tr>\n");
			htmlBuilder
					.append("            <td style=\"border: 1px solid #D3D3D3;vertical-align: top;padding:5px;\" >\n");
			htmlBuilder.append(
					"                <p style=\"color: #3C3535;font-weight: 500;font-size: 13px;margin: 0;\">Country</p>\n");
			htmlBuilder.append(
					"                <p style=\"width:10em;word-wrap: break-word;word-wrap: break-word;color: #696969;font-size: 11px;margin: 0;\">"
							+ vendorKYCMainDto.getShippingcountryMaster().getCountry() + "</p>\n");
			htmlBuilder.append("            </td>\n");
			htmlBuilder
					.append("            <td style=\"border: 1px solid #D3D3D3;vertical-align: top;padding:5px; \">\n");
			htmlBuilder.append(
					"                <p style=\"color: #3C3535;font-weight: 500;font-size: 13px;margin: 0;\">ZIP code</p>\n");
			htmlBuilder.append(
					"                <p style=\"width:10em;word-wrap: break-word;word-wrap: break-word;color: #696969;font-size: 11px;margin: 0;\">"
							+ vendorKYCMainDto.getShippingZipCode() + "</p>\n");
			htmlBuilder.append("            </td>\n");
			htmlBuilder.append("        </tr>\n");
			htmlBuilder.append("    </table>\n");

			htmlBuilder.append("<br>\n");
			htmlBuilder
					.append("    <table style=\"width: 100%;border-collapse: collapse; border: 1px solid #D3D3D3;\">");
			htmlBuilder.append("        <tr style=\"border: 1px solid #D3D3D3; \">");
			htmlBuilder
					.append("            <td style=\"border: 1px solid #D3D3D3;padding:5px;vertical-align: top; \">");
			htmlBuilder.append(
					"                <p style=\"color: #3C3535;font-weight: 100;font-size: 13px;margin: 0;\">Do you come under MSME?</p>");
			htmlBuilder.append("                <p style=\"color: #696969;font-size: 11px;margin: 0;\">"
					+ vendorKYCMainDto.getIsUnderMsme()

					+ "</p>");
			htmlBuilder.append("            </td>");
			/////
			htmlBuilder.append("            <td style=\"border: 1px solid #D3D3D3;padding:5px; \">");
			htmlBuilder.append(
					"                <p style=\"color: #3C3535;font-weight: 100;font-size: 13px;margin: 0;\">Are you registered under GST?</p>");
			htmlBuilder.append(
					"                <p style=\"color: #696969;font-size: 11px;margin: 0;\">" + isUnderGst + "</p>");
			htmlBuilder.append("            </td>");
			htmlBuilder.append("            <td style=\"border: 1px solid #D3D3D3;padding:5px; \">");
			htmlBuilder.append(
					"                <p style=\"color: #3C3535;font-weight: 100;font-size: 13px;margin: 0;\">Do you come under Tax Exemptions?</p>");
			htmlBuilder.append("                <p style=\"color: #696969;font-size: 11px;margin: 0;\">"
					+ isUnderTaxExemptions + "</p>");
			htmlBuilder.append("            </td>");
			htmlBuilder.append("        </tr>");

			htmlBuilder.append("    </table>");
//			htmlBuilder.append("\n");

			//
			if (vendorKYCMainDto.getIsUnderGst().equalsIgnoreCase("Yes")) {
				if (!vendorKYCMainDto.getGstdetailsList().isEmpty()) {
					List<GSTDetailsDto> gstdetailsList = vendorKYCMainDto.getGstdetailsList();

					htmlBuilder.append(
							"    <div style=\"border-left: 3px solid #C22457;padding-left: 10px;margin: 20px 0;\">\n");
					htmlBuilder.append(
							"        <span style=\"font-size: 14px;color: #C22457;font-weight: 500;\">Tax Details</span>\n");
					htmlBuilder.append("    </div>\n");
					for (GSTDetailsDto gst : gstdetailsList) {
						htmlBuilder.append(
								"    <table style=\"width: 100%;border-collapse: collapse; border: 1px solid #D3D3D3;\">\n");
						htmlBuilder.append("        <tr>\n");
						htmlBuilder.append(
								"            <td style=\"border: 1px solid #D3D3D3;vertical-align: top;padding: 5px;\">\n");
						htmlBuilder.append(
								"                <p style=\"color: #3C3535;font-weight: 500;font-size: 13px;margin:0\">Code</p>\n");
						htmlBuilder.append(
								"                <p style=\"width:10em;word-wrap: break-word;color: #696969;font-size: 11px;font-weight: 500;margin:0\">"
										+ gst.getVendorStateGSTCode().getStateGstCode() + "</p>\n");
						htmlBuilder.append("            </td>\n");
						htmlBuilder.append(
								"            <td style=\"border: 1px solid #D3D3D3; padding: 5px;vertical-align: top; \">\n");
						htmlBuilder.append(
								"                <p style=\"color: #3C3535;font-weight: 500;font-size: 13px;margin:0\">State</p>\n");
						htmlBuilder.append(
								"                <p style=\"width:10em;word-wrap: break-word;color: #696969;font-size: 11px;font-weight: 400;margin:0\">"
										+ gst.getVendorStateGSTCode().getState() + "</p>\n");
						htmlBuilder.append("            </td>\n");
						htmlBuilder.append("\n");
						htmlBuilder.append("            </td>\n");
						htmlBuilder.append(
								"            <td style=\"border: 1px solid #D3D3D3; padding: 5px;vertical-align: top; \">\n");
						htmlBuilder.append(
								"                <p style=\"color: #3C3535;font-weight: 500;font-size: 13px;margin:0\">GST No</p>\n");
						htmlBuilder.append(
								"                <p style=\"width:10em;word-wrap: break-word;color: #696969;font-size: 11px;font-weight: 400;margin:0\">"
										+ gst.getGstNo() + "</p>\n");
						htmlBuilder.append("            </td>\n");
						htmlBuilder.append(
								"            <td style=\"border: 1px solid #D3D3D3; padding: 5px;vertical-align: top;margin:0 \">\n");
						htmlBuilder.append(
								"                <p style=\"color: #3C3535;font-weight: 500;font-size: 13px;margin:0\">Pan No</p>\n");
						htmlBuilder.append(
								"                <p style=\"width:10em;word-wrap: break-word;color: #696969;font-size: 11px;font-weight: 400;margin:0\">"
										+ gst.getPanNo() + "</p>\n");
						htmlBuilder.append("            </td>\n");
						htmlBuilder.append("        </tr>\n");
						htmlBuilder.append("        <tr>\n");
						htmlBuilder
								.append("            <td style=\"padding: 5px;\" colspan=\"4\";vertical-align: top>\n");
						htmlBuilder.append(
								"                <p style=\"color: #3C3535;font-weight: 500;font-size: 13px;margin:0\">Address</p>\n");
						htmlBuilder.append(
								"                <p style=\"word-wrap: break-word;color: #696969;font-size: 11px;font-weight: 400;margin:0\">\n");
						String gstAddress = gst.getGstAddress();
						String gstAddress1 = gstAddress.replace("⋅", "&middot;");
						htmlBuilder.append("                    " + (gstAddress1.replace("\n", "<br>")) + "\n");
						htmlBuilder.append("                </p>\n");
						htmlBuilder.append("            </td>\n");
						htmlBuilder.append("        </tr>\n");
//						htmlBuilder.append("\n");
						htmlBuilder.append("    </table>\n");
						htmlBuilder.append("<br>");
					}
//					htmlBuilder.append("\n");
					htmlBuilder.append(
							"    <div style=\"border-left: 3px solid #C22457;padding-left: 10px;margin: 20px 0;\">\n");
					htmlBuilder.append(
							"        <span style=\"font-size: 14px;color: #C22457;font-weight: 400;line-height: 2px;\">Details of Service Provided</span>\n");
					htmlBuilder.append("    </div>\n");

					if (!vendorKYCMainDto.getVendorServiceProviderList().isEmpty()) {
						List<VendorServiceProviderDTO> vendorServiceProviderList = vendorKYCMainDto
								.getVendorServiceProviderList();
						for (VendorServiceProviderDTO service : vendorServiceProviderList) {
							String description = "";
							if (Objects.nonNull(service.getDescOfServices())) {
								description = service.getDescOfServices();
								description = description.replace("\\n", "\n");
								description = description.replace("\n", "<br>");
							}
							htmlBuilder.append(
									"    <table style=\"width: 100%;border-collapse: collapse; border: 1px solid #D3D3D3;\">\n");
							htmlBuilder.append("        <tr>\n");
							htmlBuilder.append(
									"            <td style=\"border: 1px solid #D3D3D3;padding: 5px;vertical-align: top\">\n");
							htmlBuilder.append(
									"                <p style=\"color: #3C3535;font-weight: 500;font-size: 13px;margin:0\">Service Accounting Code</p>\n");
							htmlBuilder.append(
									"                <p style=\"width:10em;word-wrap: break-word;color: #696969;font-size: 11px;margin:0\">"
											+ service.getServiceAccgCode() + "</p>\n");
							htmlBuilder.append("            </td>\n");
							htmlBuilder.append(
									"            <td style=\"border: 1px solid #D3D3D3; padding: 5px;vertical-align: top \">\n");
							htmlBuilder.append(
									"                <p style=\"color: #3C3535;font-weight: 500;font-size: 13px;margin:0\">GST Rate Expected</p>\n");
							htmlBuilder.append(
									"                <p style=\"width:10em;word-wrap: break-word;color: #696969;font-size: 11px;margin:0\">"
											+ service.getGstRateExpected() + "</p>\n");
							htmlBuilder.append("            </td>\n");
							htmlBuilder.append("\n");
							htmlBuilder.append("        </tr>\n");
							htmlBuilder.append("        <tr>\n");
							htmlBuilder.append(
									"            <td style=\"padding: 5px;\" colspan=\"2\";vertical-align: top>\n");
							htmlBuilder.append(
									"                <p style=\"color: #3C3535;font-weight: 500;font-size: 13px;margin:0\">Description of Services</p>\n");
							htmlBuilder.append(
									"                <p style=\"word-wrap: break-word;color: #696969;font-size: 11px;margin:0\">\n");
							htmlBuilder.append("                    " + description + "\n");
							htmlBuilder.append("                </p>\n");
							htmlBuilder.append("            </td>\n");
							htmlBuilder.append("        </tr>\n");
							htmlBuilder.append("        <tr>\n");
							htmlBuilder.append(
									"            <td style=\"padding: 5px;border: 1px solid #D3D3D3;\" colspan=\"2\";vertical-align: top>\n");
							htmlBuilder.append(
									"                <p style=\"color: #3C3535;font-weight: 500;font-size: 13px;margin:0\">Whether opted for composition scheme under GST?</p>\n");
							htmlBuilder.append(
									"                <p style=\"width:40em;word-wrap: break-word;color: #696969;font-size: 11px;margin:0\">\n");
							htmlBuilder.append("                    " + (service.getIsCompSchemeUnderGst()) + "\n");
							htmlBuilder.append("                </p>\n");
							htmlBuilder.append("            </td>\n");
							htmlBuilder.append("        </tr>\n");

							htmlBuilder.append("\n");
							htmlBuilder.append("    </table>\n");
							htmlBuilder.append("<br>");
						}
					}
				}

				htmlBuilder.append("\n");
			}
			htmlBuilder
					.append("    <div style=\"border-left: 3px solid #C22457;padding-left: 10px;margin: 20px 0;\">\n");
			htmlBuilder.append(
					"        <span style=\"font-size: 14px;color: #C22457;font-weight: 400;\">SPOC Details</span>\n");
			htmlBuilder.append("    </div>\n");
			htmlBuilder.append(
					"    <table style=\"width: 100%;border-collapse: collapse; border: 1px solid #D3D3D3;\">\n");
			htmlBuilder.append("        <tr>\n");
			htmlBuilder.append(
					"            <th style=\"border: 1px solid #D3D3D3;padding: 5px;vertical-align: top;color: #3C3535;font-weight: 500;font-size: 13px;margin:0;text-align: center;max-width: 10em;word-wrap: break-word;\">\n");
			htmlBuilder.append("Department");
			htmlBuilder.append("            </th>\n");
			htmlBuilder.append(
					"            <th style=\"border: 1px solid #D3D3D3;padding: 5px;vertical-align: top;color: #3C3535;font-weight: 500;font-size: 13px;margin:0;text-align: center;max-width: 10em;word-wrap: break-word;\">\n");
			htmlBuilder.append("Contact Name");
			htmlBuilder.append("            </th>\n");
			htmlBuilder.append(
					"            <th style=\"border: 1px solid #D3D3D3;padding: 5px;vertical-align: top;color: #3C3535;font-weight: 500;font-size: 13px;margin:0;text-align: center;max-width: 10em;word-wrap: break-word;\">\n");
			htmlBuilder.append("Email ID");
			htmlBuilder.append("            </th>\n");
			htmlBuilder.append(
					"            <th style=\"border: 1px solid #D3D3D3;padding: 5px;vertical-align: top;color: #3C3535;font-weight: 500;font-size: 13px;margin:0;text-align: center;max-width: 10em;word-wrap: break-word;\">\n");
			htmlBuilder.append("Mobile Number");
			htmlBuilder.append("            </th>\n");
			htmlBuilder.append(
					"            <th style=\"border: 1px solid #D3D3D3;padding: 5pxvertical-align: top;color: #3C3535;font-weight: 500;font-size: 13px;margin:0;text-align: center;max-width: 10em;word-wrap: break-word;\">\n");
			htmlBuilder.append("Telephone No");
			htmlBuilder.append("            </th>\n");
			htmlBuilder.append("        </tr>\n");
			htmlBuilder.append("        <tr>\n");
			htmlBuilder.append(
					"            <td style=\"border: 1px solid #D3D3D3;padding: 5px;vertical-align: topwidth:5em;color: #C22457;font-weight: 500;font-size: 13px;margin:0;\">\n");
			htmlBuilder.append("Accounts");
			htmlBuilder.append("            </td>\n");
			htmlBuilder.append(
					"            <td style=\"border: 1px solid #D3D3D3;padding: 5px;vertical-align: top;width:9em;word-wrap: break-word;color: #696969;font-size: 11px;margin:0\">\n");
			htmlBuilder.append("                    " + vendorKYCMainDto.getAccountsName() + "\n");
			htmlBuilder.append("            </td>\n");
			htmlBuilder.append(
					"            <td style=\"border: 1px solid #D3D3D3;padding: 5px;vertical-align: top;width: 7em;word-wrap: break-word;color: #696969;font-size: 11px;\">\n");
			htmlBuilder.append("                    " + vendorKYCMainDto.getAccountsEmail() + "\n");
			htmlBuilder.append("            </td>\n");
			htmlBuilder.append(
					"            <td style=\"border: 1px solid #D3D3D3;padding: 5px;vertical-align: top;width:7em;word-wrap: break-word;color: #696969;font-size: 11px;margin:0\">\n");
			htmlBuilder.append("                    " + vendorKYCMainDto.getAccountsMobileNo() + "\n");
			htmlBuilder.append("            </td>\n");
			htmlBuilder.append(
					"            <td style=\"border: 1px solid #D3D3D3;padding: 5px;vertical-align: top;width:7em;word-wrap: break-word;color: #696969;font-size: 11px;margin:0\">\n");
			htmlBuilder.append("                    "
					+ ((vendorKYCMainDto.getAccountsTelNo() != null && !vendorKYCMainDto.getAccountsTelNo().isEmpty())
							? vendorKYCMainDto.getAccountsTelNo()
							: "N/A")
					+ "\n");
			htmlBuilder.append("</td>\n");
			htmlBuilder.append("        </tr>\n");
			htmlBuilder.append("        <tr>\n");
			htmlBuilder.append(
					"            <td style=\"border: 1px solid #D3D3D3;padding: 5px;vertical-align: top;width:5em;color: #C22457;font-weight: 500;font-size: 13px;margin:0\">\n");
			htmlBuilder.append("Sales</td>");
			htmlBuilder.append(
					"            <td style=\"border: 1px solid #D3D3D3;padding: 5px;vertical-align: top;width:9em;word-wrap: break-word;color: #696969;font-size: 11px;margin:0\">\n");
			htmlBuilder.append("                    " + vendorKYCMainDto.getSalesName() + "\n");
			htmlBuilder.append("            </td>\n");
			htmlBuilder.append(
					"            <td style=\"border: 1px solid #D3D3D3;padding: 5px;vertical-align: top;width: 7rem;word-wrap: break-word;color: #696969;font-size: 11px;margin:0\">\n");
			htmlBuilder.append("                    " + vendorKYCMainDto.getSalesEmail() + "\n");
			htmlBuilder.append("            </td>\n");
			htmlBuilder.append(
					"            <td style=\"border: 1px solid #D3D3D3;padding: 5px;vertical-align: top;width:7em;word-wrap: break-word;color: #696969;font-size: 11px;margin:0\">\n");
			htmlBuilder.append("                    " + vendorKYCMainDto.getSalesMobileNo() + "\n");
			htmlBuilder.append("            </td>\n");
			htmlBuilder.append(
					"            <td style=\"border: 1px solid #D3D3D3;padding: 5px;vertical-align: top;width:7em;word-wrap: break-word;color: #696969;font-size: 11px;margin:0\">\n");
			htmlBuilder.append("                    "
					+ ((vendorKYCMainDto.getSalesTelNo() != null && !vendorKYCMainDto.getSalesTelNo().isEmpty())
							? vendorKYCMainDto.getSalesTelNo()
							: "N/A")
					+ "\n");
			htmlBuilder.append("            </td>\n");
			htmlBuilder.append("        </tr>\n");
			htmlBuilder.append("        <tr>\n");
			htmlBuilder.append(
					"            <td style=\"border: 1px solid #D3D3D3;padding: 5px;vertical-align: top;width:5em;color: #C22457;font-weight: 500;font-size: 13px;margin:0\">\n");
			htmlBuilder.append("Escalation");
			htmlBuilder.append("            </td>\n");
			htmlBuilder.append(
					"            <td style=\"border: 1px solid #D3D3D3;padding: 5px;vertical-align: top;width:9em;word-wrap: break-word;color: #696969;font-size: 11px;margin:0\">\n");
			htmlBuilder.append("                    " + vendorKYCMainDto.getEscalationName() + "\n");
			htmlBuilder.append("            </td>\n");
			htmlBuilder.append(
					"            <td style=\"border: 1px solid #D3D3D3;padding: 5px;vertical-align: top;width: 7rem;word-wrap: break-word;color: #696969;font-size: 11px;margin:0\">\n");
			htmlBuilder.append("                    " + vendorKYCMainDto.getEscalationEmail() + "\n");
			htmlBuilder.append("            </td>\n");
			htmlBuilder.append(
					"            <td style=\"border: 1px solid #D3D3D3;padding: 5px;vertical-align: top;width:7em;word-wrap: break-word;color: #696969;font-size: 11px;margin:0\">\n");
			htmlBuilder.append("                    " + vendorKYCMainDto.getEscalationMobile() + "\n");
			htmlBuilder.append("            </td>\n");
			htmlBuilder.append(
					"            <td style=\"border: 1px solid #D3D3D3;padding: 5px;vertical-align: top;width:7em;word-wrap: break-word;color: #696969;font-size: 11px;margin:0\">\n");
			htmlBuilder.append("                    "
					+ ((vendorKYCMainDto.getEscalationTel() != null && !vendorKYCMainDto.getEscalationTel().isEmpty())
							? vendorKYCMainDto.getEscalationTel()
							: "N/A")
					+ "\n");
			htmlBuilder.append("            </td>\n");
			htmlBuilder.append("        </tr>\n");
			htmlBuilder.append("    </table>\n");
			htmlBuilder.append("\n");
			htmlBuilder
					.append("    <div style=\"border-left: 3px solid #C22457;padding-left: 10px;margin: 20px 0;\">\n");
			htmlBuilder.append(
					"        <span style=\"font-size: 14px;color: #C22457;font-weight: 400;\">Financial Information</span>\n");
			htmlBuilder.append("    </div>\n");
			htmlBuilder.append(
					"    <table style=\"width: 100%;border-collapse: collapse; border: 1px solid #D3D3D3;\">\n");
			htmlBuilder.append("        <tr>\n");
			htmlBuilder
					.append("            <td style=\"border: 1px solid #D3D3D3;padding: 5px;vertical-align: top\">\n");
			htmlBuilder.append(
					"                <p style=\"color: #3C3535;font-weight: 500;margin:0;font-size: 13px;\">Bank Name</p>\n");
			htmlBuilder.append(
					"                <p style=\"width:13em;word-wrap: break-word;color: #696969;font-size: 11px;margin:0\">"
							+ vendorKYCMainDto.getVenderBankDetails().getBankName() + "</p>\n");
			htmlBuilder.append("            </td>\n");
			htmlBuilder.append(
					"            <td style=\"border: 1px solid #D3D3D3; padding: 5px;vertical-align: top \">\n");
			htmlBuilder.append(
					"                <p style=\"color: #3C3535;font-weight: 500;margin:0;font-size: 13px;\">Branch</p>\n");
			htmlBuilder.append(
					"                <p style=\"width:15em;word-wrap: break-word;color: #696969;font-size: 11px;margin:0\">"
							+ vendorKYCMainDto.getVenderBankDetails().getBranch() + "</p>\n");
			htmlBuilder.append("            </td>\n");
			htmlBuilder.append("\n");
			htmlBuilder.append("            </td>\n");
			htmlBuilder.append(
					"            <td style=\"border: 1px solid #D3D3D3; padding: 5px;vertical-align: top \">\n");
			htmlBuilder.append(
					"                <p style=\"color: #3C3535;font-weight: 500;margin:0;font-size: 13px;\">IFSC Code</p>\n");
			htmlBuilder.append(
					"                <p style=\"width:15em;word-wrap: break-word;color: #696969;font-size: 11px;margin:0\">"
							+ vendorKYCMainDto.getVenderBankDetails().getIfscCode() + "</p>\n");
			htmlBuilder.append("            </td>\n");
			htmlBuilder.append("        </tr>\n");
			htmlBuilder.append("        <tr>\n");
			htmlBuilder
					.append("            <td style=\"border: 1px solid #D3D3D3; padding: 5px;vertical-align: top\">\n");
			htmlBuilder.append(
					"                <p style=\"color: #3C3535;font-weight: 500;margin:0;font-size: 13px;\">Beneficiary Account Name</p>\n");
			htmlBuilder.append(
					"                <p style=\"width:15em;word-wrap: break-word;color: #696969;font-size: 11px;margin:0\">"
							+ vendorKYCMainDto.getVenderBankDetails().getBeneficiaryAccName() + "</p>\n");
			htmlBuilder.append("            </td>\n");
			htmlBuilder
					.append("            <td style=\"border: 1px solid #D3D3D3; padding: 5px;vertical-align: top\">\n");
			htmlBuilder.append(
					"                <p style=\"color: #3C3535;font-weight: 500;margin:0;font-size: 13px;\">Account No</p>\n");
			htmlBuilder.append(
					"                <p style=\"width:10em;word-wrap: break-word;color: #696969;font-size: 11px;margin:0\">"
							+ vendorKYCMainDto.getVenderBankDetails().getAccountNo() + "</p>\n");
			htmlBuilder.append("            </td>\n");
			htmlBuilder
					.append("            <td style=\"border: 1px solid #D3D3D3; padding: 5px;vertical-align: top\">\n");
			htmlBuilder.append(
					"                <p style=\"color: #3C3535;font-weight: 500;margin:0;font-size: 13px;\">SWIFT</p>\n");
			htmlBuilder.append(
					"                <p style=\"width:15em;word-wrap: break-word;color: #696969;font-size: 11px;margin:0\">"
							+ ((vendorKYCMainDto.getVenderBankDetails().getSwiftCode() != null
									&& !vendorKYCMainDto.getVenderBankDetails().getSwiftCode().isEmpty())
											? vendorKYCMainDto.getVenderBankDetails().getSwiftCode()
											: "N/A")
							+ "</p>\n");
			htmlBuilder.append("            </td>\n");
			htmlBuilder.append("        <tr>\n");
			htmlBuilder.append(
					"            <td style=\"border: 1px solid #D3D3D3;vertical-align: top; padding: 5px;\" rowspan=\"2\";>\n");
			htmlBuilder.append(
					"                <p style=\"color: #3C3535;font-weight: 500;margin:0;font-size: 13px;\">Address</p>\n");
			htmlBuilder.append(
					"                <p style=\"word-wrap: break-word;color: #696969;font-size: 11px;margin:0\">");
			String bankAddressLine1 = vendorKYCMainDto.getVenderBankDetails().getBankAddressLine1().replace("⋅",
					"&middot;");
			htmlBuilder.append(bankAddressLine1 + " ");
			if (Objects.nonNull(vendorKYCMainDto.getVenderBankDetails().getBankAddressLine2())) {
				String bankAddressLine2 = vendorKYCMainDto.getVenderBankDetails().getBankAddressLine2().replace("⋅",
						"&middot;");
				htmlBuilder.append(bankAddressLine2);
			}

			htmlBuilder.append("            </td>\n");
			htmlBuilder
					.append("            <td style=\"border: 1px solid #D3D3D3; padding: 5px;vertical-align: top\">\n");
			htmlBuilder.append(
					"                <p style=\"color: #3C3535;font-weight: 500;margin:0;font-size: 13px;\">City</p>\n");
			htmlBuilder.append(
					"                <p style=\"width:15em;word-wrap: break-word;color: #696969;font-size: 11px;margin:0\">"
							+ vendorKYCMainDto.getVenderBankDetails().getCity() + "</p>\n");
			htmlBuilder.append("            </td>\n");
			htmlBuilder
					.append("            <td style=\"border: 1px solid #D3D3D3; padding: 5px;vertical-align: top\">\n");
			htmlBuilder.append(
					"                <p style=\"color: #3C3535;font-weight: 500;margin:0;font-size: 13px;\">State</p>\n");
			htmlBuilder.append(
					"                <p style=\"width:15em;word-wrap: break-word;color: #696969;font-size: 11px;margin:0\">"
							+ vendorKYCMainDto.getVenderBankDetails().getState() + "</p>\n");
			htmlBuilder.append("            </td>\n");

			htmlBuilder.append("        </tr>\n");
			htmlBuilder.append("        <tr>\n");
			htmlBuilder.append(
					"            <td style=\"border: 1px solid #D3D3D3; padding: 5px;vertical-align: top\" >\n");
			htmlBuilder.append(
					"                <p style=\"color: #3C3535;font-weight: 500;margin:0;font-size: 13px;\">Country</p>\n");
			htmlBuilder.append(
					"                <p style=\"width:15em;word-wrap: break-word;word-wrap: break-word;color: #696969;font-size: 11px;margin:0\">"
							+ vendorKYCMainDto.getVenderBankDetails().getCountryMaster().getCountry() + "</p>\n");
			htmlBuilder.append("            </td>\n");
			htmlBuilder
					.append("            <td style=\"border: 1px solid #D3D3D3; padding: 5px;vertical-align: top\">\n");
			htmlBuilder.append(
					"                <p style=\"color: #3C3535;font-weight: 500;font-size: 13px;margin:0;\">ZIP code</p>\n");
			htmlBuilder.append(
					"                <p style=\"width:15em;word-wrap: break-word;word-wrap: break-word;color: #696969;font-size: 11px;margin:0\">"
							+ vendorKYCMainDto.getVenderBankDetails().getZipCode() + "</p>\n");
			htmlBuilder.append("            </td>\n");
			htmlBuilder.append("        </tr>\n");
			htmlBuilder.append("        </tr>\n");
			htmlBuilder.append("        <tr>\n");
			htmlBuilder
					.append("            <td style=\"padding: 5px; border: 1px solid #D3D3D3;vertical-align: top\">\n");
			htmlBuilder.append(
					"                <p style=\"color: #3C3535;font-weight: 500;margin:0;font-size: 13px;\">Account Currency</p>\n");

			if (Objects.nonNull(vendorKYCMainDto.getVenderBankDetails())
					&& Objects.nonNull(vendorKYCMainDto.getVenderBankDetails().getAccCurrency()) && vendorKYCMainDto
							.getVenderBankDetails().getAccCurrency().equalsIgnoreCase("AMD ֏ - Armenian dram")) {
				String crrencyImg = "<img src=\"data:image/svg+xml;base64," + getCurrency()
						+ "\"style=\"width:16px;height:10px;opacity:0.6;\">";
				htmlBuilder
						.append("<p style=\"width:15em;word-wrap: break-word;color: #696969;font-size: 11px;margin:0\">"
								+ vendorKYCMainDto.getVenderBankDetails().getAccCurrency().replace("֏", crrencyImg)
								+ "</p>\n");
			} else if (Objects.nonNull(vendorKYCMainDto.getVenderBankDetails())
					&& Objects.nonNull(vendorKYCMainDto.getVenderBankDetails().getAccCurrency())
					&& vendorKYCMainDto.getVenderBankDetails().getAccCurrency()
							.equalsIgnoreCase("SAR ﷼ - Saudi Arabia Riyal")
					|| vendorKYCMainDto.getVenderBankDetails().getAccCurrency()
							.equalsIgnoreCase("YER ﷼ - Yemen Rial")) {
				String crrencyImg = "<img src=\"data:image/svg+xml;base64," + getRiyalCurrency()
						+ "\"style=\"width:16px;height:10px;opacity:0.6;\">";
				htmlBuilder
						.append("<p style=\"width:15em;word-wrap: break-word;color: #696969;font-size: 11px;margin:0\">"
								+ vendorKYCMainDto.getVenderBankDetails().getAccCurrency().replace("﷼", crrencyImg)
								+ "</p>\n");

			} else

				htmlBuilder
						.append("<p style=\"width:15em;word-wrap: break-word;color: #696969;font-size: 11px;margin:0\">"
								+ vendorKYCMainDto.getVenderBankDetails().getAccCurrency() + "</p>\n");
			htmlBuilder.append("            </td>\n");
			htmlBuilder.append("            <td style=\"padding: 5px;\" colspan=\"2\";vertical-align: top;>\n");
			htmlBuilder.append(
					"                <p style=\"color: #3C3535;font-weight: 500;margin:0;font-size: 13px;\">Intermediary Bank Details</p>\n");
			htmlBuilder.append(
					"                <p style=\"word-wrap: break-word;color: #696969;font-size: 11px;margin:0\">\n");
			htmlBuilder.append(
					"                    " + ((vendorKYCMainDto.getVenderBankDetails().getIntermediaryBankDtl() != null
							&& !vendorKYCMainDto.getVenderBankDetails().getIntermediaryBankDtl().isEmpty())
									? vendorKYCMainDto.getVenderBankDetails().getIntermediaryBankDtl().replace("\n",
											"<br>")
									: "N/A")
							+ "\n");
			htmlBuilder.append("                </p>\n");
			htmlBuilder.append("            </td>\n");
			htmlBuilder.append("        </tr>\n");
			htmlBuilder.append("\n");
			htmlBuilder.append("\n");
			htmlBuilder.append("        <tr>\n");
			htmlBuilder
					.append("            <td style=\"border: 1px solid #D3D3D3; padding: 5px;vertical-align: top\">\n");
			htmlBuilder.append(
					"                <p style=\"color: #3C3535;font-weight: 500;margin:0;font-size: 13px;\">Comapany Turnover</p>\n");
			if (Objects.nonNull(vendorKYCMainDto.getVenderBankDetails())
					&& Objects.nonNull(vendorKYCMainDto.getVenderBankDetails().getAccCurrency()) && vendorKYCMainDto
							.getVenderBankDetails().getAccCurrency().equalsIgnoreCase("AMD ֏ - Armenian dram")) {
				String crrencyImg = "<img src=\"data:image/svg+xml;base64," + getCurrency()
						+ "\"style=\"width:16px;height:10px;opacity:0.6;\">";
				htmlBuilder
						.append("<p style=\"width:15em;word-wrap: break-word;color: #696969;font-size: 11px;margin:0\">"
								+ (vendorKYCMainDto.getVenderBankDetails().getCompanyTurnover() != null ? crrencyImg
										+ " " + vendorKYCMainDto.getVenderBankDetails().getCompanyTurnover() : "N/A")
								+ "</p>\n");
			} else if (Objects.nonNull(vendorKYCMainDto.getVenderBankDetails())
					&& Objects.nonNull(vendorKYCMainDto.getVenderBankDetails().getAccCurrency())
					&& vendorKYCMainDto.getVenderBankDetails().getAccCurrency()
							.equalsIgnoreCase("SAR ﷼ - Saudi Arabia Riyal")
					|| vendorKYCMainDto.getVenderBankDetails().getAccCurrency()
							.equalsIgnoreCase("YER ﷼ - Yemen Rial")) {
				String crrencyImg = "<img src=\"data:image/svg+xml;base64," + getRiyalCurrency()
						+ "\"style=\"width:16px;height:10px;opacity:0.6;\">";
				htmlBuilder
						.append("<p style=\"width:15em;word-wrap: break-word;color: #696969;font-size: 11px;margin:0\">"
								+ (vendorKYCMainDto.getVenderBankDetails().getCompanyTurnover() != null ? crrencyImg
										+ " " + vendorKYCMainDto.getVenderBankDetails().getCompanyTurnover() : "N/A")
								+ "</p>\n");
			} else
				htmlBuilder.append(
						"                <p style=\"width:10em;word-wrap: break-word;color: #696969;font-size: 11px;margin:0\">"
								+ (vendorKYCMainDto.getVenderBankDetails().getCompanyTurnover() != null
										? (vendorKYCMainDto.getVenderBankDetails().getAccCurrency()).substring(3, 5)
												+ " " + vendorKYCMainDto.getVenderBankDetails().getCompanyTurnover()
										: "N/A")
								+ "</p>\n");
			htmlBuilder.append("            </td>\n");
			htmlBuilder.append(
					"           <td style=\"padding: 5px;border: 1px solid #D3D3D3;\" colspan=\"2\";vertical-align: top>\n");
			htmlBuilder.append(
					"                <p style=\"color: #3C3535;font-weight: 500;margin:0;font-size: 13px;\">ITR Filled?</p>\n");
			htmlBuilder.append(
					"                <p style=\"width:10em;word-wrap: break-word;color: #696969;font-size: 11px;margin:0\">"
							+ (vendorKYCMainDto.getVenderBankDetails().getIsItrFiled()) + "</p>\n");
			htmlBuilder.append("            </td>\n");
			htmlBuilder.append("        </tr>\n");
			htmlBuilder.append("\n");
			htmlBuilder.append("    </table>\n");
			if (Objects.nonNull(vendorKYCMainDto.getVendorCoreGoodServicesList())) {

				List<VendorCoreGoodServicesDTO> vendorCoreGoodServicesList = vendorKYCMainDto
						.getVendorCoreGoodServicesList();
				htmlBuilder.append(
						"    <div style=\"border-left: 3px solid #C22457;padding-left: 10px;margin: 20px 0;\">\n");
				htmlBuilder.append(
						"        <span style=\"font-size: 14px;color: #C22457;font-weight: 400;\">List of Core Goods/Services</span>\n");
				htmlBuilder.append("    </div>\n");
				htmlBuilder.append(
						"    <table style=\"width: 100%;border-collapse: collapse; border: 1px solid #D3D3D3;\">\n");
				htmlBuilder.append("        <tr>\n");
				htmlBuilder.append(
						"            <td style=\"border: 1px solid #D3D3D3;vertical-align: top;padding: 5px;color: #696969;font-size: 11px;x\">\n");
				for (VendorCoreGoodServicesDTO vendorCoreGood : vendorCoreGoodServicesList) {
					htmlBuilder.append("                <p style=\"color: #696969;font-size: 11px;margin:0;\">"
							+ (vendorCoreGood.getCoreGoodsService()) + "\n" + "</p>\n");
				}
				htmlBuilder.append("            </td>\n");
				htmlBuilder.append("        <tr>\n");
				htmlBuilder.append("    </table>\n");
				htmlBuilder.append("\n");
			}
			if (Objects.nonNull(vendorKYCMainDto.getVendorMajorCustomerList())) {
				List<VendorMajorCustomerDTO> vendorMajorCustomerList = vendorKYCMainDto.getVendorMajorCustomerList();
				htmlBuilder.append(
						"    <div style=\"border-left: 3px solid #C22457;padding-left: 10px;margin: 20px 0;\">\n");
				htmlBuilder.append(
						"        <span style=\"font-size: 14px;color: #C22457;font-weight: 400;\">Major Customers</span>\n");
				htmlBuilder.append("    </div>\n");
				htmlBuilder.append(
						"    <table style=\"width: 100%;border-collapse: collapse; border: 1px solid #D3D3D3;\">\n");
				htmlBuilder.append("        <tr>\n");
				htmlBuilder.append(
						"            <td style=\"border: 1px solid #D3D3D3;vertical-align: top;padding: 5px;color: #696969;font-size: 11px;\">\n");
				for (VendorMajorCustomerDTO majorcustomer : vendorMajorCustomerList) {
					htmlBuilder.append("                <p style=\"color: #696969;font-size: 11px;margin:0;\">"
							+ (majorcustomer.getMajorCustomer()) + "\n" + "</p>\n");
				}
				htmlBuilder.append("            </td>\n");
				htmlBuilder.append("        <tr>\n");
				htmlBuilder.append("    </table>\n");
			}

			if (Objects.nonNull(companyIncorporationDocument) || Objects.nonNull(cancelledChequeBankDocument)
					|| Objects.nonNull(panCard) || Objects.nonNull(gSTCertificate)
					|| Objects.nonNull(last3MonthBankstmt) || Objects.nonNull(underTaxExemptionsDoc)
					|| Objects.nonNull(msmeCertificate) || Objects.nonNull(iTRFiled)) {
				htmlBuilder.append(
						"    <div style=\"border-left: 3px solid #C22457;padding-left: 10px;margin: 20px 0;\">\n");
				htmlBuilder.append(
						"        <span style=\"font-size: 14px;color: #C22457;font-weight: 400;\">Uploaded Documents</span>\n");
				htmlBuilder.append("    </div>\n");
				htmlBuilder.append("<ul>\n");
				String url = pdfUrl + encodeToString;
				if (Objects.nonNull(companyIncorporationDocument)) {
					htmlBuilder.append("<li style=\"color: #696969; font-size: 11px\">\r\n").append(url).append("/")
							.append(Base64.getEncoder().encodeToString("1".getBytes()))
							.append("\" target=\"_blank\">Company Incorporation Document</a>\r\n").append("</li>");
				}
				if (Objects.nonNull(cancelledChequeBankDocument)) {

					htmlBuilder.append("<li style=\"color: #696969; font-size: 11px\">\r\n").append(url).append("/")
							.append(Base64.getEncoder().encodeToString("2".getBytes()))
							.append("\" target=\"_blank\">Cancelled Cheque/ Bank Letter Statement</a>\r\n")
							.append("</li>");
				}
				if (Objects.nonNull(panCard)) {
					htmlBuilder.append("<li style=\"color: #696969; font-size: 11px\">\r\n").append(url).append("/")
							.append(Base64.getEncoder().encodeToString("3".getBytes()))
							.append("\" target=\"_blank\">Pan Card</a>\r\n").append("</li>");
				}
				if (Objects.nonNull(gSTCertificate)) {

					htmlBuilder.append("<li style=\"color: #696969; font-size: 11px\">\r\n").append(url).append("/")
							.append(Base64.getEncoder().encodeToString("4".getBytes()))
							.append("\" target=\"_blank\">GST Certificate</a>\r\n").append("</li>");
				}
				if (Objects.nonNull(last3MonthBankstmt)) {

					htmlBuilder.append("<li style=\"color: #696969; font-size: 11px\">\r\n").append(url).append("/")
							.append(Base64.getEncoder().encodeToString("5".getBytes()))
							.append("\" target=\"_blank\">Last 3 Month Bank Statement</a>\r\n").append("</li>");
				}
				if (Objects.nonNull(underTaxExemptionsDoc)) {

					htmlBuilder.append("<li style=\"color: #696969; font-size: 11px\">\r\n").append(url).append("/")
							.append(Base64.getEncoder().encodeToString("7".getBytes()))
							.append("\" target=\"_blank\">Tax Exemptions Document</a>\r\n").append("</li>");
				}
				if (Objects.nonNull(msmeCertificate)) {

					htmlBuilder.append("<li style=\"color: #696969; font-size: 11px\">\r\n").append(url).append("/")
							.append(Base64.getEncoder().encodeToString("8".getBytes()))
							.append("\" target=\"_blank\">MSME Certificate</a>\r\n").append("</li>");
				}
				if (Objects.nonNull(iTRFiled)) {

					htmlBuilder.append("<li style=\"color: #696969; font-size: 11px\">\r\n").append(url).append("/")
							.append(Base64.getEncoder().encodeToString("9".getBytes()))
							.append("\" target=\"_blank\">ITR Filled</a>\r\n").append("</li>");

				}
			}
			htmlBuilder.append("</ul>\n");
			htmlBuilder
					.append("    <div style=\"border-left: 3px solid #C22457;padding-left: 10px;margin: 20px 0;\">\n");
			htmlBuilder.append(
					"        <span style=\"font-size: 14px;color: #C22457;font-weight: 400;\">Declaration (Authorised Supplier’s Representative and Approved Signatory)</span>\n");
			htmlBuilder.append("    </div>\n");
			htmlBuilder.append(
					"    <p style=\"color: #696969;font-size: 11px\"><input style=\"position: relative; top:5px; background: red;\"type='checkbox' checked=true />\r\n"
							+ "I hereby declare that, to the best of my knowledge, the information furnished in this form is true and correct in every respect.</p>\n");

			htmlBuilder.append(
					"    <table style=\"width: 100%;border-collapse: collapse; border: 1px solid #D3D3D3;\">\n");
			htmlBuilder.append("        <tr>\n");
			htmlBuilder
					.append("            <td style=\"border: 1px solid #D3D3D3;padding: 5px;vertical-align: top\">\n");
			htmlBuilder.append(
					"                <span style=\"color: #3C3535;font-weight: 500;font-size: 13px;\">Name</span>\n");
			htmlBuilder.append(
					"                <p style=\"width:14em;word-wrap: break-word;color: #696969;font-size: 11px;margin:0\">"
							+ vendorKYCMainDto.getDeclName() + "</p>\n");
			htmlBuilder.append("            </td>\n");
			htmlBuilder.append(
					"            <td style=\"border: 1px solid #D3D3D3;vertical-align: top; padding: 5px; \">\n");
			htmlBuilder.append(
					"                <span style=\"color: #3C3535;font-weight: 500;font-size: 13px;\">Designation</span>\n");
			htmlBuilder.append(
					"                <p style=\"width:13em;word-wrap: break-word;color: #696969;font-size: 11px;margin:0;\">"
							+ vendorKYCMainDto.getDeclDesignation() + "</p>\n");
			htmlBuilder.append("            </td>\n");
			htmlBuilder.append("\n");
			htmlBuilder.append("            </td>\n");
			htmlBuilder.append("            <td style=\"border: 1px solid #D3D3D3; padding: 5px;margin:0; \">\n");
			htmlBuilder.append(
					"                <span style=\"color: #3C3535;font-weight: 500;font-size: 13px;\">Date</span>\n");
			htmlBuilder.append(
					"                <p style=\"width:8em;word-wrap: break-word;color: #696969;font-size: 11px;margin:0;\">"
							+ (vendorKYCMainDto.getOnboardingDate()).format(formatter) + "</p>\n");
			htmlBuilder.append("            </td>\n");
			htmlBuilder.append("        </tr>\n");
			htmlBuilder.append("       \n");
			htmlBuilder.append("    </table>\n");

			htmlBuilder.append(
					"    <div style=\"display: flex;flex-direction: column;justify-content: flex-end;align-items: flex-end;margin-top: 40px;gap:10px;\">\n");

			htmlBuilder.append(
					"        <label style=\"font-weight: 400;margin-top: 40px;font-size: 13px;color: #3C3535;width:25%\">Authorized Signature</label>\n");
			htmlBuilder.append("    </div>\n");

			htmlBuilder.append("</div>\n");
			htmlBuilder.append("</div>\n");
			htmlBuilder.append("</body>\n");
			htmlBuilder.append("</html>\n");
			return htmlBuilder.toString();
		} catch (Exception e) {
			throw new CRMException(e);
		}
	}

}

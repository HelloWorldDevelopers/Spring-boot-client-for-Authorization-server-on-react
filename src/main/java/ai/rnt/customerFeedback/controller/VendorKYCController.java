package ai.rnt.customerFeedback.controller;

import static ai.rnt.customerFeedback.constants.ApiConstants.BASE;
import static ai.rnt.customerFeedback.enums.ApiResponse.DATA;

import java.io.IOException;
import java.util.EnumMap;
import java.util.Objects;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ai.rnt.customerFeedback.dto.VendorDocumentAttachmentUploadDTO;
import ai.rnt.customerFeedback.dto.VendorKYCDto;
import ai.rnt.customerFeedback.dto.VendorOnboardingMailBoxDTO;
import ai.rnt.customerFeedback.dto.VendorRegistrationDto;
import ai.rnt.customerFeedback.enums.ApiResponse;
import ai.rnt.customerFeedback.exception.CRMException;
import ai.rnt.customerFeedback.repository.VendorKYCRepository;
import ai.rnt.customerFeedback.service.VendorKYCService;
import ai.rnt.customerFeedback.service.VendorStateGSTCodeService;
import ai.rnt.customerFeedback.util.EmailUtil;
import ai.rnt.customerFeedback.util.GeneratePdf1;
import ai.rnt.customerFeedback.util.TokanEncrypterDecrypter;

@RestController
@RequestMapping(BASE)
@CrossOrigin("*")

public class VendorKYCController {

	@Autowired
	private EmailUtil emailUtil;

	@Autowired
	VendorKYCService vendorKYCService;

	@Autowired
	VendorStateGSTCodeService gstCodeService;

	@Autowired
	VendorKYCRepository vendorKYCRepository;

	@Autowired
	GeneratePdf1 generatePdf;

	@Autowired
	TokanEncrypterDecrypter tokanEncrypterDecrypter;

	@PutMapping("kycvendorform")
	public ResponseEntity<EnumMap<ApiResponse, Object>> saveKycVendorForm(@RequestBody VendorKYCDto vendorKYCDto) {
		return vendorKYCService.saveVendorKYCForm(vendorKYCDto);
	}

	@PostMapping("kycvendorregistration")
	public ResponseEntity<EnumMap<ApiResponse, Object>> saveKycVendorFormRegistration(
			@RequestBody VendorRegistrationDto vendorRegistrationDto) {
		return vendorKYCService.saveVendorRegistration(vendorRegistrationDto);
	}

	@GetMapping("getAllKYCForm")
	public ResponseEntity<EnumMap<ApiResponse, Object>> getVendorKYCForm() {
		return vendorKYCService.findAll();
	}

	@GetMapping("kycvendorform/{id}")
	public ResponseEntity<EnumMap<ApiResponse, Object>> getVendorKYCForm(@PathVariable("id") int id) {
		return vendorKYCService.findById(id);
	}

	@GetMapping("kycvendorfullform/{id}")
	public ResponseEntity<EnumMap<ApiResponse, Object>> getVendorKYCFullForm(@PathVariable("id") int id) {
		return vendorKYCService.getFullForm(id);
	}

	@PutMapping("vendorupdate")
	public ResponseEntity<EnumMap<ApiResponse, Object>> updateVendorRegistration(
			@RequestBody VendorRegistrationDto vendorRegistrationDto) {
		return vendorKYCService.updateVendorRegistration(vendorRegistrationDto);
	}

	@GetMapping("/generatePdf/{id}")
	public ResponseEntity<ByteArrayResource> generatePdf(@PathVariable("id") Integer id) {
		try {
			EnumMap<ApiResponse, Object> resposeMap = vendorKYCService.findById(id).getBody();
			if (Objects.nonNull(resposeMap)) {
				Optional<VendorKYCDto> vendorKYCMainDto = (Optional<VendorKYCDto>) resposeMap.get(DATA);
				if (vendorKYCMainDto.isPresent()) {
					return generatePdf.generatePdfMethod(vendorKYCMainDto.get());
				}
			}
		} catch (Exception e) {
			throw new CRMException(e);
		}
		return null;
	}

	@PostMapping("sendMailToVendor/{id}")
	public ResponseEntity<EnumMap<ApiResponse, Object>> sendEmailVendor(@PathVariable int id) {
		return emailUtil.sendEmailVedor(id);
	}

	@PostMapping("upload")
	public ResponseEntity<EnumMap<ApiResponse, Object>> uploadsignedDocument(
			@RequestBody VendorDocumentAttachmentUploadDTO vendorDocumentAttachmentUploadDTO) {
		return vendorKYCService.uploadDocument(vendorDocumentAttachmentUploadDTO);
	}

	@GetMapping("getstate/{code}")
	public ResponseEntity<EnumMap<ApiResponse, Object>> getStateByGSTCode(@PathVariable("code") int code) {
		return gstCodeService.findStateByGSTCode(code);
	}

	@GetMapping("getAllCountry")
	public ResponseEntity<EnumMap<ApiResponse, Object>> getAllCountry() {
		return gstCodeService.findAll();
	}

	@GetMapping("getAllStateWithCode")
	public ResponseEntity<EnumMap<ApiResponse, Object>> getAllStateWithCode() {
		return gstCodeService.findAllStateByGSTCode();
	}

	@PostMapping("saveVendorOnboardingEmail")
	public ResponseEntity<EnumMap<ApiResponse, Object>> saveVendoremail(
			@RequestBody VendorOnboardingMailBoxDTO vendorOnboardingMailBoxDTO) {
		return vendorKYCService.saveVendoremail(vendorOnboardingMailBoxDTO);
	}

	@GetMapping("getVendorKycMail/{kycId}")
	public ResponseEntity<EnumMap<ApiResponse, Object>> getSavedEmail(@PathVariable int kycId) {
		return vendorKYCService.getSavedEmail(kycId);
	}

	@GetMapping("getPdfFile/{id}/{docId}")
	public void getPdfFile(HttpServletResponse response, @PathVariable String id, @PathVariable String docId)
			throws IOException {
		vendorKYCService.getPdfFile(response,id,docId);
	}
	
	@GetMapping("getrntlogo/{imangeName}")
	public void getrntlogo(HttpServletResponse response,@PathVariable String imangeName) {
		 vendorKYCService.getBugImage(imangeName,response);
	}
}
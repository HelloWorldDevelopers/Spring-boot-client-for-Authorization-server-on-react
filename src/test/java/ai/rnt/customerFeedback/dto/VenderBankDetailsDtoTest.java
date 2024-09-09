package ai.rnt.customerFeedback.dto;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class VenderBankDetailsDtoTest {

	@Test
	void testVenderBankDetailsDto() {
		Integer expectedVendorBankDtlId = 1;
		String expectedBankName = "ABC Bank";
		String expectedBranch = "Main Branch";
		String expectedBeneficiaryAccName = "John Doe";
		String expectedAccountNo = "1234567890";
		String expectedIntermediaryBankDtl = "Intermediary Bank, City";
		String expectedAccCurrency = "USD";
		String expectedIfscCode = "ABCD123456";
		String expectedSwiftCode = "SWIFT123456";
		VenderBankDetailsDto venderBankDetailsDto = new VenderBankDetailsDto();
		venderBankDetailsDto.setVendorBankDtlId(expectedVendorBankDtlId);
		venderBankDetailsDto.setBankName(expectedBankName);
		venderBankDetailsDto.setBranch(expectedBranch);
		venderBankDetailsDto.setBeneficiaryAccName(expectedBeneficiaryAccName);
		venderBankDetailsDto.setAccountNo(expectedAccountNo);
		venderBankDetailsDto.setIntermediaryBankDtl(expectedIntermediaryBankDtl);
		venderBankDetailsDto.setAccCurrency(expectedAccCurrency);
		venderBankDetailsDto.setIfscCode(expectedIfscCode);
		venderBankDetailsDto.setSwiftCode(expectedSwiftCode);
		venderBankDetailsDto.setCompanyTurnover("5678934");
		venderBankDetailsDto.setIsItrFiled("Yes");
		assertEquals(expectedVendorBankDtlId, venderBankDetailsDto.getVendorBankDtlId());
		assertEquals(expectedBankName, venderBankDetailsDto.getBankName());
		assertEquals(expectedBranch, venderBankDetailsDto.getBranch());
		assertEquals(expectedBeneficiaryAccName, venderBankDetailsDto.getBeneficiaryAccName());
		assertEquals(expectedAccountNo, venderBankDetailsDto.getAccountNo());
		assertEquals(expectedIntermediaryBankDtl, venderBankDetailsDto.getIntermediaryBankDtl());
		assertEquals(expectedAccCurrency, venderBankDetailsDto.getAccCurrency());
		assertEquals(expectedIfscCode, venderBankDetailsDto.getIfscCode());
		assertEquals(expectedSwiftCode, venderBankDetailsDto.getSwiftCode());
		assertEquals("5678934", venderBankDetailsDto.getCompanyTurnover());
		assertEquals("Yes", venderBankDetailsDto.getIsItrFiled());

	}
}
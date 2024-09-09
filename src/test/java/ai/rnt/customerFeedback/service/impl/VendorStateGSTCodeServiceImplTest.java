package ai.rnt.customerFeedback.service.impl;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Sort;

import ai.rnt.customerFeedback.entity.CountryMaster;
import ai.rnt.customerFeedback.entity.VendorStateGSTCode;
import ai.rnt.customerFeedback.exception.CRMException;
import ai.rnt.customerFeedback.repository.CountryMasterRepository;
import ai.rnt.customerFeedback.repository.VendorStateGSTCodeRepository;

@ExtendWith(MockitoExtension.class)
class VendorStateGSTCodeServiceImplTest {

	@InjectMocks
	VendorStateGSTCodeServiceImpl codeServiceImpl;

	@Mock
	VendorStateGSTCodeRepository gstCodeRepository;

	@Mock
	CountryMasterRepository countryMasterRepository;

	@Test
	void testfindAllStateByGSTCode() {
		List<VendorStateGSTCode> stateGSTCodes = new ArrayList<>();
		VendorStateGSTCode vendorStateGSTCode = new VendorStateGSTCode();
		vendorStateGSTCode.setState("satere");
		vendorStateGSTCode.setStateGstCode(22);
		vendorStateGSTCode.setStateGstCodeId(1);
		stateGSTCodes.add(vendorStateGSTCode);
		when(gstCodeRepository.findAll()).thenReturn(stateGSTCodes);
		assertNotNull(codeServiceImpl.findAllStateByGSTCode());
		when(gstCodeRepository.findAll()).thenThrow(CRMException.class);
		assertThrows(CRMException.class, () -> codeServiceImpl.findAllStateByGSTCode());
	}

	@Test
	void testfindStateByGSTCode() {
		int staffId = 1;
		VendorStateGSTCode vendorStateGSTCode = new VendorStateGSTCode();
		vendorStateGSTCode.setState("satere");
		vendorStateGSTCode.setStateGstCode(22);
		vendorStateGSTCode.setStateGstCodeId(1);
		when(gstCodeRepository.findByStateGstCode(staffId)).thenReturn(vendorStateGSTCode);
		assertNotNull(codeServiceImpl.findStateByGSTCode(staffId));
		when(gstCodeRepository.findByStateGstCode(staffId)).thenThrow(CRMException.class);
		assertThrows(CRMException.class, () -> codeServiceImpl.findStateByGSTCode(staffId));
	}

	@Test
	void testfindAll() {
		List<CountryMaster> countryMasterList = new ArrayList<>();
		CountryMaster countryMaster = new CountryMaster();
		countryMaster.setCountryId(1);
		countryMaster.setCountry("india");
		countryMasterList.add(countryMaster);
		when(countryMasterRepository.findAll(Sort.by(Sort.Direction.ASC, "country"))).thenReturn(countryMasterList);
		assertNotNull(codeServiceImpl.findAll());
		when(countryMasterRepository.findAll(Sort.by(Sort.Direction.ASC, "country"))).thenThrow(CRMException.class);
		assertThrows(CRMException.class, () -> codeServiceImpl.findAll());
	}
}

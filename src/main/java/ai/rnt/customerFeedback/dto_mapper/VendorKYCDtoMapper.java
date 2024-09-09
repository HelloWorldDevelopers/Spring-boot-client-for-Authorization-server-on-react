package ai.rnt.customerFeedback.dto_mapper;

import static ai.rnt.customerFeedback.util.CollectionUtil.newList;
import static ai.rnt.customerFeedback.util.FunctionUtil.evalMapper;
import static ai.rnt.customerFeedback.util.FunctionUtil.evalMapperCollection;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import ai.rnt.customerFeedback.dto.CountryMasterDTO;
import ai.rnt.customerFeedback.dto.VendorCoreGoodServicesDTO;
import ai.rnt.customerFeedback.dto.VendorKYCDto;
import ai.rnt.customerFeedback.dto.VendorMajorCustomerDTO;
import ai.rnt.customerFeedback.dto.VendorOnboardingMailBoxDTO;
import ai.rnt.customerFeedback.dto.VendorRegistrationDto;
import ai.rnt.customerFeedback.dto.VendorServiceProviderDTO;
import ai.rnt.customerFeedback.entity.CountryMaster;
import ai.rnt.customerFeedback.entity.VendorCoreGoodServices;
import ai.rnt.customerFeedback.entity.VendorKYC;
import ai.rnt.customerFeedback.entity.VendorMajorCustomer;
import ai.rnt.customerFeedback.entity.VendorOnboardingMailBox;
import ai.rnt.customerFeedback.entity.VendorServiceProvider;

public class VendorKYCDtoMapper {
	private VendorKYCDtoMapper() {

	}

	public static final Function<VendorKYCDto, Optional<VendorKYC>> TO_VENDOR_KYC = e -> evalMapper(e, VendorKYC.class);
	public static final Function<VendorRegistrationDto, Optional<VendorKYC>> TO_VENDOR_KYC_RESISTRATION = e -> evalMapper(
			e, VendorKYC.class);
	public static final Function<VendorKYC, Optional<VendorRegistrationDto>> TO_VENDOR_KYC_RESISTRATION_DTO = e -> evalMapper(
			e, VendorRegistrationDto.class);
	public static final Function<VendorKYC, Optional<VendorKYCDto>> TO_VENDOR_KYC_MAIN_DTO = e -> evalMapper(e,
			VendorKYCDto.class);

	public static final Function<Collection<VendorKYC>, List<VendorRegistrationDto>> TO_VEDOR_REGISTRATION_DTO_LIST = e -> newList(
			evalMapperCollection(e, VendorRegistrationDto.class));

	public static final Function<VendorKYC, Optional<VendorKYCDto>> TO_VENDOR_KYC_DTO = e -> evalMapper(e,
			VendorKYCDto.class);

	public static final Function<VendorServiceProviderDTO, Optional<VendorServiceProvider>> TO_VENDOR_SERVICE_PROVIDER = e -> evalMapper(
			e, VendorServiceProvider.class);

	public static final Function<Collection<VendorServiceProviderDTO>, List<VendorServiceProvider>> TO_VENDOR_SERVICE_PROVIDER_LIST = e -> newList(
			evalMapperCollection(e, VendorServiceProvider.class));

	public static final Function<VendorMajorCustomerDTO, Optional<VendorMajorCustomer>> TO_VENDOR_MAJOR_CUSTOMER = e -> evalMapper(
			e, VendorMajorCustomer.class);

	public static final Function<Collection<VendorMajorCustomerDTO>, List<VendorMajorCustomer>> TO_VENDOR_MAJOR_CUSTOMER_LIST = e -> newList(
			evalMapperCollection(e, VendorMajorCustomer.class));

	public static final Function<VendorCoreGoodServicesDTO, Optional<VendorCoreGoodServices>> TO_VENDOR_COREGOODSSERVICES = e -> evalMapper(
			e, VendorCoreGoodServices.class);

	public static final Function<Collection<VendorCoreGoodServicesDTO>, List<VendorCoreGoodServices>> TO_VENDOR_COREGOODSSERVICES_LIST = e -> newList(
			evalMapperCollection(e, VendorCoreGoodServices.class));

	public static final Function<CountryMasterDTO, Optional<CountryMaster>> TO_COUNTRY_MASTER = e -> evalMapper(e,
			CountryMaster.class);

	public static final Function<VendorOnboardingMailBoxDTO, Optional<VendorOnboardingMailBox>> VENDORONBOARDING_MAILBOX = e -> evalMapper(
			e, VendorOnboardingMailBox.class);

	public static final Function<VendorOnboardingMailBox, Optional<VendorOnboardingMailBoxDTO>> VENDORONBOARDING_MAILBOX_DTO = e -> evalMapper(
			e, VendorOnboardingMailBoxDTO.class);

}

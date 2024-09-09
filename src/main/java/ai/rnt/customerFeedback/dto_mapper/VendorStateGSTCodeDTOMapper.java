package ai.rnt.customerFeedback.dto_mapper;

import static ai.rnt.customerFeedback.util.CollectionUtil.newList;
import static ai.rnt.customerFeedback.util.FunctionUtil.evalMapper;
import static ai.rnt.customerFeedback.util.FunctionUtil.evalMapperCollection;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import ai.rnt.customerFeedback.dto.CountryMasterDTO;
import ai.rnt.customerFeedback.dto.VendorStateGSTCodeDTO;
import ai.rnt.customerFeedback.entity.CountryMaster;
import ai.rnt.customerFeedback.entity.VendorStateGSTCode;

public class VendorStateGSTCodeDTOMapper {

	public static final Function<VendorStateGSTCode, Optional<VendorStateGSTCodeDTO>> TO_VENDOR_STATE_GST_DTO = e -> evalMapper(
			e, VendorStateGSTCodeDTO.class);

	public static final Function<Collection<VendorStateGSTCode>, List<VendorStateGSTCodeDTO>> TO_VENDOR_STATE_GST_DTO_LIST = e -> newList(
			evalMapperCollection(e, VendorStateGSTCodeDTO.class));

	public static final Function<Collection<CountryMaster>, List<CountryMasterDTO>> TO_COUNTRY_MASTER_DTO_LIST = e -> newList(
			evalMapperCollection(e, CountryMasterDTO.class));
}

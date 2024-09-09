package ai.rnt.customerFeedback.dto_mapper;

import static ai.rnt.customerFeedback.util.FunctionUtil.evalMapper;

import java.util.Optional;
import java.util.function.Function;

import ai.rnt.customerFeedback.dto.VenderBankDetailsDto;
import ai.rnt.customerFeedback.entity.VenderBankDetails;

public class VenderBankDetailsDtoMapper {
	
	private VenderBankDetailsDtoMapper() {}
	
	public static final Function<VenderBankDetailsDto, Optional<VenderBankDetails>> TO_VENDOR_BANK_DETAILS= e -> evalMapper(e, VenderBankDetails.class);


}

package ai.rnt.customerFeedback.dto_mapper;

import static ai.rnt.customerFeedback.util.CollectionUtil.newList;
import static ai.rnt.customerFeedback.util.FunctionUtil.evalMapper;
import static ai.rnt.customerFeedback.util.FunctionUtil.evalMapperCollection;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import ai.rnt.customerFeedback.dto.AddressMasterDTO;
import ai.rnt.customerFeedback.entity.AddressMaster;

public class AddressMasterModelMapper {
	public static final Function<Collection<AddressMaster>, List<AddressMasterDTO>> TO_ADDRESS_MASTER_DTO_LIST = e -> newList(
			evalMapperCollection(e, AddressMasterDTO.class));
	
	public static final Function<AddressMaster, Optional<AddressMasterDTO>> TO_ADDRESS_MASTER_DTO = e -> evalMapper(e, AddressMasterDTO.class);


}

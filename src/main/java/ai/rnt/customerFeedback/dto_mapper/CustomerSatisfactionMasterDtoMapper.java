/**
 * 
 */
package ai.rnt.customerFeedback.dto_mapper;

import static ai.rnt.customerFeedback.util.CollectionUtil.newList;
import static ai.rnt.customerFeedback.util.FunctionUtil.evalMapper;
import static ai.rnt.customerFeedback.util.FunctionUtil.evalMapperCollection;

import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import ai.rnt.customerFeedback.dto.CustomerSatisfactionDTO;
import ai.rnt.customerFeedback.dto.CustomerSatisfactionListDto;
import ai.rnt.customerFeedback.dto.CustomerSatisfactionMailBoxDTO;
import ai.rnt.customerFeedback.dto.CustomerSatisfactionMasterDTONew;
import ai.rnt.customerFeedback.dto.CustomerSatisfactionMasterDto;
import ai.rnt.customerFeedback.entity.CustomerSatisfactionMailBox;
import ai.rnt.customerFeedback.entity.CustomerSatisfactionMaster;

/**
 * @author Nikita Raut
 * @Date Jan 12, 2024
 * @Version 1.0
 */
public class CustomerSatisfactionMasterDtoMapper {

	private CustomerSatisfactionMasterDtoMapper() {

	}

	public static final Function<CustomerSatisfactionMasterDto, Optional<CustomerSatisfactionMaster>> TO_CUST_SATISFACTION_MASTER = e -> evalMapper(
			e, CustomerSatisfactionMaster.class);

	public static final Function<CustomerSatisfactionMaster, Optional<CustomerSatisfactionMasterDto>> TO_CUST_SATISFACTION_MASTER_DTO = e -> evalMapper(
			e, CustomerSatisfactionMasterDto.class);

	public static final Function<CustomerSatisfactionMaster, Optional<CustomerSatisfactionMasterDto>> TO_CUSTOMER_SATISFACTION_MASTER_DTO = e -> {
		CustomerSatisfactionMasterDto customerSatisfactionMasterDto = evalMapper(e, CustomerSatisfactionMasterDto.class)
				.get();
		return Optional.of(customerSatisfactionMasterDto);
	};

	public static final Function<CustomerSatisfactionMaster, Optional<CustomerSatisfactionMasterDTONew>> TO_CUSTOMER_SATISFACTION_DTO = e -> evalMapper(
			e, CustomerSatisfactionMasterDTONew.class);

	public static final Function<CustomerSatisfactionMasterDto, Optional<CustomerSatisfactionMaster>> TO_CUSTOMER_SATISFACTION_MASTER = e -> {
		CustomerSatisfactionMaster customerSatisfactionMaster = evalMapper(e, CustomerSatisfactionMaster.class).get();
		return Optional.of(customerSatisfactionMaster);
	};

	public static final Function<CustomerSatisfactionMaster, Optional<CustomerSatisfactionListDto>> TO_CUSTOMERS_SATISFACTION_DTO = e -> {
		CustomerSatisfactionListDto customerSatisfaction = evalMapper(e, CustomerSatisfactionListDto.class).get();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		if (Objects.nonNull(e.getAddressId())) {
			customerSatisfaction.setCreatedDate(e.getCreatedDate().format(formatter));
		}
		return Optional.of(customerSatisfaction);
	};

	public static final Function<Collection<CustomerSatisfactionMaster>, List<CustomerSatisfactionDTO>> TO_CUSTOMER_SATISFACTION_MASTER_LIST_DTO = e -> newList(
			evalMapperCollection(e, CustomerSatisfactionDTO.class));

	public static final Function<Collection<CustomerSatisfactionMaster>, List<CustomerSatisfactionListDto>> TO_CUSTOMER_SATISFACTION_LIST_DTO = e -> e
			.stream().map(dm -> TO_CUSTOMERS_SATISFACTION_DTO.apply(dm).get()).collect(Collectors.toList());

	public static final Function<CustomerSatisfactionMailBoxDTO, Optional<CustomerSatisfactionMailBox>> CUSTOMER_SATISFACTION_MAILBOX = e -> evalMapper(
			e, CustomerSatisfactionMailBox.class);

	public static final Function<CustomerSatisfactionMailBox, Optional<CustomerSatisfactionMailBoxDTO>> CUSTOMER_SATISFACTION_MAILBOX_DTO = e -> evalMapper(
			e, CustomerSatisfactionMailBoxDTO.class);
}

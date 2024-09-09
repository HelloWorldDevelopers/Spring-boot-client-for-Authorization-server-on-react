package ai.rnt.customerFeedback.dto_mapper;

import static ai.rnt.customerFeedback.util.FunctionUtil.evalMapper;

import java.util.Optional;
import java.util.function.Function;

import ai.rnt.customerFeedback.dto.CustomerDto;
import ai.rnt.customerFeedback.entity.Customer;

public class CustomerDtoMapper {
	
	private CustomerDtoMapper() {}
	
//	public static final Function<Customer, Optional<CustomerDto>> TO_CUSTOMER_DTO = e -> evalMapper(
//			e, CustomerDto.class);
	
	public static final Function<CustomerDto, Optional<Customer>> TO_CUSTOMER = e -> evalMapper(e, Customer.class);


}

package ai.rnt.customerFeedback.dto;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
@ExtendWith(MockitoExtension.class)
class CustomerFeedbackMasterDTONewTest {

	 @Test
	    void testCustomerFeedbackMasterDTONew() {
		 CustomerFeebackMasterDTONew customerFeebackMasterDTONew=new CustomerFeebackMasterDTONew();
		 customerFeebackMasterDTONew.setCustFeedbackId(1);
		 assertEquals(1, customerFeebackMasterDTONew.getCustFeedbackId());
       
	    }

}

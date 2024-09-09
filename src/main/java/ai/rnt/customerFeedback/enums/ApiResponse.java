package ai.rnt.customerFeedback.enums;

import ai.rnt.customerFeedback.constants.ApiResponseKeyConstant;

public enum ApiResponse {

	DATA(ApiResponseKeyConstant.DATA), MESSAGE(ApiResponseKeyConstant.MESSAGE),
	DOCUMENTNAME(ApiResponseKeyConstant.DOCUMENTNAME), TOKEN(ApiResponseKeyConstant.TOKEN),
	SUCCESS(ApiResponseKeyConstant.SUCCESS);

	String data;

	ApiResponse(String data2) {
		this.data = data2;
	}

}

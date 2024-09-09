package ai.rnt.customerFeedback.exception;

import ai.rnt.customerFeedback.payloads.ApiError;

public class DTOConvertionException extends CRMException {

	private static final long serialVersionUID = 5224740785947546863L;

	public DTOConvertionException(ApiError error, Exception exception) {
		super(error, exception);
	}

	public DTOConvertionException(String error) {
		super(error);
	}
}

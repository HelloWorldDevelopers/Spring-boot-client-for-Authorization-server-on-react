package ai.rnt.customerFeedback.payloads;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class JwtAuthResponse {
	@JsonProperty("SUCCESS")
	private boolean status;
	@JsonProperty("TOKEN")
	private String token;

}

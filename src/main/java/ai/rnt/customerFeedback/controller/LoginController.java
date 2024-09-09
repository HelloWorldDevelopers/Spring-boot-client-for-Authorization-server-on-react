package ai.rnt.customerFeedback.controller;

import static ai.rnt.customerFeedback.constants.ApiConstants.BASE;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.security.Principal;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import ai.rnt.customerFeedback.dto.RBAC;
import ai.rnt.customerFeedback.entity.EmployeeMaster;
import ai.rnt.customerFeedback.repository.EmployeeMasterRepository;
import ai.rnt.customerFeedback.service.RoleService;
import ai.rnt.customerFeedback.util.RoleUtil;
import ai.rnt.customerFeedback.util.TokanEncrypterDecrypter;


@RestController
@CrossOrigin("*")
@RequestMapping(BASE)
public class LoginController {

	@Autowired
	TokanEncrypterDecrypter tokanEncrypterDecrypter;
	
	@Autowired
	RoleService roleService;
	
	@Autowired
	EmployeeMasterRepository employeeMasterRepository;
	 
	@Autowired
    private JwtDecoder jwtDecoder;

	 
	
	@GetMapping("/message")
	public ResponseEntity<?> getMessage() {
		  Map<String, Object> map=new HashMap<String, Object>();
	        map.put("data", "Data got ");
	        return new ResponseEntity<>(map,HttpStatus.OK);
 	}
	
	 @GetMapping("/tokenparse")
		public ResponseEntity<Map<String, Object>> tokenDecode(HttpServletRequest request,Principal principal) {
 			Map<String, Object> map1 = new LinkedHashMap();
			try {
				 
				Integer staffId  = Integer.parseInt(principal.getName().replaceAll("\\D+", ""));
				List<String> roles = roleService.getRoleList(staffId);
    			List<RBAC> rbacList = roleService.getRbacList(staffId);
    			Optional<EmployeeMaster> userDetail = employeeMasterRepository.findById(staffId);
    			map1.put("fullName", (tokanEncrypterDecrypter.encryptTokan(userDetail.get().getFirstName()+" "+userDetail.get().getLastName())));
                map1.put("staffId", (tokanEncrypterDecrypter.encryptTokan(userDetail.get().getStaffId().toString())));
                map1.put("emailId", (tokanEncrypterDecrypter.encryptTokan(userDetail.get().getEmailId())));
                 map1.put("role", tokanEncrypterDecrypter.encryptRoleList(roles));
                map1.put("RBAC", tokanEncrypterDecrypter.encryptTokan(RoleUtil.serializeObjectToJson(rbacList)));
                map1.put("SUCCESS", true);
			} catch (Exception e) {
				map1.put("SUCCESS", false);
				e.printStackTrace();
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
			return ResponseEntity.ok(map1);
		}
	
	 @GetMapping("/redirect")
	    public ResponseEntity<?> redirect(@RequestParam("code") String code) throws Exception {

	        System.err.println("----------------------------" + code);

	        String clientId = "customer";
	        String clientSecret = "secret"; // Optional for public clients, may be empty
	        String tokenEndpoint = "http://localhost:8080/oauth2/token";
	        String redirectUri = "http://localhost:3001/redirect";

	        // Retrieve the code verifier from the session or wherever it was stored
	        String codeVerifier = "R1Yh6bLyEm1E1-GsaZBt6WU9nJMrBF1Dl2upuVu888JMqzVa-8FWqi_SVWyBg-BQCNBQPpPpBUBu9yu_-MdfZbZZcp3cF3JdBaY6B0KppM43Va40klVTr0_DPvtaSMFW";

	        // Create the token request
	        URL url = new URL(tokenEndpoint);
	        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
	        connection.setRequestMethod("POST");
	        connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
	        connection.setDoOutput(true);

	        // Construct the POST body
	        String postData = "grant_type=authorization_code" +
	                           "&code=" + URLEncoder.encode(code, "UTF-8") +
	                           "&redirect_uri=" + URLEncoder.encode(redirectUri, "UTF-8") +
	                           "&client_id=" + URLEncoder.encode(clientId, "UTF-8") +
	                           "&code_verifier=" + URLEncoder.encode(codeVerifier, "UTF-8");

	        // Write POST data to the request body
	        try (OutputStream os = connection.getOutputStream()) {
	            os.write(postData.getBytes("UTF-8"));
	            os.flush();
	        }

	        // Read the response
	        StringBuilder response = new StringBuilder();
	        try (BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"))) {
	            String line;
	            while ((line = br.readLine()) != null) {
	                response.append(line);
	            }
	        }

	        // Close the connection
	        connection.disconnect();

	        // Process the response
	        ObjectMapper mapper = new ObjectMapper();
	        JsonNode jsonNode = mapper.readTree(response.toString());
	        String idToken = jsonNode.get("id_token").asText();
	        String accessToken = jsonNode.get("access_token").asText();

	        // Prepare the response
	        Map<String, String> map = new HashMap<>();
	        map.put("token", accessToken);
	        return new ResponseEntity<>(map, HttpStatus.OK);
	    }
	 
	 
	
}

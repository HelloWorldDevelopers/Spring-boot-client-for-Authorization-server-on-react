package ai.rnt.customerFeedback.security;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class JWTTokenHelper {

//	@Autowired
//	private EmployeeService service;
//	@Value("${jwt.secret.key}")
//	private String secret;
//
//	@Autowired
//	private RoleServiceImpl roleService;
//
//	public static final long JWT_TOKEN_VALIDITY = 2400000;
//
//	private final ConcurrentMap<String, Boolean> invalidatedTokens = new ConcurrentHashMap<>();
//
//	public String extractUsername(String token) {
//		return extractClaim(token, Claims::getSubject);
//	}
//
//	public Date extractExpiration(String token) {
//		return extractClaim(token, Claims::getExpiration);
//	}
//
//	public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
//		final Claims claims = extractAllClaims(token);
//		return claimsResolver.apply(claims);
//	}
//
//	public Claims extractAllClaims(String token) {
//		return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
//	}
//
//	private Boolean isTokenExpired(String token) {
//		return extractExpiration(token).before(new Date());
//	}
//
//	public String generateToken(UserDetails userDetails, HttpServletRequest request) {
//		Map<String, Object> claims = new HashMap<>();
//		try {
//			List<String> roleList = userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority)
//					.collect(Collectors.toList());
//
//			service.getEmployeeByUserId(userDetails.getUsername()).ifPresent(emp -> {
//				List<RBAC> rbacList = roleService.getRbacList(emp.getStaffId());
//				claims.put("fullName", emp.getFirstName() + " " + emp.getLastName());
//				claims.put("StaffId", emp.getStaffId());
//				claims.put("EmailId", emp.getEmailID());
//				claims.put("RBAC", rbacList);
//				claims.put("Role", roleList);
//				claims.put("ipAddress", request.getRemoteAddr());
//			});
//			return createToken(claims, userDetails.getUsername());
//		} catch (Exception e) {
//			claims.put("success", false);
//		}
//		return null;
//	}
//
//	private String createToken(Map<String, Object> claims, String subject) {
//		return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
//				.setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY))
//				.signWith(SignatureAlgorithm.HS256, secret).compact();
//	}
//
//	public Boolean validateToken(String token, UserDetails userDetails) {
//		final String username = extractUsername(token);
//		return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
//	}
//
//	public KeyPair getKeyPair() {
//		try {
//			KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(EncryptionAlgoConstants.RSA);
//			keyPairGenerator.initialize(4096);
//			return keyPairGenerator.generateKeyPair();
//		} catch (Exception e) {
//			log.error("error occured while getting the Keys.{} ", e.getMessage());
//		}
//		return null;
//	}
//
//	public String getfullNameOfLoggedInUser(String token) {
//		return nonNull(this.extractAllClaims(token)) ? this.extractAllClaims(token).get("fullName").toString() : null;
//	}
//
//	public String getIPAddress(String token) {
//		return nonNull(this.extractAllClaims(token)) ? this.extractAllClaims(token).get("ipAddress").toString() : null;
//	}
//
//	public Boolean isTokenInvalidated(String token) {
//		return invalidatedTokens.containsKey(token);
//	}
}

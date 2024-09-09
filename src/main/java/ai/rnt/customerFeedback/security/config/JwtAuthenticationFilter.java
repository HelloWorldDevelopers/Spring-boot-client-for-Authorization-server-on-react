//package ai.rnt.customerFeedback.security.config;
//
//import org.springframework.stereotype.Component;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//
//@Component
//@RequiredArgsConstructor
//@Slf4j
//public class JwtAuthenticationFilter extends OncePerRequestFilter {
//
////	@Autowired
////	private CustomUserDetails detailsService;
////
////	@Autowired
////	private JWTTokenHelper helper;
////
////	@Autowired
////	RSAToJwtDecoder rSAToJwtDecoder;
////
////	private HandlerExceptionResolver exceptionResolver;
////
////	public JwtAuthenticationFilter(HandlerExceptionResolver exceptionResolver) {
////		this.exceptionResolver = exceptionResolver;
////	}
////
////	@SuppressWarnings("unused")
////	@Override
////	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
////			throws ServletException, IOException {
////		try {
////			String requestTokenHeader = request.getHeader(AUTHORIZATION);
////			if (isNull(requestTokenHeader)) {
////				filterChain.doFilter(request, response);
////			} else {
////				String userName;
////				if (requestTokenHeader.startsWith(TOKEN_PREFIX_BEARER) && nonNull(requestTokenHeader)) {
////					requestTokenHeader = requestTokenHeader.substring(7);
////					requestTokenHeader = rSAToJwtDecoder.decryptTokan(requestTokenHeader);
////					if (!request.getRemoteAddr().equals(helper.getIPAddress(requestTokenHeader))) {
////						throw new AccessDeniedException("Access denied");
////					}
////					// requestTokenHeader = rSAToJwtDecoder.rsaToJwtDecoder(requestTokenHeader);
////					userName = this.helper.extractUsername(requestTokenHeader);
////					UserDetail loadUserByUsername = this.detailsService.loadUserByUsername(userName);
////					if (Boolean.TRUE.equals(this.helper.validateToken(requestTokenHeader, loadUserByUsername))) {
////						UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
////								requestTokenHeader, null, loadUserByUsername.getAuthorities());
////						usernamePasswordAuthenticationToken.setDetails(loadUserByUsername);
////						getContext().setAuthentication(usernamePasswordAuthenticationToken);
////					}
////				}
////				filterChain.doFilter(request, response);
////			}
////
////		} catch (Exception e) {
////			log.error("Got Excetion while checking request authorizations. Request: {} {} {}",
////					request.getHeader(AUTHORIZATION), e.getClass(), e.getMessage());
////			exceptionResolver.resolveException(request, response, null, e);
////
////		}
////	}
//
//}

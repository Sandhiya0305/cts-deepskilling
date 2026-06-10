# Task Description

Implement JWT-based security for REST API. Create: JwtTokenProvider (generateToken, validateToken, getUsernameFromToken), JwtAuthenticationFilter (extends OncePerRequestFilter, extracts and validates token from Authorization header), SecurityConfig (@EnableWebSecurity, SecurityFilterChain bean with permitAll for /api/auth/** and authenticated for others, BCryptPasswordEncoder bean). Create AuthController with /api/auth/login (returns JWT) and /api/auth/register. Show how to access authenticated user in controllers using @AuthenticationPrincipal.

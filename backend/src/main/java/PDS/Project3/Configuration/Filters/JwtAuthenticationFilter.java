package PDS.Project3.Configuration.Filters;

import PDS.Project3.Configuration.TokenProvider;
import PDS.Project3.Domain.Entities.HTTPResponse;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

import static java.time.LocalTime.now;
import static java.util.Arrays.asList;
import static java.util.Optional.ofNullable;
import static org.apache.commons.lang3.StringUtils.EMPTY;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.*;

@Component
@RequiredArgsConstructor
@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private static final String TOKEN_PREFIX = "Bearer ";
    private static final String[] PUBLIC_ROUTES = {"user/login"};
    private final TokenProvider tokenProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            String token = getToken(request);
            String userName = getUserName(request);
            if (tokenProvider.isTokenValid(userName, token)) {
                List<GrantedAuthority> authorities = tokenProvider.getAuthorities(token);
                Authentication auth = tokenProvider.getAuthentication(userName, authorities, request);
                SecurityContextHolder.getContext().setAuthentication(auth);
            } else {
                SecurityContextHolder.clearContext();
            }
            filterChain.doFilter(request, response);
        } catch (JWTVerificationException e) {
            log.error("JWT Verification Error: {}", e.getMessage());
            handleJwtException(response, e);
        }
    }

    private void handleJwtException(HttpServletResponse response, JWTVerificationException e) throws IOException {
        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        response.setContentType("application/json");
        HTTPResponse errorResponse = HTTPResponse.builder()
                .timeStamp(now().toString())
                .reason("Invalid JWT token")
                .developerMessage(e.getMessage())
                .status(BAD_REQUEST)
                .statusCode(HttpServletResponse.SC_BAD_REQUEST)
                .build();
        response.getWriter().write(new ObjectMapper().writeValueAsString(errorResponse));
    }


    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return      request.getHeader(AUTHORIZATION) == null
                || !request.getHeader(AUTHORIZATION).startsWith(TOKEN_PREFIX)
                || asList(PUBLIC_ROUTES).contains(request.getRequestURI());
    }

    private String getUserName(HttpServletRequest request) {
        return tokenProvider.getSubject(getToken(request), request);
    }

    private String getToken(HttpServletRequest request) {
        return ofNullable(request.getHeader(AUTHORIZATION))
                .filter(header -> header.startsWith(TOKEN_PREFIX))
                .map(token -> token.replace(TOKEN_PREFIX, EMPTY)).get();
    }
}

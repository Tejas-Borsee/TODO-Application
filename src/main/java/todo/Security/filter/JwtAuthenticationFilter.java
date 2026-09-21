package todo.Security.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import todo.Security.service.impl.SessionServiceImpl;
import todo.Security.utils.JwtUtils;
import todo.common.exception.UnprocessableException;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtUtils jwtUtils;
    private final UserDetailsService userDetailsService;
    private final SessionServiceImpl sessionService;

    public JwtAuthenticationFilter(JwtUtils jwtUtils, UserDetailsService userDetailsService, SessionServiceImpl sessionService) {
        this.jwtUtils = jwtUtils;
        this.userDetailsService = userDetailsService;
        this.sessionService = sessionService;
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain)
            throws ServletException, IOException {

        String authorizationHeader =
                request.getHeader("Authorization");


        if (authorizationHeader == null ||
                !authorizationHeader.startsWith("Bearer ")) {

            filterChain.doFilter(request, response);
            return;
        }


        String token =
                authorizationHeader.substring(7);


        try {

            /*
             * First validate JWT
             */
            if (!jwtUtils.isTokenValid(token)) {

                filterChain.doFilter(request, response);
                return;
            }


            /*
             * Extract username and JTI
             */
            String username = jwtUtils.extractUsername(token);

            String jti = jwtUtils.extractJti(token);

            String userId = jwtUtils.extractUserId(token);
            /*
             * Check whether this is the
             * currently active session
             */
            if (!sessionService.isSessionActive(username, jti)) {

                filterChain.doFilter(request, response);
                return;
            }


            /*
             * Create Spring Security authentication
             */
            if (SecurityContextHolder
                    .getContext()
                    .getAuthentication() == null) {

                UserDetails userDetails =
                        userDetailsService
                                .loadUserByUsername(username);


                UserAuthentication authentication =
                        new UserAuthentication(
                                userDetails,
                                null,
                                userDetails.getAuthorities(),
                                userId
                        );


                authentication.setDetails(
                        new WebAuthenticationDetailsSource()
                                .buildDetails(request)
                );


                SecurityContextHolder
                        .getContext()
                        .setAuthentication(authentication);
            }

        } catch (Exception e) {

            /*
             * Invalid JWT / invalid session
             */
            SecurityContextHolder
                    .clearContext();
        }


        /*
         * Always continue the filter chain
         */
        filterChain.doFilter(request, response);
    }
}

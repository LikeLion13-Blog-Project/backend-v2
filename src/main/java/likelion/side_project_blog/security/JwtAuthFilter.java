package likelion.side_project_blog.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import likelion.side_project_blog.service.JwtTokenService;
import likelion.side_project_blog.service.UserDetailsServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@AllArgsConstructor
@Component
public class JwtAuthFilter extends OncePerRequestFilter {

    private final JwtTokenService jwtTokenService;
    private final UserDetailsServiceImpl userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if (request.getRequestURI().equals("/articles") && request.getMethod().equals("GET")) {
            filterChain.doFilter(request, response); // 다음 필터로 바로 넘김
            return; //여기서 메서드 실행을 종료하여 아래 JWT 검증 로직이 실행되지 않도록 함
        }

        // `/auth/login` 경로도 permitAll이므로
        if (request.getRequestURI().equals("/auth/login") && request.getMethod().equals("POST")) {
            filterChain.doFilter(request, response);
            return;
        }

        // OPTIONS 메서드도 permitAll이므로 명시적으로 스킵 처리 (SecurityConfig에서 이미 처리했지만, 필터 내부에서도 명확히 하는 것이 좋음)
        if (request.getMethod().equals("OPTIONS")) {
            filterChain.doFilter(request, response);
            return;
        }

        //헤더 토큰
        String token=jwtTokenService.resolveToken(request);
        if (token == null) {
            System.out.println("[JwtAuthFilter] 토큰 없음, 인증 없이 요청 처리");
        } else if (!jwtTokenService.validateToken(token)) {
            System.out.println("[JwtAuthFilter] 토큰 유효하지 않음");
        }

        if(token!=null&&jwtTokenService.validateToken(token)){
            String userId= jwtTokenService.getUserId(token);
            UserDetails userDetails=userDetailsService.loadUserByUsername(userId);
            Authentication authentication=new UsernamePasswordAuthenticationToken(
                    userDetails,null,userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        filterChain.doFilter(request,response);
    }
}

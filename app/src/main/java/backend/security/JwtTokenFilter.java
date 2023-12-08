// package backend.security;

// import java.io.IOException;

// import org.springframework.http.HttpHeaders;
// import org.springframework.security.core.userdetails.UserDetails;
// import org.springframework.stereotype.Component;
// import org.springframework.web.filter.OncePerRequestFilter;

// import backend.repository.UserRepository;
// import jakarta.servlet.FilterChain;
// import jakarta.servlet.ServletException;
// import jakarta.servlet.http.HttpServletRequest;
// import jakarta.servlet.http.HttpServletResponse;
// import lombok.AllArgsConstructor;
// import lombok.RequiredArgsConstructor;

// @Component
// @AllArgsConstructor
// public class JwtTokenFilter extends OncePerRequestFilter {
//     private final UserRepository userRepository;
//     private final JwtUtils jwtUtils;

//     private final String authHeader = "Bearer ";

//     @Override
//     protected void doFilterInternal(HttpServletRequest req, HttpServletResponse resp, FilterChain filterChain)
//             throws ServletException, IOException {
//         final String header = req.getHeader(HttpHeaders.AUTHORIZATION);
//         // header validation
//         if (header.isEmpty() || !header.startsWith(authHeader)) {
//             filterChain.doFilter(req, resp);
//             return;
//         }

//         // get jwt token
//         final String jwtToken = header.split(" ")[1].trim();
//         // jwt token validation
//         // if (!jwtUtils.isTokenValid(jwtToken)) {
//         //     filterChain.doFilter(req, resp);
//         //     return;
//         // }
        
//         // UserDetails userDetails = userRepository.findByName(jwtUtils.getUsername(jwtToken));

//     }
    
// }

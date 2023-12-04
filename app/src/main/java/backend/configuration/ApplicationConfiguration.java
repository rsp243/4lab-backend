package backend.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import backend.exceptions.UsernameNotFoundException;
import backend.model.Users;
import backend.repository.UsersRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
@RequiredArgsConstructor
public class ApplicationConfiguration {
    // private final UsersRepository userRepository;

    // @Bean
    // public UserDetailsService userDetailsService() {
    //     return name -> {
    //         Users user = null;
    //         try {
    //             user = userRepository.findByName(name)
    //                     .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    //         } catch (UsernameNotFoundException e) {
    //             e.printStackTrace();
    //         }
    //         return new CustomUserDetails(user);
    //     };
    // }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

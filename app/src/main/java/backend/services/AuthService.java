package backend.services;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import backend.DTO.TokenDTO;
import backend.DTO.UsersDTO;
import backend.exceptions.ApiException;
import backend.exceptions.DoesNotExistException;
import backend.exceptions.WrongPasswordException;
import backend.model.Users;
import backend.repository.UserRepository;
import backend.security.JwtUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository usersRepository;
    // private final AuthenticationManager authenticationManager;
    // private final AuthentificatedMap authMap;
    private final JwtUtils jwtUtils;
    private final PasswordEncoder passwordEncoder;

    public TokenDTO login(UsersDTO req) throws DoesNotExistException, ApiException, WrongPasswordException {
        // authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
        //         req.getName(), req.getPassword()));

        Users userEntity = usersRepository.findByName(req.getName())
                .orElseThrow(() -> new DoesNotExistException(req.getName()));
        
        if (!passwordEncoder.matches(req.getPassword(), userEntity.getPassword()))
            throw new WrongPasswordException(req.getName());
            
        // CustomUserDetails customUserDetails = new CustomUserDetails(userEntity);
        TokenDTO token = new TokenDTO(jwtUtils.generateAccessToken(userEntity));
        // authMap.addNewHash(userEntity, token);
        return token;
    }
}

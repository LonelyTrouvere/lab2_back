package uni.LonelyTrouvere.lab.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import uni.LonelyTrouvere.lab.dto.AuthDTO;
import uni.LonelyTrouvere.lab.dto.LoginDTO;
import uni.LonelyTrouvere.lab.entity.Password;
import uni.LonelyTrouvere.lab.entity.User;
import uni.LonelyTrouvere.lab.repository.UserRepo;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepo userRepository;

    public Optional<AuthDTO> auth(LoginDTO loginDTO){
        Optional<User> user = userRepository.findUserByEmail(loginDTO.getEmail());
        if(user.isEmpty()){
            return Optional.empty();
        }
        User confirmedUser = user.get();
        Password pwd = Password.builder()
                .hash(confirmedUser.getPassword())
                .salt(confirmedUser.getSalt())
                .build();
        String hashedPwd = BCrypt.hashpw(loginDTO.getPassword(), pwd.getSalt());
        if(!hashedPwd.equals(pwd.getHash())){
            return Optional.empty();
        }
        Algorithm algorithm = Algorithm.HMAC256("baeldung");
        JWTVerifier verifier = JWT.require(algorithm)
                .withIssuer("Baeldung")
                .build();
        String jwt = JWT.create()
                .withIssuer("Baeldung")
                .withClaim("id", confirmedUser.getId())
                .withClaim("login", confirmedUser.getLogin())
                .withClaim("email", confirmedUser.getEmail())
                .withClaim("role", confirmedUser.getRole())
                .sign(algorithm);
        return Optional.of(new AuthDTO(jwt));
    }
}

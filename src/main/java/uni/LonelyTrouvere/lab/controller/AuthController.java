package uni.LonelyTrouvere.lab.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import uni.LonelyTrouvere.lab.dto.AuthDTO;
import uni.LonelyTrouvere.lab.dto.LoginDTO;
import uni.LonelyTrouvere.lab.service.AuthService;
import uni.LonelyTrouvere.lab.service.JsonParser;

import java.util.Optional;

@RestController
@RequestMapping(value = "/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173/")
public class AuthController {
    private final AuthService authService;
    @PostMapping
    private String auth(@RequestBody LoginDTO loginDto) throws Exception {
        Optional<AuthDTO> response = authService.auth(loginDto);
        if(response.isEmpty()){
            return "[]";
        }
        return JsonParser.toJsonObject(response.get());
    }

}

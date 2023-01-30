package cat.itacademy.barcelonactiva.S05DiceGameJWTAlbertMartin.security;

import lombok.Data;

@Data
public class AuthCredentials {

    private String email;
    private String password;
}

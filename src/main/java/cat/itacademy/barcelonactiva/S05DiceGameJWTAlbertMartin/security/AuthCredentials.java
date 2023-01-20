package cat.itacademy.barcelonactiva.S05DiceGameJWTAlbertMartin.security;

import lombok.Data;

//esta clase recibe tanto el nombre como la contraseña
@Data
public class AuthCredentials {

    private String email;
    private String password;
}

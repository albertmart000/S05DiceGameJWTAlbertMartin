package cat.itacademy.barcelonactiva.S05DiceGameJWTAlbertMartin.security;

import cat.itacademy.barcelonactiva.S05DiceGameJWTAlbertMartin.exceptions.ResourceNotFoundException;
import cat.itacademy.barcelonactiva.S05DiceGameJWTAlbertMartin.repository.IUserRepository;
import cat.itacademy.barcelonactiva.S05DiceGameJWTAlbertMartin.entity.User;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

//con @Autowired
//@Service
//public class UserDetailsServiceImpl implements UserDetailsService {
//
//  @Autowired
//  private IUserRepository userRepository;
//
//    //ResourceNotFoundException??
//    @Override
//    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//        User user = userRepository
//                .findOneByEmail(email)
//                .orElseThrow(() -> new UsernameNotFoundException("El usuario con email" + email + "no existe."));
//        return new UserDetailsImpl(user);
//    }
//}

// con @RequiredArgsConstructor
@RequiredArgsConstructor
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final IUserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository
                .findOneByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("Usuari", "email", email));
                //.orElseThrow(() -> new UsernameNotFoundException("El usuario con email" + email + "no existe."));
        return new UserDetailsImpl(user);
    }
}